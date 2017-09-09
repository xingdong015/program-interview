package program201708;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhengcheng
 * @date 2017/8/23
 * @time 下午9:18
 **/

public class FourSum {
    public static void main(String[] args) {
        System.out.println(fourSum(new int[]{-3,-2,-1,0,0,1,2,3},0));
    }
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = Lists.newArrayList();
        if(nums.length < 4){
            return new ArrayList<>();
        }

        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        for(int i = 0;i < nums.length-3;i++){
            for(int j = i+1;j < nums.length - 2;j++){
                int left = j+1;int right = nums.length - 1;
                while(left < right){
                    int sum = nums[i]+nums[j]+nums[left]+nums[right];
                    if(sum == target){
                        ArrayList<Integer> integers = Lists.<Integer>newArrayList(nums[i], nums[j], nums[left], nums[right]);
                        if(!judge(result,integers)) {
                            result.add(integers);
                        }
                        left++;
                        right--;
                    }else if(sum > target){
                        right--;
                    }else{
                        left++;
                    }
                }

            }
        }
        return result;
    }

    public static boolean judge(List<List<Integer>> result,List<Integer> each){
        return result.contains(each)?true:false;
    }




}
