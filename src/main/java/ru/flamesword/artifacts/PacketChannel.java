package ru.flamesword.artifacts;

public enum PacketChannel {
    ARTIFACTSOTHER(0);

    private final int id;

    private PacketChannel(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}

