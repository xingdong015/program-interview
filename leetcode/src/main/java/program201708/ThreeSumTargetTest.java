package program201708;

import java.util.Arrays;

/**
 * @author zhengcheng
 * @date 2017/8/21
 * @time 上午9:38
 **/

public class ThreeSumTargetTest {
    public static void main(String[] args) {
        System.out.println(sumTarget(new  int[]{-1,2,1,-4},1));
    }

    public static int sumTarget(int[] array, int target) {
        if (array == null || array.length < 3) return 0;
        int cloestDist = Integer.MAX_VALUE;
        int ret = 0;
        Arrays.sort(array);
        for (int i = 0; i < array.length - 2; i++) {
            if (i == 0 || array[i - 1] != array[i]) {
                int j = i + 1;
                int k = array.length - 1;
                while (j < k) {
                    int sum = array[i] + array[j] + array[k];
                    if(sum > target){
                        if(sum - target < cloestDist){
                            cloestDist = sum - target;
                            ret = sum;
                        }

                        k--;

                        while(j < k && array[k] == array[k+1]){
                            k--;
                        }
                    }else if(sum < target){
                        if(target - sum < cloestDist){
                            cloestDist = target - sum;
                            ret = sum;
                        }
                        j++;

                        while(j < k && array[j] == array[j-1]){
                            j++;
                        }
                    }else{
                        return sum;
                    }
                }
            }
        }
        return ret;
    }

}
