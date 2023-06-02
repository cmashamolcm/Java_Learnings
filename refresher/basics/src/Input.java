import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Input {
    public static void main(String[] args) throws IOException {
        //int i = System.in.read();// gives as ascii. a=97, A=63
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//br is resource. close it.
        //int i = Integer.parseInt(br.readLine());

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        System.out.println(i);
        //br.close();
        testReturn();
    }

    static void testReturn(){
        try{
            System.out.println("try");
            //System.exit(0);// here, process terminates and no finally call happens
            //return;
           //1 int i = 1/0;
           //1 return;
        }catch(ArithmeticException e){
            System.out.println("Exception");
        }finally {
            System.out.println("Finally block");
        }
    }

    static void test() throws IOException {
        BufferedReader br = null;
        try{
           int i = 1/0;
            br = new BufferedReader(new InputStreamReader(System.in));
            int x = Integer.parseInt(br.readLine());
        }catch(ArithmeticException e){
            System.out.println("Exception");
        } catch (IOException e) {
            System.out.println("Read failed");
        } finally {// finally helps to close resources, cleanup etc. even if exception happens.
            System.out.println("Safe exit");
            br.close();
        }

    }

    static void testWithResource() throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));){// here, on exit from try, this will be closed even if exception happens.
            int i = 1/0;
            int x = Integer.parseInt(br.readLine());
        }catch(ArithmeticException e){
            System.out.println("Exception");
        } catch (IOException e) {
            System.out.println("Read failed");
        } finally {// finally helps to close resources, cleanup etc. even if exception happens.
            System.out.println("Safe exit");
        }

    }

}
