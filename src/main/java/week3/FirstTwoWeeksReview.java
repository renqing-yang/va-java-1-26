package week3;


import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Out of memory error
 * 1. generate Heap Dump  -> JProfiler , Memory Analyzer
 * 2. check Memory Leak
 * 3. increase heap size
 * 4. change Reference type
 * 5. restart application
 *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  *
 * Volatile
 * CPU
 * L1
 * L2
 *
 * Shared memory (heap)
 *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  *
 * Reflection
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String type() default "abc";
}

@MyAnnotation(type = "def")
class MyReflectionTest {
    private int a = 0;

    public int getA() {
        System.out.println("invoking getA(): " + a);
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
class ReflectionExample {
    public static void main(String[] args) throws Exception {
        Class<MyReflectionTest> clazz = MyReflectionTest.class;

        Method methodGetA = clazz.getDeclaredMethod("getA");

        MyReflectionTest obj = clazz.getDeclaredConstructor().newInstance();
//        System.out.println(obj);
//        System.out.println(methodGetA);
        methodGetA.invoke(obj);

        Field field = clazz.getDeclaredField("a");
        field.setAccessible(true);
        field.set(obj, 10);
//        System.out.println(field);
        methodGetA.invoke(obj);

        MyAnnotation annotation = (MyAnnotation)clazz.getDeclaredAnnotations()[0];
        System.out.println(annotation.type());
    }
}

/**
 *  *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  *
 */

interface Car {
    void start();
    void stop();
    String getColor();
}
class BMWCar implements Car {
    @Override
    public void start() {
        System.out.println("BMW start");
    }

    @Override
    public void stop() {
        System.out.println("BMW stop");
    }

    @Override
    public String getColor() {
        return "white";
    }
}

/**
 * proxy impl1 , using inheritance
 */
class BMWCarProxy1 extends BMWCar implements Car {
    @Override
    public void start() {
        System.out.println("before BMW ");
        super.start();
        System.out.println("after BMW ");
    }

    @Override
    public void stop() {
        System.out.println("before BMW ");
        super.stop();
        System.out.println("after BMW ");
    }
}
/**
 * proxy impl2 , using interface
 */

class BMWCarProxy2 implements Car {
    private final Car bmwCar;

    public BMWCarProxy2(Car bmwCar) {
        this.bmwCar = bmwCar;
    }

    @Override
    public void start() {
        System.out.println("before BMW ");
        bmwCar.start();
        System.out.println("after BMW ");
    }

    @Override
    public void stop() {
        System.out.println("before BMW ");
        bmwCar.stop();
        System.out.println("after BMW ");
    }

    @Override
    public String getColor() {
        return null;
    }
}

class InvocationHandlerImpl implements InvocationHandler {
    private final Object obj;

    public InvocationHandlerImpl(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before BMW ");
        Object res = method.invoke(obj, args);
        System.out.println(method);
        System.out.println("after BMW ");
        return res;
    }
}
class JDKDynamicProxyExample {
    public static void main(String[] args) {
        Car car = (Car) Proxy.newProxyInstance(
                JDKDynamicProxyExample.class.getClassLoader(),
                new Class[]{Car.class},
                new InvocationHandlerImpl(new BMWCar())
        );
        String color = car.getColor();
        System.out.println(color);
    }
}
/**
 * Singleton Lazy Loading thread safe
 */
class Lazy {
    private static volatile Lazy instance;
    private Lazy() {}
    public static Lazy getInstance() {
        if(instance == null) {
            //T1, 2, 3
            synchronized (Lazy.class) {
                //T1
                if(instance == null) {
                    instance = new Lazy();
                }
            }
        }
        return instance;
    }
}
/**
 * HashMap equals and hashcode
 */

class StudentTeacher {
    private int id;

    public StudentTeacher(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void main(String[] args) {
        Map<StudentTeacher, Integer> map = new HashMap<>();
        StudentTeacher s1 = new StudentTeacher(1);
        StudentTeacher s2 = new StudentTeacher(1);
        map.put(s1, 1);
        System.out.println(map.get(s2));
    }
}


