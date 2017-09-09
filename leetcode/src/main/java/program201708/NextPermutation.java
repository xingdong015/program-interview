package program201708;

import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * <p>
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * <p>
 * The replacement must be in-place, do not allocate extra memory.
 * <p>
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * <p>
 * https://segmentfault.com/a/1190000003766260
 *
 * @author zhengcheng
 * @date 2017/9/6
 * @time 上午10:27
 **/

public class NextPermutation {
    public static void main(String[] args) {
        int[] ints = {1, 2};
        System.out.println(Arrays.toString(ints));
        nextPermutation(ints);
        System.out.println(Arrays.toString(ints));
    }

    public static void nextPermutation(int[] nums) {
        if (nums.length <= 1) {
            return;
        }

        int i = nums.length - 2;

        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {

            int j = nums.length - 1;

            while (j < i && nums[j] <= nums[i]) {
                j--;
            }

            //交换这两个值
            swap(nums, i, j);
        }

        reverse(nums,i+1,nums.length-1);

    }

    private static void reverse(int[] nums, int left, int right) {
        while(left < right){
            swap(nums,left,right);
            left++;
            right--;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
