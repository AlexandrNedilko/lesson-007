/*"2. Написать класс TextContainer, который содержит в себе строку. С помощью механизма аннотаций указать
        1) в какой файл должен сохраниться текст
        2) метод, который выполнит сохранение. Написать класс Saver,
        который сохранит поле класса TextContainer в указанный файл."
        "@SaveTo(path=“c:\\file.txt”)
class Container {
    String text = “...”;
    @Saver
    public void save(..) {...}
}"*/

package MyAnnotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Task2 {
        public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
            TextContainer object = new TextContainer();
            Class cls = object.getClass();
            SaveTo an = (SaveTo) cls.getAnnotation(SaveTo.class);
            Method[] m = cls.getDeclaredMethods();
            for (Method method : m) {
                if (method.isAnnotationPresent(Saver.class)) {
                    method.invoke(object, an.path());
                    break;
                }
            }

        }
    }