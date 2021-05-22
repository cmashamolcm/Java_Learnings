package creational.singleton;

import java.io.Serializable;
import java.security.SecureRandom;

//Thread safe, reflection safe
//It cannot inherit classes, only interfaces allowed. So, it is rigid
public enum Enums implements Serializable {
    instance;

    public static void print(){
        System.out.println("Enum");
    }
}
