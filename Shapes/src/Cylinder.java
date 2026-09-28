public class Cylinder extends Circle {
    private double height;

    public Cylinder(double height, double radius, String color) {
        super(radius, color);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double volume() {
        return area() * height;
    }

    @Override
    public void printInfo() {
        System.out.println("Cylinder " + color + ", volume = " + volume());
    }
}