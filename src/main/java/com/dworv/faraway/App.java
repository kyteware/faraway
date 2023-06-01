package com.dworv.faraway;

public class App {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("you must pass an argument");
            return;
        }
        String filename = args[0];
        Scene scene = FScene.readFScene(filename);
        scene.render_cams();
    }
}
