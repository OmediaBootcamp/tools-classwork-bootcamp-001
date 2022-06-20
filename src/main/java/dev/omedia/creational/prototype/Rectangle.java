package dev.omedia.creational.prototype;

public class Rectangle extends Shape<Rectangle> {
    private double width;
    private double height;

    public Rectangle(int x, int y, Color color, double width, double height) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    public Rectangle(Shape source, double width, double height) {
        super(source);
        this.width = width;
        this.height = height;
    }

    public Rectangle(Rectangle source) {
        super(source);
        this.width = source.width;
        this.height = source.height;
    }

    @Override
    public Rectangle clone() {
        return new Rectangle(this);
    }

    @Override
    public String toString() {
        return
                "Rectangle{" +
                        "Super: " + super.toString() +
                        "width=" + width +
                        ", height=" + height +
                        '}';
    }
}
