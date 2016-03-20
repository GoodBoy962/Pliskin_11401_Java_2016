package com.pliskin;

public class ComplexVector2D {
    private ComplexNumber x, y;

    public ComplexVector2D() {
        this(new ComplexNumber(), new ComplexNumber());
    }

    public ComplexVector2D(ComplexNumber x, ComplexNumber y) {
        this.x = x;
        this.y = y;
    }

    public ComplexNumber getComplexNumberX() {
        return x;
    }

    public void setComplexNumberX(ComplexNumber x) {
        this.x = x;
    }

    public ComplexNumber getComplexNumberY() {
        return y;
    }

    public void setComplexNumberY(ComplexNumber y) {
        this.y = y;
    }

    public boolean equals(ComplexVector2D complexVector2D1) {
        if (this.x.equals(complexVector2D1.getComplexNumberX()) && (this.y.equals(complexVector2D1.getComplexNumberY()))) {
            return true;
        } else {
            return false;
        }
    }

}
