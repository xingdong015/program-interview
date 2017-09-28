package program201709;

/**
 * @author zhengcheng
 * @date 2017/9/24
 * @time 下午10:38
 * <p>
 * <p>
 * 给定一个字符串，要求把字符串前面的若干个字符移动到字符串的尾部，如把字符串“abcdef”前面的
 * 2个字符'a'和'b'移动到字符串的尾部，使得原字符串变成字符串“cdefab”。
 * 请写一个函数完成此功能，要求对长度为n的字符串操作的时间复杂度为 O(n)，空间复杂度为 O(1)
 **/

public class ReverseString {
    public static void main(String[] args) {
        char[] string = "abcdef".toCharArray();

        System.out.println(string);
        reverse(string, 0, 2);
        reverse(string, 3, 5);
        reverse(string, 0, 5);
        System.out.println(string);


    }

    private static void reverse(char[] string, int i, int j) {
        while (i < j) {
            char temp = string[i];
            string[i] = string[j];
            string[j] = temp;
            i++;
            j--;
        }
    }

}
