package top.boking.jvmgame.controller;

import top.boking.jvmgame.model.MemoryObject;
import top.boking.jvmgame.service.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/memory")
public class MemoryController {
    
    @Autowired
    private MemoryService memoryService;
    
    @PostMapping("/object")
    public String createObject(@RequestParam int sizeInKB) {
        return memoryService.createObject(sizeInKB);
    }

    @PostMapping("/objectNoRef")
    public MemoryObject createNoRefObject(@RequestParam int sizeInKB) {
        return memoryService.createNoRefObject(sizeInKB);
    }


    
    @DeleteMapping("/object/{id}")
    public void removeObject(@PathVariable String id) {
        memoryService.removeObject(id);
    }
    
    @GetMapping("/objects")
    public Object getAllObjects() {
        return memoryService.getAllObjects();
    }
    
    @DeleteMapping("/objects")
    public void clearAll() {
        memoryService.clearAll();
    }
}