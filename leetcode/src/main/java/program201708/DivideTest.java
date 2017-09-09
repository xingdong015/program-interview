package program201708;

/**
 * @author zhengcheng
 * @date 2017/8/30
 * @time 下午2:12
 * <p>
 * <p>
 * Divide two integers without using multiplication, division and mod operator.
 * <p>
 * If it is overflow, return MAX_INT.
 **/

public class DivideTest {
    public static void main(String[] args) {
        System.out.println(divide(5,-1));
    }
    public static  int divide(int dividend, int divisor) {




        int sign = 1;
        if((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) sign = -1;

        long ldivident = Math.abs((long)dividend);
        long ldivisor = Math.abs((long)divisor);

        if(ldivisor ==0 )return Integer.MIN_VALUE;

        long result = ldivide(ldivident,ldivisor);

        if(ldivident == 0 || ldivident < ldivisor) return 0;

        int ans = 0;
        if(result > Integer.MAX_VALUE){
            ans =  sign == -1 ? Integer.MIN_VALUE:Integer.MAX_VALUE;
        }else{
            ans = (int)(sign * result);
        }
        return ans;



    }

    public static long ldivide(long ldivident,long ldivisor){
        if(ldivident < ldivisor) return 0;
        long sum = ldivisor;
        int multiply = 1;
        while((sum + sum) <= ldivident){
            sum += sum;
            multiply += multiply;
        }
        return multiply + ldivide(ldivident - sum,ldivisor);
    }

}
