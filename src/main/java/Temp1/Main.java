package Temp1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {

        TextContainer c = new TextContainer();
        Class<?> cls = c.getClass();

        if (!cls.isAnnotationPresent(SaveTo.class)) {
            System.out.println("Error");
            return;
        }

        SaveTo saveTo = cls.getAnnotation(SaveTo.class);
        String path = saveTo.path();
        Method[] methods = cls.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Save.class)) {
                try {
                    method.invoke(c, path);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

    }

}
