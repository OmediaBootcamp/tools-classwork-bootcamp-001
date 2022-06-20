package dev.omedia.creational.prototype;

import java.util.Objects;

public abstract class Shape<X extends Shape> implements Prototype<X> {
    private int x;
    private int y;
    private Color color;

    public Shape(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Shape(Shape source) {
        this.x = source.x;
        this.y = source.y;
        this.color = (Color) (source.color.clone());

    }

    public abstract X clone();

    @Override
    public String toString() {
        return "Shape{" +
                "x=" + x +
                ", y=" + y +
                ", color=" + color +
                '}';
    }
}
