package program201708;

/**
 * @author zhengcheng
 * @date 2017/8/18
 * @time 上午9:24
 **/

public class MaxAreaTest {
    public static void main(String[] args) {

    }

    public static int maxArea(int[] array) {
        int i = 0;
        int j = array.length - 1;
        int res = 0;
        while (i < j) {
            int max = Math.min(array[i], array[j]) * (j - i);
            res = Math.max(max,res);
            if(array[i] <= array[j]){
                i++;
            }else{
                j--;
            }
        }
        return res;
    }
}
