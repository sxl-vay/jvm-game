package top.boking.jvmgame.model;

import lombok.Data;
import java.util.UUID;

@Data
public class MemoryObject {
    private String id;
    private byte[] payload;
    private long creationTime;

    public MemoryObject(int sizeInKB) {
        this.id = UUID.randomUUID().toString();
        this.payload = new byte[sizeInKB * 1024];
        this.creationTime = System.currentTimeMillis();
    }
}