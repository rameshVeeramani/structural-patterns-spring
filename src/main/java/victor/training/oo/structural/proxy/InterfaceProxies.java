package victor.training.oo.structural.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Slf4j
public class InterfaceProxies {

    public static void main(String[] args) {
        MathImpl impl = new MathImpl();

        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                log.info("this method.....");
                return method.invoke(impl,args);
            }
        };

        Math math = (Math) Proxy.newProxyInstance(InterfaceProxies.class.getClassLoader(),
                new Class<?>[]{Math.class}, h);

        System.out.println(math.sum(1,1));
    }
}


interface Math{
    int sum(int a, int b);
}
class MathImpl implements Math{

    @Override
    public int sum(int a, int b) {
        return a+b;
    }
}