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
        int[] arrs = new int[]{5,1,3};

        System.out.println(searchFromRotatedArray(arrs, 5));
        //left < right     left > right
    }

    public static int searchFromRotatedArray(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target)
                return mid;

            /**
             *     left  mid right
             *  4,   5,  6,   1,   2,   3
             *
             * 收获：遇到问题关键是分析问题、列出各种可能情况从而进行分类处理，
             * 几种情况
             * 1、left mid right都在左边增长子序列             nums[left] < nums[mid] < nums[right]
             * 2、left mid right都在右边增长子序列             nums[left] < nums[mid] < nums[right]
             * 3、left mid在左边增长子序列，right在右边增长子序列 nums[left] < nums[mid] > nums[right]
             * 4、left在左边增长子序列，mid和right在右边增长子序列 nums[mid] < nums[right] < nums[left]
             */

            //递增子序列   1,3  2

            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;

                }
            }

            if (nums[right] >= nums[mid]) {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;

    }
}
