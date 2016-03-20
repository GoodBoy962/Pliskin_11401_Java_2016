package com.pliskin;

import org.springframework.stereotype.Component;

@Component
public class Matrix2x2 {

    private double[][] arr;

    public Matrix2x2() {
        this(new double[][]{{0.0, 0.0}, {0.0, 0.0}});
    }

    public Matrix2x2(double[][] arr) {
        this.arr = arr;
    }

    public Matrix2x2(double a11, double a12, double a21, double a22) {
        this(new double[][]{{a11, a12}, {a21, a22}});
    }

    public Matrix2x2(double a) {
        this(new double[][]{{a, a}, {a, a}});
    }

    public Matrix2x2 add(Matrix2x2 matrix2) {
        double[][] res = new double[2][2];
        double[][] arr2 = matrix2.getArr();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                res[i][j] = arr[i][j] + arr2[i][j];
            }
        }
        return new Matrix2x2(res);
    }

    public void add2(Matrix2x2 matrix2) {
        double[][] arr2 = matrix2.getArr();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] += arr2[i][j];
            }
        }
    }

    public Matrix2x2 sub(Matrix2x2 matrix2) {
        double res[][] = new double[2][2];
        double arr2[][] = matrix2.getArr();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                res[i][j] = arr[i][j] - arr2[i][j];
            }
        }
        return new Matrix2x2(res);
    }

    public void sub2(Matrix2x2 matrix2) {
        double[][] arr2 = matrix2.getArr();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] -= arr2[i][j];
            }
        }
    }

    public Matrix2x2 multNumber(double number) {
        double res[][] = new double[2][2];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                res[i][j] = arr[i][j] * number;
            }
        }
        return new Matrix2x2(res);
    }

    public void multNumber2(double number) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] *= number;
            }
        }
    }

    public Matrix2x2 mult(Matrix2x2 matrix2) {
        double res[][] = new double[2][2];
        double arr2[][] = matrix2.getArr();

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    res[i][j] += arr[i][k] * arr2[k][j];
                }
            }
        }

        return new Matrix2x2(res);
    }

    public void mult2(Matrix2x2 matrix2) {
        double res[][] = new double[2][2];
        double arr2[][] = matrix2.getArr();

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    res[i][j] += arr[i][k] * arr2[k][j];
                }
            }
        }

        this.arr = res;
    }

    public double det() {
        return arr[0][0] * arr[1][1] - arr[1][0] * arr[0][1];
    }

    public void transpon() {
        double swap = arr[1][0];
        arr[1][0] = arr[0][1];
        arr[0][1] = swap;
    }

    public Matrix2x2 inverseMatrix() {
        double res[][] = new double[2][2];
        double det = det();
        res[0][0] = arr[1][1];
        res[1][0] = -arr[1][0];
        res[0][1] = -arr[0][1];
        res[1][1] = arr[0][0];
        double div = 1 / det;
        return new Matrix2x2(res).multNumber(div);
    }

    public Matrix2x2 equivalentDiagonal() throws Exception {
        double res[][] = new double[2][2];
        res[1][0] = 0.0;
        res[0][1] = 0.0;
        res[0][0] = arr[0][0];
        res[1][1] = arr[1][1] - arr[1][0] * arr[0][1] / arr[0][0];
        if (res[1][1] == 0) {
            throw new Exception("no diagonal matrix");
        }
        return new Matrix2x2(res);
    }

    public Vector2D multVector(Vector2D vector) {
        return new Vector2D(
                arr[0][0] * vector.getX() + arr[0][1] * vector.getY(),
                arr[1][0] * vector.getX() + arr[1][1] * vector.getY()
        );
    }


    public double[][] getArr() {
        return arr;
    }

    public void setArr(double[][] arr) {
        this.arr = arr;
    }
}
