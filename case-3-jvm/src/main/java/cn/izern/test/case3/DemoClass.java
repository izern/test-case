package cn.izern.test.case3;

import cn.izern.test.case3.loader.CustomClassLoader;
import cn.izern.test.case3.loader.EmptyClassLoader;
import cn.izern.test.case3.spi.service.HelloService;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ServiceLoader;
import sun.misc.Launcher;

/**
 * @author: zern
 * @since 1.0.0
 */
public class DemoClass {

  private static String className = "cn.izern.test.case3.DemoClass";

  public static void main(String[] args) throws ClassNotFoundException {
    printClassLoaderInfo();
    loadClassWithDiffClassLoader();
    serviceLoader();
//    这里报错，原因待查
    Thread.currentThread().setContextClassLoader(new CustomClassLoader());
    serviceLoader();
  }

  public static void printClassLoaderInfo() {
    EmptyClassLoader echoClassLoader = new EmptyClassLoader();
    System.out.println(String.format("echoClassLoader父 类加载器:%s", echoClassLoader.getParent()));

    System.out.println(String.format("系统classLoader：%s", ClassLoader.getSystemClassLoader()));
    System.out.println(String.format("boot classLoader：%s", Launcher.getBootstrapClassPath()));
    System.out.println(String
        .format("应用程序classLoader:%S,父级loader:%s", EmptyClassLoader.class.getClassLoader(),
            EmptyClassLoader.class.getClassLoader().getParent()));

    assert
        String.class.getClassLoader() == null
        : "String类不是由bootstrap加载？" + String.class.getClassLoader();

    System.out.println("bootstrap classloader 加载路径：");
    Arrays.asList(System.getProperty("sun.boot.class.path").split(File.pathSeparator))
        .forEach(System.out::println);
    System.out.println("ext classloader 加载路径：");
    Arrays.asList(System.getProperty("java.ext.dirs").split(File.pathSeparator))
        .forEach(System.out::println);

    System.out.println("app classloader 加载路径：");
    Arrays.asList(System.getProperty("java.class.path").split(File.pathSeparator))
        .forEach(System.out::println);

  }

  public static void loadClassWithDiffClassLoader() throws ClassNotFoundException {

    Class<?> class1 = new EmptyClassLoader().loadClass(className);
    Class<?> class2 = new EmptyClassLoader().loadClass(className);

    System.out.println("双亲委派模式下，重复加载类，class对象是同一个，比较结果：" + (class1 == class2));

    CustomClassLoader classLoader1 = new CustomClassLoader();
    Class<?> class3 = classLoader1.loadClass(className);

    CustomClassLoader classLoader2 = new CustomClassLoader();
    Class<?> class4 = classLoader2.loadClass(className);
    System.out.println("打破双亲委派，不同类加载器实例重复加载，class对象不同，比较结果：" + (class3 == class4));

  }

  public static void serviceLoader() throws ClassNotFoundException {
    System.out.println("加载HelloService实现类，并执行");
    //
    Class<HelloService> clazz = (Class<HelloService>) Thread.currentThread().getContextClassLoader()
        .loadClass(HelloService.class.getName());

    Iterator<HelloService> iterator = ServiceLoader.load(clazz).iterator();
    while (iterator.hasNext()) {
      Object helloService = iterator.next();
      clazz.cast(helloService).sayHello();
      System.out.println(String.format("当前实现类为%s,加载器为:%S", helloService.toString(),
          helloService.getClass().getClassLoader()));
    }
  }

}
