package dev.omedia.creational.prototype;

public class Color implements Cloneable{
    private String color;

    public Color(String color) {
        this.color = color;
    }


    public void setColor(String newColor) {
        this.color = newColor;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Color{" +
                "color='" + color + '\'' +
                '}';
    }
}
