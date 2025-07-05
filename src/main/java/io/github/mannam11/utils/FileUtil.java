package io.github.mannam11.utils;

import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static String getMimeType(File file) throws IOException {
        Tika tika = new Tika();
        return tika.detect(file);
    }
}
