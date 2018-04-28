import com.asprise.ocr.Ocr;
import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Learn2 {
    public static String test1() {
        try {
            FileUtils.copyURLToFile(new URL("http://urp.npumd.cn/validateCodeAction.do?random=1"), new File("/home/k/wpy.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Ocr.setUp();
        Ocr ocr = new Ocr();
        ocr.startEngine("eng", Ocr.SPEED_FASTEST);
        File filepicF = new File("/home/k/wpy.jpeg");
        String s = ocr.recognize(new File[]{filepicF}, Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT);
        System.out.println(s);
        ocr.stopEngine();
        return s;
    }

    public static void ts2() {
        try {
            Connection timeout = Jsoup.connect("http://urp.npumd.cn/validateCodeAction.do?random=1")
                    .header("Accept", "text/html,application/xhtml+xm…plication/xml;q=0.9,*/*;q=0.8")
                    .header("Accept-Encoding", "gzip,deflate")
                    .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                    //                                .header("Referer","https://www.baidu.com/")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                    .header("Connection", "keep-alive")
                    .header("Cookie", "JSESSIONID=acbiclZc9gfDh8GMtugmw")
                    .header("Host", "urp.npumd.cn")
                    //                    .header("Referer","http://urp.npumd.cn/loginAction.do")
                    .header("Upgrade-Insecure-Requests", "1")
                    .timeout(5000);
            Connection.Response execute = timeout.method(Connection.Method.GET).execute();


        } catch (Exception e) {
            e.printStackTrace();
        }























    



    }


    public static void main(String[] args) {
        ts2();
    }
}
