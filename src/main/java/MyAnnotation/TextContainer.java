package MyAnnotation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SaveTo(path = "c:\\temp\\file.txt")
public class TextContainer {
    public String str = "\r\nПервое правило бизнеса — защищайте свои инвестиции. — этикет банкира, 1775 г."; //my String

    @Saver
    public void save(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(path,true);
            writer.write(str);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}