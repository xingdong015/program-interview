import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author zhengcheng
 * @date 2017/9/27
 * @time 下午4:15
 **/

public class lambda {

    public static void main(String[] args) throws IOException {
        //环绕执行模式
        String online = processFile((br) -> br.readLine());
        String string = processFile(br -> br.readLine() + br.readLine());

    }

    public static String processFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine();
        }
    }

    @FunctionalInterface
    interface BufferedReaderProcessor {
        String process(BufferedReader b) throws IOException;

    }

    public static String processFile(BufferedReaderProcessor processor) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return processor.process(br);
        }
    }
}
