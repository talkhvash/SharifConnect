package shared.util;

import java.io.*;
import java.nio.file.Files;
import java.util.Base64;

public class ImageUtil {

    public static byte[] decodeFromBase64(String string) {
        if (string == null) return null;
        return Base64.getDecoder().decode(string);
    }

    private static String encodeToBase64(byte[] bytes) {
        if (bytes == null) return null;
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String fileToString(String path) throws FileNotFoundException {
        String output = "";
        if (!path.equals("")) {
            File file = new File(path);
            if (file.exists()) {
                try {
                    output = ImageUtil.encodeToBase64(Files.readAllBytes(file.toPath()));
                } catch (IOException ignored) {
                }
            }
        }
        return output;
    }



}
