package org.example;

public class MemoryLogger {
    public static void log(){
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        System.out.println("---------------------------");
        System.out.println("Used Memory : " + bytesToMB(usedMemory) + " MB");
        System.out.println("Free Memory : " + bytesToMB(freeMemory) + " MB");
        System.out.println("Total Memory: " + bytesToMB(totalMemory) + " MB");
        System.out.println("---------------------------");

        System.out.println("---------------------------");
        System.out.println("Used Memory : " + bytesToKB(usedMemory) + " KB");
        System.out.println("Free Memory : " + bytesToKB(freeMemory) + " KB");
        System.out.println("Total Memory: " + bytesToKB(totalMemory) + " KB");
        System.out.println("---------------------------");


    }

    
    private static long bytesToMB(long bytes) {
        return bytes / (1024 * 1024);
    }

    private static long bytesToKB(long bytes) {
        return bytes / 1024;
    }
}
