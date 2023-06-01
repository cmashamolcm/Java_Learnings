public class Arrays {
    public static void main(String[] args) {
        int[] array = {1, 2, 3};
        System.out.println(array[0]);

        int[][] arrayOfArray = {// each row of array can have different length
                {1, 2, 3},
                {100, 200},
                {}, null,
        };
        System.out.println(arrayOfArray[1][1]);//200

        for(int[] row: arrayOfArray){//for each
            System.out.println(row[1]);// out of bound for {}.
        }
    }
}
