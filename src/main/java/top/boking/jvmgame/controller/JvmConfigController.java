package top.boking.jvmgame.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.RuntimeMXBean;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/jvm")
public class JvmConfigController {
    
    @GetMapping("/config")
    public Map<String, Object> getJvmConfig() {
        Map<String, Object> config = new HashMap<>();
        
        // 获取运行时信息
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        config.put("jvmArgs", runtime.getInputArguments());
        
        // 获取内存信息
        MemoryMXBean memory = ManagementFactory.getMemoryMXBean();
        config.put("heapMemoryUsage", memory.getHeapMemoryUsage());
        config.put("nonHeapMemoryUsage", memory.getNonHeapMemoryUsage());
        
        // 获取GC信息
        config.put("gcCollectors", ManagementFactory.getGarbageCollectorMXBeans()
                .stream()
                .map(gc -> gc.getName())
                .toArray());
                
        return config;
    }
}