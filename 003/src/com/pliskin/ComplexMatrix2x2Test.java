package com.pliskin;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ComplexMatrix2x2Test {

    private static ComplexNumber num1, num2, num3, zeroNum;
    private static ComplexNumber arr[][], arr2[][], zeroArr[][];
    private static ComplexMatrix2x2 matrix2x2, matrixZero;
    private static ComplexVector2D vector;
    private static ApplicationContext context;

    @BeforeClass
    public static void initComplexNumbers() {
        context = new ClassPathXmlApplicationContext("spring-config.xml");
        num1 = mock(ComplexNumber.class);
        num2 = mock(ComplexNumber.class);
        num3 = mock(ComplexNumber.class);
        zeroNum = mock(ComplexNumber.class);

        when(num1.getIm()).thenReturn(2.0);
        when(num1.getRe()).thenReturn(1.0);
        when(num2.getIm()).thenReturn(4.0);
        when(num2.getRe()).thenReturn(2.0);
        when(num3.getIm()).thenReturn(4.0);
        when(num3.getRe()).thenReturn(-3.0);
        when(zeroNum.getRe()).thenReturn((double) 0);
        when(zeroNum.getIm()).thenReturn((double) 0);

        when(num1.length()).thenReturn(Math.abs(5.0));
        when(num2.length()).thenReturn(Math.abs(20.0));
        when(num3.length()).thenReturn(5.0);
        when(zeroNum.length()).thenReturn((double) 0);

        arr = new ComplexNumber[][]{{num1, num1}, {num1, num1}};
        arr2 = new ComplexNumber[][]{{num2, num2}, {num2, num2}};
        zeroArr = new ComplexNumber[][]{{zeroNum, zeroNum}, {zeroNum, zeroNum}};

        when(num1.add(num1)).thenReturn(num2);
        when(num1.mult(num1)).thenReturn(num3);

        when(num1.add(zeroNum)).thenReturn(num1);
        when(num1.mult(zeroNum)).thenReturn(zeroNum);

        when(num2.add(zeroNum)).thenReturn(num2);
        when(num2.mult(zeroNum)).thenReturn(zeroNum);

        when(num3.add(zeroNum)).thenReturn(num3);
        when(num3.sub(num3)).thenReturn(zeroNum);
        when(num3.sub(zeroNum)).thenReturn(num3);

        when(zeroNum.add(num1)).thenReturn(num1);
        when(zeroNum.add(num2)).thenReturn(num2);
        when(zeroNum.add(num3)).thenReturn(num3);
        when(zeroNum.add(zeroNum)).thenReturn(zeroNum);
        when(zeroNum.add(new ComplexNumber())).thenAnswer(new Answer<ComplexNumber>() {

            @Override
            public ComplexNumber answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] args = invocationOnMock.getArguments();
                return (ComplexNumber) args[0];
            }
        });
        when(zeroNum.mult(anyObject())).thenReturn(zeroNum);

        matrix2x2 = mock(ComplexMatrix2x2.class);
        when(matrix2x2.getArr()).thenReturn(arr);

        matrixZero = mock(ComplexMatrix2x2.class);
        when(matrixZero.getArr()).thenReturn(zeroArr);

        vector = mock(ComplexVector2D.class);

        when(vector.getComplexNumberX()).thenReturn(num1);
        when(vector.getComplexNumberY()).thenReturn(zeroNum);
    }

    @Test
    public void getArrShouldWorkOk() {
        ComplexMatrix2x2 matrix = new ComplexMatrix2x2(arr);
        Assert.assertTrue(Arrays.deepEquals(matrix.getArr(), arr));
    }

    @Test
    public void emptyConstructorShouldWorkOk() {
        ComplexMatrix2x2 matrix = new ComplexMatrix2x2();
        Assert.assertTrue(Arrays.deepEquals(
                matrix.getArr(),
                zeroArr));
    }

    @Test
    public void stupidConstructorShouldWorksOk() {
        ComplexMatrix2x2 matrix = new ComplexMatrix2x2(num1, num1, num1, num1);
        Assert.assertTrue(Arrays.deepEquals(
                matrix.getArr(),
                arr
        ));
    }

    @Test
    public void afterAddingMatrixFinalMatrixShouldBeOk() {
        ComplexMatrix2x2 matrix = new ComplexMatrix2x2(arr);
        Assert.assertTrue(Arrays.deepEquals(
                matrix.add(matrix2x2).getArr(),
                arr2
        ));
    }

    @Test
    public void afterMultiplyOnMatrixFinalMatrixShouldBeOk() {
        ComplexMatrix2x2 matrix = new ComplexMatrix2x2(arr);
        Assert.assertTrue(Arrays.deepEquals(
                matrix.mult(matrixZero).getArr(),
                zeroArr
        ));
    }

    @Test
    public void matrixDeterminantShouldBeRight() {
        ComplexMatrix2x2 matrix = new ComplexMatrix2x2(arr);
        Assert.assertEquals(matrix.det(), zeroNum);
    }

    @Test
    public void complexVectorShouldBeRightAfterMultiplyMatrixOnVector() {
        ComplexMatrix2x2 matrix = new ComplexMatrix2x2(arr);
        ComplexVector2D v = matrix.multVector(vector);
        Assert.assertTrue(v.getComplexNumberX().equals(num3) && v.getComplexNumberY().equals(num3));
    }

}
