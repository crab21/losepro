import com.google.common.collect.Lists;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;
import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Learn3 {

    public static void t1() throws URISyntaxException, IOException {
        /**
         * 下载照片
         */
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpClientContext httpClientContext = HttpClientContext.create();
        //使用HttpGet方式请求网址
//        HttpUriRequest httpGet =
        String url = "http://urp.npumd.cn/validateCodeAction.do?random=1";
        RequestBuilder requestBuilder = RequestBuilder.get();
        HttpUriRequest build = requestBuilder.setUri(url).build();

        //获取网址的返回结果
        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(build, httpClientContext);
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();

            FileOutputStream file = new FileOutputStream(new File("/home/k/wpy.jpeg"));

            int flag = 0;
            byte[] b = new byte[2048];
            while ((flag = content.read(b)) != -1) {
                file.write(b, 0, flag);
            }

            if (content != null) {
                content.close();
            }
            if (file != null) {
                file.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        //准备登录
        URI uri = build.getURI();
        System.out.println(uri.toString());

        CookieStore cookieStore = httpClientContext.getCookieStore();
        RequestConfig requestConfig = httpClientContext.getRequestConfig();
        System.out.println(cookieStore.toString());
        System.out.println("请输入验证码");
        String v_yzm = new Scanner(System.in).next();
        List<NameValuePair> loginNV = new ArrayList<>();
        loginNV.add(new BasicNameValuePair("dzslh", ""));
        loginNV.add(new BasicNameValuePair("eflag", ""));
        loginNV.add(new BasicNameValuePair("evalue", ""));
        loginNV.add(new BasicNameValuePair("fs", ""));
        loginNV.add(new BasicNameValuePair("lx", ""));
        loginNV.add(new BasicNameValuePair("mm", "131292"));
        loginNV.add(new BasicNameValuePair("tips", ""));
        loginNV.add(new BasicNameValuePair("v_yzm", v_yzm));
        loginNV.add(new BasicNameValuePair("zjh", "131292"));
        loginNV.add(new BasicNameValuePair("zjh1", ""));


        HttpPost post = new HttpPost("http://urp.npumd.cn/loginAction.do");

        post.setEntity(new UrlEncodedFormEntity(loginNV, "UTF-8"));


        post.setHeader("Accept", "text/html,application/xhtml+xm…plication/xml;q=0.9,*/*;q=0.8");
        post.setHeader("Accept-Encoding", "gzip,deflate");
        post.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0");
        post.setHeader("Connection", "keep-alive");
//        post.setHeader("Cookie", "JSESSIONID=acbiclZc9gfDh8GMtugmw");
        post.setHeader("Host", "urp.npumd.cn");
        post.setHeader("Upgrade-Insecure-Requests", "1");


        HttpResponse execute = httpClient.execute(post, httpClientContext);
        HttpEntity entity = execute.getEntity();
        InputStream content = entity.getContent();

        FileOutputStream ff = new FileOutputStream(new File("/home/k/wpy.html"));
        int len = 0;
        byte[] by = new byte[2048];
        while ((len = content.read(by)) != -1) {
            ff.write(by, 0, len);
        }
        content.close();
        ff.close();


        //利用登录cookie信息
        System.out.println(httpClientContext.getCookieStore().toString());

        HttpClient newHttpClient = HttpClientBuilder.create().setDefaultCookieStore(httpClientContext.getCookieStore()).build();

        HttpUriRequest httpUriRequest = RequestBuilder.get().setUri("http://urp.npumd.cn/xjInfoAction.do?oper=xjxx").build();

        HttpResponse execute1 = newHttpClient.execute(httpUriRequest, httpClientContext);

        InputStream content1 = execute1.getEntity().getContent();
        FileOutputStream ffs = new FileOutputStream(new File("/home/k/ss.html"));
        int len1 = 0;
        byte[] by1 = new byte[2048];
        ffs.flush();
        while ((len1 = content1.read(by1)) != -1) {
            ffs.write(by, 0, len1);
        }
        content1.close();
        ffs.close();
    }

    public static void main(String[] args) {
        try {
            t1();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
