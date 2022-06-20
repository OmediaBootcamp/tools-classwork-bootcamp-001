package dev.omedia;

import dev.omedia.creational.builder.Triangle;

/**
 * Hello world!
 */

// Creational Patterns(Singleton, Prototype, Builder)
public class App {
    public static void main(String[] args) {

//        Singleton example1 = Singleton.getInstance();
//        Singleton example2 = Singleton.getInstance();
//
//        System.out.println(example1 == example2);
//
//        String host = SingletonEnumVariant.INSTANCE.getHost();
//        Color initialRed = new Color("red");
//
//        Rectangle rectangle1 = new Rectangle(3, 6, initialRed, 4.5, 3.);
//        Rectangle rectangle2 = rectangle1.clone();
//
//        System.out.println(rectangle1);
//        System.out.println(rectangle2);
//
//        initialRed.setColor("green");
//
//        System.out.println(rectangle1);
//        System.out.println(rectangle2);

        Triangle triangle1 = Triangle.getBuilder().lengthA(3.).lengthB(2.).lengthC(4).build();
        Triangle.TriangleBuilder builder = Triangle.getBuilder();
        builder.lengthA(3.);

        // do something please
        builder.lengthB(2.);

        // do something
        builder.lengthC(4.);

        Triangle triangle2 = builder.build();

        System.out.println(triangle1);
        System.out.println(triangle2);

        builder.lengthC(0);
        Triangle triangle3 = builder.build();

    }
}
