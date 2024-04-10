package co.istad.elearningapi.utils;

public class MediaUtils {

    public static String extractExtension(String name){
        // extract extension from file
        int lastDotIndex = name
                .lastIndexOf(".");

        return name
                .substring(lastDotIndex + 1);
    }
}
