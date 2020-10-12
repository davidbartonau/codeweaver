package writer;

import common.Tags;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CodeWriter {
    File file;

    public CodeWriter(String name){
        if(Objects.isNull(file)){
            file = new File(name);
        }
    }

    public void writeToFile(List<String> rows, Map<Integer, Tags> tagsMap) {
        try(FileWriter fileWriter = new FileWriter(file)) {
            for (String row : rows) {
                fileWriter.write(row + System.lineSeparator());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
