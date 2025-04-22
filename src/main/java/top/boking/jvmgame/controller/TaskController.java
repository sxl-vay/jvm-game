package top.boking.jvmgame.controller;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    
    private final List<Thread> runningTasks = new ArrayList<>();
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    
    @PostMapping("/cpu")
    public String startCPUTask(@RequestParam int durationSeconds) {
        Runnable task = () -> {
            long endTime = System.currentTimeMillis() + (durationSeconds * 1000);
            while (System.currentTimeMillis() < endTime) {
                // CPU密集型计算
                Math.sqrt(Math.random() * 10000);
            }
        };
        
        executorService.submit(task);
        return "CPU任务已启动，持续时间: " + durationSeconds + "秒";
    }
    
    @PostMapping("/memory-leak")
    public String simulateMemoryLeak() {
        List<byte[]> leakyList = new ArrayList<>();
        Thread leakThread = new Thread(() -> {
            while (true) {
                try {
                    leakyList.add(new byte[1024 * 1024]); // 1MB
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        
        leakThread.start();
        runningTasks.add(leakThread);
        return "内存泄漏模拟已启动";
    }
    
    @DeleteMapping("/stop-all")
    public String stopAllTasks() {
        for (Thread task : runningTasks) {
            task.interrupt();
        }
        runningTasks.clear();
        executorService.shutdownNow();
        return "所有任务已停止";
    }
}