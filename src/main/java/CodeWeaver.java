import common.Tags;
import reader.CodeReader;

import java.io.File;
import java.util.Map;

public class CodeWeaver {
    public static void main(String[] args) {
        File file = new File("D:\\upwork\\codeweaver\\src\\main\\java\\Opportunity.txt");
        CodeReader reader = new CodeReader(file);
        Map<Integer, String> map = reader.findTagsIndex(reader.getAllRowsAsList());
    }
}
