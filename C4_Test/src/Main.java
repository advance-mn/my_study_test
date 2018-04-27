import my.study.spring4.Car;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static java.lang.Thread.currentThread;

public class Main {
    public static Car initByDefaultConst() throws Exception {
        //通过类装载器获取Car类对象
        ClassLoader loader = currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("my.study.spring4.Car");

        //获取类的默认构造函数，并通过它示例化Car对象
        Constructor cons = clazz.getDeclaredConstructor((Class[])null);
        Car car = (Car)cons.newInstance();

        //通过反射机制取得setter， 设置对象的各个属性；
        Method setBrand = clazz.getMethod("setBrand", String.class);
        setBrand.invoke(car, "红旗CA72");

        Method setColor = clazz.getMethod("setColor", String.class);
        setColor.invoke(car, "黑色");

        Method setMaxSpeed = clazz.getMethod("setMaxSpeed", int.class);
        setMaxSpeed.invoke(car, 250);

        return car;
    }

    public static void testClassLoader() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println("Current     loader: " + loader );
        System.out.println("Parent      loader:" + loader.getParent());
        System.out.println("grandParent loader:" + loader.getParent().getParent());
    }


    public static void main(String[] args) throws Exception {
        System.out.println("test1------------------");
        Car car = initByDefaultConst();
        car.introduce();

        System.out.println("test2------------------");
        testClassLoader();
    }
}
