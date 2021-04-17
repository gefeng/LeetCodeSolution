package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Encode and Decode TinyURL",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/encode-and-decode-tinyurl/"
)
public class Q535 {
    /*
        0 - 9 10
        a - z 26
        A - Z 26
              62

        len(code) = 6

        62^6  let's assume we consume 100k tiny url per day
        overall we can run out in ~1500years

        map
        key   value
        code  original url
    */
    private static final int LENGTH = 6;
    private static final String charMap = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static Map<Integer, String> database = new HashMap<>();
    private static int idGenerator = 1; // simulate database auto-incremental primary key id
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        int id = idGenerator++;
        database.put(id, longUrl);

        StringBuilder urlBuilder = new StringBuilder();
        for(int i = 0; i < LENGTH; i++) {
            urlBuilder.append(charMap.charAt(id % 62));
            id /= 62;
        }

        String shortUrl = urlBuilder.reverse().toString();
        return shortUrl;  // aaaaab
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        int id = 0;
        for(int i = 0; i < shortUrl.length(); i++) {
            char c = shortUrl.charAt(i);
            if(c >= 'a' && c <= 'z') {
                id = id * 62 + c - 'a';
            } else if(c >= 'A' && c <= 'Z') {
                id = id * 62 + 26 + c - 'A';
            } else if(c >= '0' && c <= '9') {
                id = id * 62 + 52 + c - '0';
            }
        }

        return database.get(id);
    }
}
