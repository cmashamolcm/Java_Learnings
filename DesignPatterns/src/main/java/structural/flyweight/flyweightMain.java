package structural.flyweight;

import java.util.HashMap;
import java.util.Map;

public class flyweightMain {
    public static void main(String[] args) {
        AngryBirdGame game = new AngryBirdGame();
        game.run("SLOW", "user1");
        game.run("FAST", "user2");
    }
}

class AngryBirdGame{
    private Map<String, AngryBird> angryBirdMap;
    public AngryBirdGame(){
        angryBirdMap = new HashMap<>();
        angryBirdMap.put("SLOW", new SlowAngryBird());
    }

    public void run(String type, String userName){
        if (angryBirdMap.containsKey(type)){
            System.out.println("Object exists");
            angryBirdMap.get(type).id = type + "12345";
        }else{
            System.out.println("Object creating as not available");
            switch (type){
                case "SLOW"-> {
                    AngryBird slow = new SlowAngryBird();
                    slow.id = type + "12345";
                    slow.name = userName + "_slow_bird";
                    angryBirdMap.put(type, slow);
                }
                case "FAST"->{
                    AngryBird fast = new FastAngryBird();
                    fast.id = type + "12345";
                    fast.name = userName + "_fast_bird";
                    angryBirdMap.put(type, fast);
                }
            }
        }
        angryBirdMap.get(type).perform();
    }
}


class SlowAngryBird extends AngryBird{
    public SlowAngryBird(){//intrinsic
        color = "green";
        speed = "10km";
        type = "SLOW";
    }

    @Override
    public void perform() {
        System.out.println("Performs by Slow " + id);
    }
}

class FastAngryBird extends AngryBird{
    public FastAngryBird(){
        color = "red";
        speed = "60km";
        type = "FAST";
    }

    @Override
    public void perform() {
        System.out.println("Performs by Fast " + id);
    }
}

abstract class AngryBird{
    String id;
    String name;
    protected String color;
    protected String speed;
    protected String type;

    public abstract void perform();
}