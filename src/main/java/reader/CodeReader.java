package reader;

import common.Tags;

import java.io.File;
import java.util.*;

public class CodeReader {

    private File file;
    private Scanner scanner;

    @lombok.SneakyThrows
    public CodeReader(File file){
        this.file = file;
        if(Objects.isNull(scanner)){
            scanner = new Scanner(file);
        }
    }

    public List<String> getAllRowsAsList(){
        List<String> rows = new ArrayList<>();

        while(scanner.hasNextLine())
            rows.add(scanner.nextLine());

        scanner.close();

        return rows;
    }

    public Map<Integer, Tags> findTagsIndex(List<String> rows){
        Map<Integer, Tags> map = new LinkedHashMap<>();
        List<Tags> tags = Arrays.asList(Tags.values());

        for(int i=0; i<rows.size(); i++){
            String row = rows.get(i);
            if(row.trim().isEmpty()) continue;
            for(Tags tag: tags){
                if(row.contains(tag.label)) {
                    map.put(i, tag);
                    break;
                }
            }
        }

        return map;
    }
}
