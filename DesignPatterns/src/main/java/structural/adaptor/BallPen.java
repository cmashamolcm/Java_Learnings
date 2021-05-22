package structural.adaptor;

public class BallPen implements Pen{
    @Override
    public void write(String msg) {
        System.out.println("Ball pen: " + msg);
    }
}
