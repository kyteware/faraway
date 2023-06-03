package com.dworv.faraway;

import java.io.File;

public class Utils {
    /**
     * Delete a directory.
     * <p>
     * This is used to delete a directory.
     * <p>
     * Example:
     * <pre>
     * Utils.deleteDirectory(new File("test"));
     * </pre>
     * @param folder the directory to delete
     */
    public static void deleteDirectory(File folder) {
        if (!folder.exists()) {
            return;
        }
        File[] files = folder.listFiles();
        if (files != null) {
            for (File f : files) {
                if(f.isDirectory()) {
                    deleteDirectory(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }
}
