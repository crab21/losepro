import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Learn1 {


    public static void test1() {
        try {
            Document doc = Jsoup.connect("http://urp.npumd.cn/")
                    .header("Accept", "*/*")
                    .header("Accept-Encoding", "gzip,deflate")
                    .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
//                                .header("Referer","https://www.baidu.com/")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                    .timeout(5000)
                    .get();
            System.out.println(doc.body().toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void test2() {
        Connection con = Jsoup.connect("http://urp.npumd.cn/")
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip,deflate")
                .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                //                                .header("Referer","https://www.baidu.com/")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                .timeout(5000);
        try {
            Document document = con.get();
            Elements imgs = document.getElementsByTag("img");
            for (Element element : imgs) {
                String imgSrc = element.attr("abs:src");
                System.out.println(imgSrc);

            }
            int size = imgs.size();
            System.out.println(size);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test3() {

        try {
            URL url = new URL("http://www.baidu.com/");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            String cookie = con.getHeaderField("set-cookie");
            System.out.println(cookie);
            ImageIO.write(ImageIO.read(con.getInputStream()), "png", new File(
                    "/home/k/wang"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void test4(String value){
        try {
            Map<String,String> map = new HashMap<>();
            map.put("dzslh","");
            map.put("eflag","");
            map.put("evalue","");
            map.put("fs","");
            map.put("lx","");
            map.put("mm","131292");
            map.put("tips","");
            map.put("v_yzm",value);

            map.put("zjh","131292");
            map.put("zjh1","");

            Connection.Response execute = Jsoup.connect("http://urp.npumd.cn/loginAction.do")
                    .header("Accept", "text/html,application/xhtml+xm…plication/xml;q=0.9,*/*;q=0.8")
                    .header("Accept-Encoding", "gzip,deflate")
                    .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                    //                                .header("Referer","https://www.baidu.com/")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                    .header("Connection","keep-alive")
                    .header("Cookie","JSESSIONID=acbiclZc9gfDh8GMtugmw")
                    .header("Host","urp.npumd.cn")
//                    .header("Referer","http://urp.npumd.cn/loginAction.do")
                    .header("Upgrade-Insecure-Requests","1")
                    .data(map)
                    .method(Connection.Method.POST)
                    .execute();
            Document parse = execute.parse();
            System.out.println(parse.body().toString());
            String cookie = execute.cookie("JSESSIONID");

            System.out.println(cookie);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        test4(Learn2.test1());
//        test4();
    }


}
