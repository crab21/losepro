package cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * Created by k on 2018/6/26.
 */
public class Client {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Hello.class);
        enhancer.setCallback(new HelloServiceImpl());
        Hello hello = (Hello) enhancer.create();
        hello.sayHello();
    }
}
