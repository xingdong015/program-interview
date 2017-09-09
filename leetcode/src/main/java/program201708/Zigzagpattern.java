package program201708;

/**
 * The string "PAYPALISHIRING" is written in a
 * zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 *
 * 0                8                               16
 * 1            7   9                       15      17
 * 2        6       10              14              18
 * 3    5           11      13                      19
 * 4                12                              20
 *
 * @author zhengcheng
 * @date 2017/8/16
 * @time 上午10:13
 **/

public class Zigzagpattern {
    public static void main(String[] args) {
        System.out.println(zigZag("ABCD",3));
    }

    public static String zigZag(String s, int n) {
        if(n == 1 || s.length() <= 1){
            return s;
        }
        int len = s.length();
        char[]res = new char[len];
        int interv  = (n << 1) - 2;
        int k = 0;
        for(int i = 0;i < len;i += interv ){
            res[k++] = s.charAt(i);
        }
        //处理中间的
        for(int i = 1;i < n - 1;i++){
            int temp = i * 2;
            for(int j = i; j < len;j += temp){
                res[k++] = s.charAt(j);
                temp = interv - temp;
            }
        }

        //处理最后一行
        for(int j = n - 1;j < len;j += interv){
            res[k++] = s.charAt(j);
        }
        return new String(res);
    }

}
