package cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * Created by k on 2018/6/26.
 */
public class HelloServiceImpl implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before" + method.getName());
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("after" + method.getName());
        return object;
    }

    public static void main(String[] args) {
        User user = null;
        User user2 = Optional.ofNullable(user).orElse(null);
        System.out.println(user2);
    }
}
