package program201708;

/**
 * Given a string s, find the longest palindromic
 * substring in s. You may assume that the maximum
 * length of s is 1000.
 *
 * @author zhengcheng
 * @date 2017/8/14
 * @time 上午9:44
 **/

public class PalindromicSubStr {
    public static void main(String[] args) {
        String s = ("a");
        System.out.println(longestPalindrome(s));


    }


    public static String longestPalindrome(String s) {

        String longestStr = "";
        if(s.length() <= 1){
            return s;
        }
        for (int i = 0; i < s.length() - 1; i++) {

            String longest = getLongest(i, i, s);
            if (longest.length() > longestStr.length()) {
                longestStr = longest;
            }


            int j = i;
            int j1 = i + 1;
            char c1 = s.charAt(j);
            char c2 = s.charAt(j1);
            if (c1 != c2) {
                continue;
            }

            String longest1 = getLongest(j, j1, s);


            if (longest1.length() > longestStr.length()) {
                longestStr = longest1;
            }
        }
        return longestStr;

    }

    public static String getLongest(int start, int end, String s) {

        while (start >= 0 && end <= s.length() - 1 && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }
        return s.substring(start + 1, end);
    }

}
