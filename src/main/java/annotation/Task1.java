/*1. Создать аннотацию, которая принимает параметры для метода. Написать код, который вызовет метод,
        помеченный этой аннотацией, и передаст параметры аннотации в вызываемый метод.
        "@Test(a=2, b=5)
public void test(int a, int b) {...}"*/

package annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Task1 {
    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> c = Task1.class;
        Method[] methods = c.getDeclaredMethods();

        for (Method mthd : methods) {
            if (mthd.isAnnotationPresent(Test.class)) {
                Test t = mthd.getAnnotation(Test.class);
                int f = (Integer)mthd.invoke(null, t.a(),  t.b());
                System.out.println("Результат должен быть равен трем, если метод сработал правильно. Результат: " + f);
            }
        }
    }

    @Test(a = 1, b = 2)
    public static int method(int a, int b) {
        return a + b;
    }
}