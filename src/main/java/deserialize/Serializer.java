package deserialize;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.InvalidParameterException;

public class Serializer {
    public static String serialize(Object o, String path) throws IllegalAccessException {

        Class<?> cls = o.getClass();
        StringBuilder sb = new StringBuilder();

        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream writer = new FileOutputStream(path);

            Field[] fields = cls.getDeclaredFields();
            for (Field f : fields) {
                if (!f.isAnnotationPresent(Save.class))
                    continue;
                if (Modifier.isPrivate(f.getModifiers()))
                    f.setAccessible(true);

                sb.append(f.getName() + "=");

                if (f.getType() == int.class) {

                     sb.append(f.getInt(o));
                } else if (f.getType() == String.class) {

                     sb.append((String) f.get(o));
                } else if (f.getType() == long.class) {

                    sb.append(f.getLong(o));
                }

                sb.append("!");
            }
            byte [] str = sb.toString().getBytes();
            writer.write(str);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


       return path;
    }

    public static <T> T deserialize(String s, Class<T> cls)
            throws IllegalAccessException, InstantiationException, NoSuchFieldException, IOException {
        T res = (T) cls.newInstance();

        FileInputStream is = new FileInputStream(s);
        int i=0;
        byte [] bytes= new byte[is.available()];
        while(true){
            try {
                if (!((i=is.read(bytes))!= -1)) break;


            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        String string = new String(bytes);
        System.out.println("string = "+string);
        String[] pairs = string.split("!");
        System.out.println("length = "+pairs.length);
        System.out.println(pairs[0]);
        System.out.println(pairs[1]);

        for (String p : pairs) {
            System.out.println(p);
            String[] nv = p.split("=");
            if (nv.length != 2)
                throw new InvalidParameterException(s);

            String name = nv[0];
            String value = nv[1];
            Field f = cls.getDeclaredField(name);
            if (Modifier.isPrivate(f.getModifiers()))
                f.setAccessible(true);

            if (f.isAnnotationPresent(Save.class)) {
                if (f.getType() == int.class) {
                    f.setInt(res, Integer.parseInt(value));
                } else if (f.getType() == String.class) {
                    f.set(res, value);
                } else if (f.getType() == long.class) {
                    f.setLong(res, Long.parseLong(value));
                }
            }
        }
        return res;
    }
}