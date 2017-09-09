package program201708;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengcheng
 * @date 2017/8/27
 * @time 下午2:42
 **/

public class BacktrackingTest {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        backtrack(list, "", 0, 0, n);
        return list;
    }
    public static void main(String[] args) {
        List<String> strings = new BacktrackingTest().generateParenthesis(3);
        System.out.println(strings);
    }

    public void backtrack(List<String> list, String str, int left, int right, int max){

        if(str.length() == max*2){
            list.add(str);
            return;
        }

        if(left < max)
            backtrack(list, str+"(", left+1, right, max);
        if(right < left)
            backtrack(list, str+")", left, right+1, max);
    }
}
