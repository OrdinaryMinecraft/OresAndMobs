package ru.flamesword.ordinaryores.model;

import net.minecraft.world.World;

public class WorldPoint {

    public World world;
    public int x;
    public int y;
    public int z;

    public WorldPoint() {
    }

    public WorldPoint(World world, int x, int y, int z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

}
