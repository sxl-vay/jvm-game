package top.boking.jvmgame.service;

import top.boking.jvmgame.model.MemoryObject;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MemoryService {
    private final Map<String, MemoryObject> objectStore = new ConcurrentHashMap<>();
    
    public String createObject(int sizeInKB) {
        MemoryObject obj = new MemoryObject(sizeInKB);
        objectStore.put(obj.getId(), obj);
        return obj.getId();
    }

    public MemoryObject createNoRefObject(int sizeInKB) {
        MemoryObject obj = new MemoryObject(sizeInKB);
        return obj;
    }
    
    public void removeObject(String id) {
        objectStore.remove(id);
    }
    
    public Map<String, MemoryObject> getAllObjects() {
        return objectStore;
    }
    
    public void clearAll() {
        objectStore.clear();
    }
}