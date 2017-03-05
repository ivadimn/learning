package ru.ivadimn.notes.database;

/**
 * Created by vadim on 05.03.2017.
 */

public class Blob  {
    private byte[] bytes;
    private int size;
    public Blob(int size) {
        this.size = size;
        bytes = new byte[size];
    }
    public Blob(byte[] bytes) {
        this.size = bytes.length;
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
