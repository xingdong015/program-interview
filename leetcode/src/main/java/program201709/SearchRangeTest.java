package program201709;

import java.util.Arrays;

/**
 * Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * If the target is not found in the array, return [-1, -1].
 * <p>
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 * <p>
 * 链接：http://www.jianshu.com/p/7fb01346ef73
 *
 * @author zhengcheng
 * @date 2017/9/10
 * @time 下午4:55
 **/

public class SearchRangeTest {

    public static void main(String[] args) {
        int[] nums = {5, 7, 8, 8, 8, 8,10};
        System.out.println(Arrays.toString(searchRange(nums, 1)));
    }

    public static int[] searchRange(int[] nums, int target) {

        int left = -1;
        int right = -1;
        int[] results = new int[]{left, right};

        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                right = mid;
                while (right < nums.length && nums[right] == target) {
                    right++;
                }

                left = mid;
                while (left >= 0 && nums[left] == target) {
                    left--;
                }

                return new int[]{left + 1, right - 1};
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return results;

    }


}
