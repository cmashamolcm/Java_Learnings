package structural.adaptor;

public class PilotPenAdaptor implements Pen{

    private PilotPenFromExternalLib pen = new PilotPenFromExternalLib();

    @Override
    public void write(String msg) {
        pen.writeAsAstonaut(msg);
    }
}
