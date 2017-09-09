package program201708;

import java.util.List;

/**
 * @author zhengcheng
 * @date 2017/8/27
 * @time 下午2:42
 *
 *
Given n pairs of parentheses, write a function to generate all
combinations of well-formed parentheses.
For example, given n = 3, a solution set is:

[
"((()))",
"(()())",
"(())()",
"()(())",
"()()()"
]
 **/

public class Backtracking1Test {
   public static void main(String[] args) {
//        List<String> result = new ArrayList<>();
//        String temp = "";
//
//        kuohaoPrint(result,temp,0,0,100);
//       System.out.println(result);
       while(true){
           System.out.println("hello");
       }
   }

    private static void kuohaoPrint(List<String> result, String temp, int left, int right, int max) {

       if(temp.length() == max * 2){
           result.add(temp);
           return ;
       }
       if(left < max){
           kuohaoPrint(result,temp+"(",left+1,right,max);
       }
       if(right < left){
           kuohaoPrint(result,temp+")",left,right+1,max);
       }
    }


}
