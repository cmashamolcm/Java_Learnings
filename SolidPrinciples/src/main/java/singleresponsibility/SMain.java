package singleresponsibility;

public class SMain {
    public static void main(String[] args) {
        User user = new User();
        user.update("name", "Name_1");
        user.register();
        user.logIn();
        user.logOut();
    }
}

//all user related functionalities kept inside user.
class User{
    String name;
    String place;
    int id;
    String phone;

    public void register(){
        System.out.println("User registered.");
    }

    public void delete(){
        System.out.println("User deleted.");
    }

    public void update(String key, String value){
        System.out.println("User updated.");
    }

    public void logIn(){
        System.out.println("User logged in.");
    }

    public void logOut(){
        System.out.println("User logged out.");
    }
}