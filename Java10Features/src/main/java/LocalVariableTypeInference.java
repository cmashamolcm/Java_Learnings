import java.util.ArrayList;

public class LocalVariableTypeInference {
    //private var classLevel; // not allowed var here

    public static void main(String[] args) {
        //var local;//mandatory to initialize. Else error comes.
        var local = new ArrayList();
        //It will give ArrayList. Not simply List.
        System.out.println(local.getClass());
        //local = 10; //reassign not allowed
        //Lambda cannot be assigned to it as it's target type need to specify explicitly.
        //var array = (String p)->p.length();
        var obj = new Object(){};
        //This will not give Object. But it will show name of an anonymous class. LocalVariableTypeInference$1
        System.out.println(obj.getClass());
    }
}
