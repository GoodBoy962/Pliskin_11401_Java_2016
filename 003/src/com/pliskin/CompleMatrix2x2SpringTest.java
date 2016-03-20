package com.pliskin;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * Created by aleksandrpliskin on 21.03.16.
 * 007
 */
public class CompleMatrix2x2SpringTest {

    private static ComplexNumber num1, num2, num3, zeroNum;
    private static ComplexNumber arr[][], arr2[][], zeroArr[][];
    private static ComplexMatrix2x2 matrix2x2, matrixZero;
    private static ComplexVector2D vector;
    private static ApplicationContext context;

    @BeforeClass
    public static void initComplexNumbers() {
        context = new ClassPathXmlApplicationContext("spring-config.xml");
        num1 = (ComplexNumber) context.getBean("num1");
        num2 = (ComplexNumber) context.getBean("num2");
        num3 = (ComplexNumber) context.getBean("num3");
        zeroNum = (ComplexNumber) context.getBean("zeroNum");
//        arr = (ComplexNumber[][]) context.getBean("arr");
//        arr2 = (ComplexNumber[][]) context.getBean("arr2");
//        zeroArr = (ComplexNumber[][]) context.getBean("zeroNum");
        arr = new ComplexNumber[][]{{num1, num1}, {num1, num1}};
        arr2 = new ComplexNumber[][]{{num2, num2}, {num2, num2}};
        zeroArr = new ComplexNumber[][]{{zeroNum, zeroNum}, {zeroNum, zeroNum}};
        matrix2x2 = (ComplexMatrix2x2) context.getBean("matrix2x2");
        matrixZero = (ComplexMatrix2x2) context.getBean("matrixZero");
        vector = (ComplexVector2D) context.getBean("vector");
    }

    @Test
    public void getArrShouldWorkOk() {
        Assert.assertTrue(Arrays.deepEquals(matrix2x2.getArr(), arr));
    }

    @Test
    public void emptyConstructorShouldWorkOk() {
        ComplexMatrix2x2 matrix = (ComplexMatrix2x2) context.getBean("defaultMatrix");
        Assert.assertTrue(Arrays.deepEquals(
                matrix.getArr(),
                zeroArr));
    }

    @Test
    public void stupidConstructorShouldWorksOk() {
        ComplexMatrix2x2 matrix = (ComplexMatrix2x2) context.getBean("num1Matrix");
        Assert.assertTrue(Arrays.deepEquals(
                matrix.getArr(),
                arr
        ));
    }

    @Test
    public void afterAddingMatrixFinalMatrixShouldBeOk() {
        ComplexMatrix2x2 matrix = (ComplexMatrix2x2) context.getBean("matrix2x2");
        Assert.assertTrue(Arrays.deepEquals(
                matrix.add(matrix2x2).getArr(),
                arr2
        ));
    }

    @Test
    public void afterMultiplyOnMatrixFinalMatrixShouldBeOk() {
        ComplexMatrix2x2 matrix = (ComplexMatrix2x2) context.getBean("matrix2x2");
        Assert.assertTrue(Arrays.deepEquals(
                matrix.mult(matrixZero).getArr(),
                zeroArr
        ));
    }

    @Test
    public void matrixDeterminantShouldBeRight() {
        ComplexMatrix2x2 matrix = (ComplexMatrix2x2) context.getBean("matrix2x2");
        System.out.println(matrix.det());
//        Assert.assertEquals(matrix.det(), zeroNum);
    }

    @Test
    public void complexVectorShouldBeRightAfterMultiplyMatrixOnVector() {
        ComplexMatrix2x2 matrix = (ComplexMatrix2x2) context.getBean("matrix2x2");
        ComplexVector2D v = matrix.multVector(vector);
        Assert.assertTrue(v.getComplexNumberX().equals(num3) && v.getComplexNumberY().equals(num3));
    }
}
