import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhengcheng
 * @date 2017/9/14
 * @time 下午3:15
 **/

public class test {
    public static void main(String[] args) {
        String body = "var segList = eval('[{\"departure\":\"PEK\",\"arrival\":\"TAO\",\"depEn\":\"PEKING\",\"arrEn\":\"QINGDAO\",\"depCityName\":\"北京\",\"arrCityName\":\"\n" +
                "青岛\",\"tktNo\":\"7818213852815\",\"flightNo\":\"MU5678\",\"cabin\":\"Q\",\"depDateTime\":\"2017-09-15 00:15\",\"seatNo\":\"42L\",\"type\":2,\"cancelAction\":1,\"printA\n" +
                "ction\":1,\"checkinAction\":0,\"seatAction\":1,\"canAsr\":0,\"depDate\":\"2017-09-15\",\"depTime\":\"00:15\",\"isStop\":0,\"isToAF\":0,\"isToDL\":0,\"mkt\":\"CN\",\"carr\n" +
                "AirlineCode\":\"\",\"carrFlightNumber\":\"MU5678\"}]')";

        String regex = "var segList = eval\\('(.*\n.*\n.*\n.*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(body);

        if (matcher.find()) {
            System.out.println(matcher.group(1));
            System.out.println("find");
        }else{
            System.out.println("not find");
        }

    }




}
