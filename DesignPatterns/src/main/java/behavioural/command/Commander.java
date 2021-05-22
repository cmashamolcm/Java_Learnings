package behavioural.command;

public class Commander {
    public static void main(String[] args) {
        Command openFileCommand = new Command(new Mac());
        openFileCommand.execute("MayFile.txt");
    }
}

class Command{
    FileSystem fs;
    Command(FileSystem fs){
        this.fs = fs;
    }
    public void execute(String fileName){
        fs.openFile(fileName);
    }
}

interface FileSystem{
    void openFile(String fileName);
}


class Mac implements FileSystem{

    @Override
    public void openFile(String fileName) {
        System.out.println("Opened file " + fileName + " in mac");
    }
}

class Windows implements FileSystem{

    @Override
    public void openFile(String fileName) {
        System.out.println("Opened file " + fileName + " in windows");
    }
}