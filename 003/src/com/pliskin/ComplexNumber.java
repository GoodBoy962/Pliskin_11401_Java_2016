package com.pliskin;

public class ComplexNumber {

    private double re;

    private double im;

    public ComplexNumber() {
        this(0.0, 0.0);
    }

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public ComplexNumber add(ComplexNumber number) {
        return new ComplexNumber(
                re + number.getRe(),
                im + number.getIm()
        );
    }

    public void add2(ComplexNumber number) {
        re += number.getRe();
        im += number.getIm();
    }

    public ComplexNumber sub(ComplexNumber number) {
        return new ComplexNumber(
                re - number.getRe(),
                im - number.getIm()
        );
    }

    public void sub2(ComplexNumber number) {
        re -= number.getRe();
        im -= number.getIm();
    }

    public ComplexNumber mult(ComplexNumber number) {
        return new ComplexNumber(
                re * number.getRe() - im * number.getIm(),
                re * number.getIm() + im * number.getRe()
        );
    }

    public void mult2(ComplexNumber number) {
        re = re * number.getRe() - im * number.getIm();
        im = re * number.getIm() + im * number.getRe();
    }

    public ComplexNumber div(ComplexNumber number) {
        double mod = number.getIm() * number.getIm() + number.getRe() * number.getRe();
        return new ComplexNumber(
                (re * number.getRe() + im * number.getIm()) / mod,
                (im * number.getRe() - re * number.getIm()) / mod
        );
    }

    public void div2(ComplexNumber number) {
        double mod = number.getIm() * number.getIm() + number.getRe() * number.getRe();
        re = (re * number.getRe() + im * number.getIm()) / mod;
        im = (im * number.getRe() - re * number.getIm()) / mod;
    }

    public double length() {
        return Math.sqrt(im * im + re * re);
    }

    @Override
    public String toString() {
        if (this.im == 0.0) {
            if (this.re == 0.0) {
                return "0";
            } else {
                return this.re + "";
            }
        } else {
            if (this.re == 0) {
                return this.im + "i";
            } else {
                return this.re + " + " + this.im + "i";
            }
        }
    }

    public double arg() {
        return im / length();
    }

    public ComplexNumber pow(double num) {
        double newRe, newIm, modPow;
        modPow = Math.pow(length(), num);
        newRe = modPow * Math.cos(num * arg());
        newIm = modPow * Math.sin(num * arg());
        return new ComplexNumber(newRe, newIm);
    }

    public boolean equals(Object number) {
        if (number instanceof ComplexNumber) {
            return getIm() == ((ComplexNumber) number).getIm() && getRe() == ((ComplexNumber) number).getRe();
        } else {
            return false;
        }
    }

    public double getIm() {
        return im;
    }

    public double getRe() {
        return re;
    }
}
