/*"3. Написать код, который сериализирует и десериализирует в/из файла все значения полей класса, которые
        отмечены аннотацией @Save."*/
package deserialize;

public class Main {
    public static void main (String[] args) throws Exception {
        String filepath = "c:\\temp\\file1.txt";
        Test t = new Test();
        t.setA(7);
        t.setB("Hello");
        t.setC(123L);

        String path = Serializer.serialize(t, filepath);
        System.out.println("Serialized: " + path);

        Test t1 = new Test();
        t1 = Serializer.deserialize(path, Test.class);
        System.out.println("Deserialized: " + t1.getA() + ", " + t1.getB() + ", " + t1.getC());
    }
}
