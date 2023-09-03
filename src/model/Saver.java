package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Saver {
    String fileName;

    public Saver(String fileName) {
        this.fileName = fileName;
    }

    public void save(Saveable data) throws IOException {

        File file = new File(this.fileName);
        boolean add = file.exists();
        try (FileWriter fw = new FileWriter(file, add)) {
            if (add) fw.write("\n");
            fw.write(data.getRepresentation());
        }
    }

}
