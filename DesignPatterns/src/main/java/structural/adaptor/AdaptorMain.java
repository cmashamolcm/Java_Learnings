package structural.adaptor;

public class AdaptorMain {
    public static void main(String[] args) {
        Assignment assignment = new Assignment();
        assignment.write(new BallPen(), "Hello From Adaptor Main");

        //what if we want to use PilotPen.
        assignment.write(new PilotPenAdaptor(), "Hello From Adaptor Main");
        //Here, actually pilot pen was not even a type of Pen.But with a wrapper Adaptor, we were able to use it to write.
    }
}
