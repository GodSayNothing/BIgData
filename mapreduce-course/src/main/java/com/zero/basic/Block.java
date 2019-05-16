package com.zero.basic;

import java.io.Serializable;

public class Block implements Serializable {
    private long blockId;
    private long numBytes;
    private long generationStamp;

    public Block(long blockId, long numBytes, long generationStamp) {
        this.blockId = blockId;
        this.numBytes = numBytes;
        this.generationStamp = generationStamp;
    }

    public long getBlockId() { return blockId; }
    public long getNumBytes() { return numBytes; }
    public long getGenerationStamp() { return generationStamp; }

    @Override
    public String toString() {
        return "Block{" +
                "blockId=" + blockId +
                ", numBytes=" + numBytes +
                ", generationStamp=" + generationStamp +
                "}";
    }
}
