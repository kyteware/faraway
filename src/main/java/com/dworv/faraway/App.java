package com.dworv.faraway;

public class App {
    public static void main(String[] args) throws Exception {
        // Get the filename from the command line arguments
        if (args.length < 1) {
            System.err.println("you must pass an argument");
            return;
        }
        String filename = args[0];
        // Read the scene from the file
        Scene scene = FScene.readFScene(filename);
        // Render the scene
        scene.render_cams();
    }
}
