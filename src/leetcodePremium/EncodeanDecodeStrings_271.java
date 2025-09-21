package leetcodePremium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 271. Encode and Decode Strings

Hard

Design an algorithm to encode a list of strings to a string. 
The encoded string is then sent over the network and is decoded back to the original list of strings.

Example 1:

Input: strs = ["Hello","World"]

Output: "5#Hello4#World"

Example 2:

Input: strs = ["a","b","c"]

Output: "1#a1#b1#c"

Constraints:

1 <= strs.length <= 200

0 <= strs[i].length <= 1000

1 <= total length of all strings <= 10^6

strs[i] consists of printable ASCII characters
 * */
public class EncodeanDecodeStrings_271 {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();

        for (String s : strs) {

            sb.append(s.length()).append("#").append(s);
        }

        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> result = new ArrayList<>();
        int i = 0;
        
        while( i < s.length()) {
            int hashIndex = s.indexOf("#", i);
            int length = Integer.parseInt(s.substring(i, hashIndex));
            String str = s.substring(hashIndex + 1, hashIndex + 1 + length);
            
            result.add(str);
            
            i = hashIndex + 1 + length;
        }
        
        return result;
    }

    public static void main(String[] args) {
        EncodeanDecodeStrings_271 solution = new EncodeanDecodeStrings_271();
        List<String> strs = Arrays.asList("Hello","World");
        
        String encode = solution.encode(strs);
        List<String> decodes = solution.decode(encode);
        
        System.out.println(encode);
        
        for(String s : decodes) {
            System.out.println(s);
        }
    }

}
