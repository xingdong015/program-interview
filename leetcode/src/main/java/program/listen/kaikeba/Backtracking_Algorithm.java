package program.listen.kaikeba;

import java.util.Arrays;

/**
 * @author zhengcheng
 * @date 2017/8/26
 * @time 上午10:23
 *
 *
 * 回朔算法
 *
 *
 * 八皇后问题：开课吧---->http://www.iqiyi.com/v_19rri000hc.html#curid=244489500_6aa5d33a9747731d2a99f59154e301d0
 *
 * 回朔问题采用递归实现
 **/

public class Backtracking_Algorithm {

   public static void main(String[] args) {
       int[]a = new int[8];

       f(a,0);
   }

    private static void f(int[] a, int row) {

       if(row == 8){
           System.out.println(Arrays.toString(a));
           return;
       }
        for(int i = 0;i < 8;i++){
            a[row] = i;
            if(check(a,row,i)) f(a,row+1);
        }
    }

    private static boolean check(int[] a, int row, int col) {
       for(int i = 0;i < row;i++){
           //纵向检测
           if(col == a[i]) return false;

           //对角线检测
           if(row - i == Math.abs(col - a[i])) return false;

       }
        return true;
    }
}
