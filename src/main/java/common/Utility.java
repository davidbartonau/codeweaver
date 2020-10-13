package common;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import java.util.List;

public class Utility {

    public static String calculateSha1OfRows(String rows){
        return Hashing.sha1().
                hashString( rows, Charsets.UTF_8)
                .toString();
    }

    public static String insertSha(String row, String sha){
        StringBuilder builder = new StringBuilder();
        int count = 0;
        for(int i=0;i<row.length();i++){
            if(row.charAt(i) == '}') {
                if(count == 1){
                    builder.append(row.substring(0, i));
                    builder.append(", sha: " + sha);
                    builder.append(row.substring(i));
                    break;
                }
                else count++;
            }
        }
        return builder.toString();
    }
}
