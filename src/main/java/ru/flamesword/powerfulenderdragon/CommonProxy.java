package ru.flamesword.powerfulenderdragon;

public class CommonProxy {

    public void registerRenderers() {
        System.out.println("Detected server side, skipping renderer registration");
    }

}
