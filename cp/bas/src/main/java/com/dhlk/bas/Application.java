package com.dhlk.bas;

import com.dhlk.bas.netty.DiscardServer;
import com.dhlk.bas.util.CarbonDioxideUtil;
import com.dhlk.bas.util.FileUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Application {

    // localIp & localPort
    public static String localIp;
    public static String localPort;

    // socket
    public static String socketPort;

    // 上下文对象
    public static ApplicationContext context;

    // Environment
    public static Environment environment;

    public static void main(String[] args) throws Exception {

        // 实例化全局变量
        context = SpringApplication.run(Application.class, args);
        environment = context.getEnvironment();

        localIp = environment.getProperty("localIp");
        localPort = environment.getProperty("localPort");
        socketPort = environment.getProperty("socketPort");

        // 将静态资源写入到Redis
        FileUtil.init(null);

        // 启动Socket
        DiscardServer.init(null);

        // Test
        CarbonDioxideUtil.init(null);

    }
}
