public class ComplexMatrix2x2 {

    private ComplexNumber arr[][];

    private static ComplexNumber zeroNum = new ComplexNumber();

    public ComplexMatrix2x2(ComplexNumber[][] arr) {
        this.arr = arr;
    }

    public ComplexMatrix2x2() {
        this(new ComplexNumber[][]{{zeroNum, zeroNum}, {zeroNum, zeroNum}});
    }

    public ComplexMatrix2x2(ComplexNumber num1, ComplexNumber num2,
                            ComplexNumber num3, ComplexNumber num4) {
        arr = new ComplexNumber[][]{{num1, num2}, {num3, num4}};
    }

    public ComplexMatrix2x2 add(ComplexMatrix2x2 matrix2) {
        ComplexNumber[][] arr2 = matrix2.getArr();
        ComplexNumber[][] resArr = new ComplexNumber[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                resArr[i][j] = arr[i][j].add(arr2[i][j]);
            }
        }
        return new ComplexMatrix2x2(resArr);
    }

    public ComplexMatrix2x2 mult(ComplexMatrix2x2 matrix2) {
        ComplexNumber[][] arr2 = matrix2.getArr();
        ComplexNumber[][] resArr = new ComplexNumber[][]{
                {new ComplexNumber(), new ComplexNumber()},
                {new ComplexNumber(), new ComplexNumber()}
        };

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    resArr[i][j] = arr[i][k].mult(arr2[k][j]).add(resArr[i][j]);
                }
            }
        }
        return new ComplexMatrix2x2(resArr);
    }

    public ComplexNumber det() {
        return (arr[0][0].mult(arr[1][1])).div((arr[0][1].mult(arr[1][0])));
    }

    public ComplexVector2D multVector(ComplexVector2D vector) {
        return new ComplexVector2D(
                (arr[0][0].mult(vector.getComplexNumberX())).add(arr[0][1].mult(vector.getComplexNumberY())),
                (arr[1][0].mult(vector.getComplexNumberX())).add(arr[1][1].mult(vector.getComplexNumberY()))
        );
    }

    public ComplexNumber[][] getArr() {
        return arr;
    }


}
