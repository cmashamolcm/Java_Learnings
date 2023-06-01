public class Loops {
    public static void main(String[] args) {
        int i = 0;
        while(i<10){
            System.out.println("while: " + i);
            i++;
        }

        i = 0;
        do{
            System.out.println("do: " + i);
            i++;
        }while(i<10);// executes at least once.

        for(int j = 0; j<10; j++){// comprises everything from while loop to single line.
            System.out.println("for: " + j);
        }// assignemnt, condition check happens first. Then after each loop block, change part works.
        // Then goes for assignment and checks condition before every loop step. When fails, terminates.
        // i be come 10 on exit as assignment happens and after that condition check came.

        innerLoop();
    }

    private static void innerLoop() {
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                System.out.println("i= " + i + " j= " + j );
                if(j == 2){
                   // break;// exits from inner loop only.
                   // return;exists from both loops.
                }
            }
        }
    }
}
