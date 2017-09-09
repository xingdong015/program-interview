package program201708;

/**
 * Implement atoi to convert a string to an integer.
 * <p>
 * Hint: Carefully consider all possible input cases. If you want a challenge,
 * please do not see below and ask yourself what are the possible input cases.
 *
 * @author zhengcheng
 * @date 2017/8/17
 * @time 上午9:25
 **/

public class AtoiTest {
    public static void main(String[] args) {
        System.out.println(atoi("1"));

    }

    public static  int atoi(String str) {
        if(str.length() < 1){
            return 0;
        }
        //remove the whitespace
        int index = 0;
        while(str.charAt(index) == ' ' && index < str.length()){
            index++;
        }
        int sign = 1;
        if(str.charAt(index) == '+' || str.charAt(index) == '-'){
            sign = str.charAt(index) == '-'?-1:1;
            index++;
        }

        int total = 0;
        while(index < str.length()){
            int digit = str.charAt(index) - '0';
            if(digit < 0 || digit > 9){
                break;
            }
            if(Integer.MAX_VALUE / 10 < total || (Integer.MAX_VALUE / 10 == total && Integer.MAX_VALUE % 10 < digit)){
                return sign == -1 ? Integer.MIN_VALUE:Integer.MAX_VALUE;
            }
            total = total * 10 + digit;
            index++;
        }
        return total*sign;


    }

}
