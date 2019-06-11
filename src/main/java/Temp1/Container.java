package Temp1;

import java.io.FileWriter;
import java.io.IOException;

@SaveTo(path = "Test.txt")
class TextContainer {

    String s = "Hello World!";

    @Save
    public void save(String path) throws IOException {
        FileWriter fw = new FileWriter(path);
        try {
            fw.write(s);
            System.out.println("Done");
        } finally {
            fw.close();
        }

    }

}