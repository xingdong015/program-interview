package program201708;

/**
 * https://segmentfault.com/a/1190000009133659
 * <p>
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * <p>
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * <p>
 * You may assume no duplicate exists in the array.
 *
 * @author zhengcheng
 * @date 2017/9/7
 * @time 下午1:57
 **/

public class SearchRotatedTarget {


    public static void main(String[] args) {
        int[] arrs = new int[]{3, 5, 6, 1, 2};
        System.out.println(searchFromRotatedArray(arrs, 3));
        //left < right     left > right
    }

    public static int searchFromRotatedArray(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target)
                return mid;

            if (nums[start] <= nums[mid]) {
                if (target < nums[mid] && target >= nums[start])
                    end = mid - 1;
                else
                    start = mid + 1;
            }

            if (nums[mid] <= nums[end]) {
                if (target > nums[mid] && target <= nums[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return -1;

    }
}
