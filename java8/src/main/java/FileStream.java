import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author zhengcheng
 * @date 2017/9/28
 * @time 下午6:46
 * @email chengzhengzheng@meituan.com
 **/

public class FileStream {
    public static void main(String[] args) throws IOException {
        long uniqueWords = 0;
        FileSystem aDefault = FileSystems.getDefault();


        /**
         * flatMap与map区别
         * 1、flatMap接受的是流参数
         * 2、map接受是一个lambda操作返回的是一个值、map讲集合每一个元素映射为另一个元素、而flatMap会将集合每一个元素映射为多个流、并且将他们合并为一个流
         *
         * Stream<Stream<String>> streamStream = lines.map(line -> Arrays.stream(line.split(" ")));
         Stream<String> stringStream = lines.flatMap(line -> Arrays.stream(line.split(" ")));
         */
        try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).
                    distinct().
                    count();
//            System.out.println(uniqueWords);

        } catch (IOException e) {
        }


        //由函数生成流:创建无限流

//        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);

        //斐波纳契元组序列
        Stream.iterate(new int[]{0, 1}, a -> new int[]{a[1], a[0] + a[1]}).limit(10).map(a->a[0])
//                .forEach(a -> System.out.println(a))
        ;

        //生成

        Stream.generate(Math::random).limit(10).forEach(System.out::println);

    }
}
