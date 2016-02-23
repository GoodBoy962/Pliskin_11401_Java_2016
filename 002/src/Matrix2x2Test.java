import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

public class Matrix2x2Test {

    private static Matrix2x2 matrix1, matrix2, matrix3;
    private static double[][] arr1, arr2, arr3, arr4, arr5, arr6;

    @BeforeClass
    public static void beforeClass() {
        arr1 = new double[][]{{1.0, 2.0}, {4.0, 5.0}};
        arr2 = new double[][]{{9.0, 8.0}, {6.0, 5.0}};
        arr3 = new double[][]{{10.0, 10.0}, {10.0, 10.0}};
        arr4 = new double[][]{{8.0, 6.0}, {2.0, 0.0}};
        arr5 = new double[][]{{3.5 * 1.0, 3.5 * 2.0}, {3.5 * 4.0, 3.5 * 5.0}};
        arr6 = new double[][]{{21.0, 18.0}, {66.0, 57.0}};
        matrix1 = new Matrix2x2(arr1);
        matrix2 = new Matrix2x2(arr2);
        matrix3 = new Matrix2x2(arr3);
    }

    @Test
    public void defaultMatrixShouldInitializeNewMassive() {
        Matrix2x2 matrix2x2 = new Matrix2x2();
        Assert.assertTrue(Arrays.deepEquals(
                matrix2x2.getArr(),
                new double[2][2]
        ));
    }

    @Test
    public void stupidConstructorShouldWorkOk() {
        Assert.assertTrue(Arrays.deepEquals(
                new Matrix2x2(1.0, 2.0, 3.0, 4.0).getArr(),
                new double[][]{{1.0, 2.0}, {3.0, 4.0}}
        ));
    }

    @Test
    public void matrixConsistingOfOneElementShouldWorkOk() {
        Assert.assertTrue(Arrays.deepEquals(
                new Matrix2x2(10.0).getArr(),
                arr3
        ));
    }

    @Test
    public void getArrShouldReturnArrayWhichIsInItField() {
        Assert.assertTrue(Arrays.deepEquals(
                matrix2.getArr(),
                arr2
        ));
    }

    @Test
    public void afterAddingMatrixFinalMatrixShouldBeOk() {
        Assert.assertTrue(Arrays.deepEquals(
                matrix1.add(matrix2).getArr(),
                arr3
        ));
    }

    @Test
    public void afterAddingMatrixToSelfFinalMatrixShouldBeOk() {
        Matrix2x2 matrix = new Matrix2x2(new double[][]{{1.0, 2.0}, {4.0, 5.0}});
        matrix.add2(matrix2);
        Assert.assertTrue(Arrays.deepEquals(
                matrix.getArr(),
                arr3
        ));
    }

    @Test
    public void afterSubbingMatrixFinalMatrixShouldBeOk() {
        Assert.assertTrue(Arrays.deepEquals(
                matrix2.sub(matrix1).getArr(),
                arr4
        ));
    }

    @Test
    public void afterSubbingMatrixToSelfFinalMatrixShouldBeOk() {
        Matrix2x2 matrix = new Matrix2x2(new double[][]{{9.0, 8.0}, {6.0, 5.0}});
        matrix.sub2(matrix1);
        Assert.assertTrue(Arrays.deepEquals(
                matrix.getArr(),
                arr4
        ));
    }

    @Test
    public void afterMultiplyOnNumberOk() {
        Assert.assertTrue(Arrays.deepEquals(
                matrix1.multNumber(3.5).getArr(),
                arr5
        ));
    }

    @Test
    public void afterMultiplySelfOnNumberOk() {
        Matrix2x2 matrix = new Matrix2x2(new double[][]{{1.0, 2.0}, {4.0, 5.0}});
        matrix.multNumber2(3.5);
        Assert.assertTrue(Arrays.deepEquals(
                matrix.getArr(),
                arr5
        ));
    }

    @Test
    public void multiplyMatrixShouldWork() {
        Assert.assertTrue(Arrays.deepEquals(
                matrix1.mult(matrix2).getArr(),
                arr6
        ));
    }

    @Test
    public void multiplySelfOnMatrixShouldWork() {
        Matrix2x2 matrix = new Matrix2x2(new double[][]{{1.0, 2.0}, {4.0, 5.0}});
        matrix.mult2(matrix2);
        Assert.assertTrue(Arrays.deepEquals(
                matrix.getArr(),
                arr6
        ));
    }

    @Test
    public void detShouldBeOk() {
        Assert.assertEquals(matrix1.det(), -3.0, 1e-9);
    }

    @Test
    public void transponShouldWork() {
        Matrix2x2 matrix = new Matrix2x2(new double[][]{{1.0, 2.0}, {4.0, 5.0}});
        matrix.transpon();
        Assert.assertTrue(Arrays.deepEquals(
                matrix.getArr(),
                new double[][]{{1.0, 4.0}, {2.0, 5.0}}
        ));
    }

    @Test
    public void inverseMatrixShouldWork() {
        Matrix2x2 matrix = new Matrix2x2(new double[][]{{1.0, 2.0}, {3.0, 4.0}});
        try {
            Assert.assertTrue(Arrays.deepEquals(
                    matrix.inverseMatrix().getArr(),
                    new double[][]{{-2.0, 1.0}, {1.5, -0.5}}
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void equivalentDiagonalMatrixShouldBeOk() {
        try {
            Assert.assertTrue(Arrays.deepEquals(
                    matrix1.equivalentDiagonal().getArr(),
                    new double[][]{{1.0, 0.0}, {0.0, -3.0}}
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void matrixShouldBeGoodMultiplyOnVector() {
        Vector2D vector = matrix1.multVector(new Vector2D(3.0, 4.0));
        Assert.assertTrue(vector.getX() == 11.0 && vector.getY() == 32.0);
    }

}
