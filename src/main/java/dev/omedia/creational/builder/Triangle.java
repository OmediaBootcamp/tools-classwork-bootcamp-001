package dev.omedia.creational.builder;

public class Triangle {
    private final double lengthA;
    private final double lengthB;
    private final double lengthC;

    public Triangle(double lengthA, double lengthB, double lengthC) {
        this.lengthA = lengthA;
        this.lengthB = lengthB;
        this.lengthC = lengthC;

        if (!((lengthA + lengthB > lengthC) && (lengthA + lengthC > lengthB) && (lengthB + lengthC > lengthA))) {
            throw new IllegalArgumentException();
        }
    }

    public double getLengthA() {
        return lengthA;
    }

    public double getLengthB() {
        return lengthB;
    }

    public double getLengthC() {
        return lengthC;
    }

    public static TriangleBuilder getBuilder() {
        return new TriangleBuilder();
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "lengthA=" + lengthA +
                ", lengthB=" + lengthB +
                ", lengthC=" + lengthC +
                '}';
    }

    public static class TriangleBuilder {
        private double lengthA;
        private double lengthB;
        private double lengthC;

        private TriangleBuilder() {}

        public TriangleBuilder lengthA(double lengthA) {
            this.lengthA = lengthA;
            return this;
        }

        public TriangleBuilder lengthB(double lengthB) {
            this.lengthB = lengthB;
            return this;
        }

        public TriangleBuilder lengthC(double lengthC) {
            this.lengthC = lengthC;
            return this;
        }

        public void reset() {
            this.lengthA = 0;
            this.lengthB = 0;
            this.lengthC = 0;
        }

        public Triangle build() {
            return new Triangle(lengthA, lengthB, lengthC);
        }

    }
}
