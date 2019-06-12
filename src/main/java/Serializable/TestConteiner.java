package Serializable;

import java.io.FileWriter;
import java.io.IOException;
@SaveTo(PATH = "c:\\temp\\file.txt")
public class TestConteiner {
    String text = "text from textContainer";

    @Saver
    public void save(String text1, String path) throws IOException {
        FileWriter w = new FileWriter(path);
        try {
            w.write(text1);
        } finally {
            w.close();
        }
    }
}