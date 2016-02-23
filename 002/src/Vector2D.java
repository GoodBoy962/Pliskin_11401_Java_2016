public class Vector2D {

    private double x;

    private double y;

    public Vector2D() {

    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2D addVector(Vector2D v2) {
        this.x += v2.getX();
        this.y += v2.getY();
        return this;
    }
}
