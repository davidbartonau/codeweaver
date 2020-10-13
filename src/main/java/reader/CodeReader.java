package reader;

import common.Tags;
import common.Utility;

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

    public Map<Integer, String> findTagsIndex(List<String> rows){
        Map<Integer, String> map = new LinkedHashMap<>();
        List<Tags> tags = Arrays.asList(Tags.values());

        /*for(int i=0; i<rows.size(); i++){
            String row = rows.get(i);
            if(row.trim().isEmpty()) continue;
            for(Tags tag: tags){
                if(row.contains(tag.label)) {
                    map.put(i, (row.split("}")[0]).split("\\{")[1]);
                    break;
                }
            }
        }*/

        for(int i=0;i<rows.size();i++){
            String row = rows.get(i);

            if(row.contains(Tags.PREOPEN.label)) {
                processForPreOpenToOpen(rows, i);
            }
            if(row.contains(Tags.OPEN.label)) {
                processForOpenToPostOpen(rows, i);
            }
        }

        return map;
    }

    private void processForPreOpenToOpen(List<String> list, int i){
        String row = list.get(i);
        String id = (row.split("}")[0]).split(":")[1];
        int j=0;
        for(j=i;j<= list.size();j++){
            if(list.get(j).contains(Tags.OPEN.label+":"+id)) break;
            else continue;
        }
        String sha = null;
        if (i == j) {
            // Found on same line
            String s = (row.split("\\*/")[1]).split("/*")[0];
             sha = Utility.calculateSha1OfRows(s);
        }
        else{
            StringBuilder builder = new StringBuilder();
            builder.append(row.lastIndexOf("/") != row.length()-1 ? list.get(i).split("\\*/")[1] : "");
            for(int x = i+1; x<j;x++){
                builder.append(list.get(x));
            }
            builder.append(list.get(j).split("/*")[0]);
            sha = Utility.calculateSha1OfRows(builder.toString());
        }
        list.remove(i);
        list.add(i, Utility.insertSha(row, sha));
    }

    private void processForOpenToPostOpen(List<String> list, int i){
        String row = list.get(i);
        String id = (row.split("}")[0]).split(":")[1];
        int j=0;
        for(j=i;j<= list.size();j++){
            if(list.get(j).contains(Tags.CLOSE.label+":"+id)) {
                //Close found for the tag with same id
            }
            if(list.get(j).contains(Tags.POSTOPEN.label+":"+id)) break;
            else continue;
        }
        String sha = null;
        if (i == j) {
            // Found on same line
            String s = (row.split("\\*/")[1]).split("/*")[0];
            sha = Utility.calculateSha1OfRows(s);
        }
        else{
            StringBuilder builder = new StringBuilder();
            builder.append(row.lastIndexOf("/") != row.length()-1 ? list.get(i).split("\\*/")[1] : "");
            for(int x = i+1; x<j;x++){
                builder.append(list.get(x));
            }
            builder.append(list.get(j).split("/*")[0]);
            sha = Utility.calculateSha1OfRows(builder.toString());
        }
        list.remove(i);
        list.add(i, Utility.insertSha(row, sha));
    }
}
