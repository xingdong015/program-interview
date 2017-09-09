package program201708;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengcheng
 * @date 2017/8/22
 * @time 上午9:33
 **/

public class LetterCombinationTest {
    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }

    public static List<String> letterCombinations(String digits){
        List<String> result = new ArrayList<>();//存放结果
        int index = 0;//标记指针
        String[]maps = getStrings();//码表
        char[]middleChar = new char[digits.length()];
        getStr(result,index,maps,middleChar,digits);
        return result;
    }

    private static void getStr(List<String> result, int index, String[] maps, char[] middleChar, String digits) {

        if(index == digits.length()){
            result.add(new String(middleChar));
            return;
        }
        for(int i = 0;i < maps[digits.charAt(index) - '0'].length();i++){
            middleChar[index] = maps[digits.charAt(index) - '0'].charAt(i);
            getStr(result,index+1,maps,middleChar,digits);
        }
    }


    private static String[] getStrings() {
        String[] map = new String[10];
        map[0] = "";
        map[1] = "";
        map[2] = "abc";
        map[3] = "def";
        map[4] = "ghi";
        map[5] = "jkl";
        map[6] = "mno";
        map[7] = "pqrs";
        map[8] = "tuv";
        map[9] = "wxyz";
        return map;
    }


}
