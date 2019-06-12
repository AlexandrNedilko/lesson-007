/*1. Создать аннотацию, которая принимает параметры для метода. Написать код, который вызовет метод,
        помеченный этой аннотацией, и передаст параметры аннотации в вызываемый метод.
        "@Test(a=2, b=5)
public void test(int a, int b) {...}"*/

package annotation;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Task1 {
    public static void main(String[] args)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> c = Task1.class;
        Method[] methods = c.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                Test t = method.getAnnotation(Test.class);
                 method.invoke(null, t.a(),  t.b());
                //System.out.println("Результат должен быть равен cем, если метод сработал правильно. Результат: " + f);

            }
        }
    }

    @Test(a = 2, b = 5)
    public static void test (int a, int b) {
        System.out.println("a " +a);
        System.out.println("b " +b);
        System.out.println("Результат должен быть равен cем, если метод сработал правильно. Результат: " + (a+b));

    }
}
