package ru.itvitality.sbrf.cu.cp.prototype;

public class Demo {
    public static void main(String[] args) {
        Sheep original = new Sheep("unknown");
        System.out.println(original);

        Sheep cloned = original.clone();

        System.out.println(original.equals(cloned));

        cloned.setName("Dolly");
        System.out.println(cloned);

        System.out.println(original.equals(cloned));
    }
}
