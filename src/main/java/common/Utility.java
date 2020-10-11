package common;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import java.util.List;

public class Utility {

    public static String calculateSha1OfRows(List<String> rows){
        return Hashing.sha1().
                hashString( String.join(System.lineSeparator(), rows), Charsets.UTF_8)
                .toString();
    }
}
