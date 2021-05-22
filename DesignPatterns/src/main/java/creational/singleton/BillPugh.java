package creational.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

//On demand creation.
//thread safe
//got rid of 15 version issues.
//Mostly used as explicit synchronization not needed
public class BillPugh {
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        BillPughSingleton.getInstance().print();

        //break by reflection - hashcode shows different objects
        Constructor<?> constructor = Arrays.stream(BillPughSingleton.class.getDeclaredConstructors()).findFirst().get();
        constructor.setAccessible(true);
        BillPughSingleton inst = (BillPughSingleton) constructor.newInstance();
        inst.print();

    }
}

class BillPughSingleton {

    private BillPughSingleton() {
        System.out.println("Constructor");
    }

    public static BillPughSingleton getInstance() {
        return BillPughHelper.instance;
    }

    public void print() {
        System.out.println(this.hashCode() + " Bill Pugh Helper");
    }

    public class BillPughHelper {
        private static final BillPughSingleton instance = new BillPughSingleton();

        private BillPughHelper() {
        }
    }
}
