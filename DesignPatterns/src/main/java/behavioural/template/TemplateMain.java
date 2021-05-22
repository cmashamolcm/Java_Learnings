package behavioural.template;
//dynamically based on created object, a few methods changed, a few are from parent.
public class TemplateMain {
    public static void main(String[] args) {
        HouseTemplate woodenTemplate = new WoodenHouse();
        woodenTemplate.buildHouse();

        HouseTemplate concreteTemplate = new ConcreteHouse();
        concreteTemplate.buildHouse();
    }
}


abstract class HouseTemplate{

    public void buildHouse(){
        buildBase();
        buildWall();
        buildRoof();
    }

    protected void buildBase(){
        System.out.println("Base done");
    }

    protected void buildWall(){
        System.out.println("Wall done");
    }

    protected void buildRoof() {//if a method is private in parent and public in child, then also it won't go to child if called by parent.method().
        System.out.println("Roof done");
    }
}

class WoodenHouse extends HouseTemplate{
    protected void buildWall(){
        System.out.println("Wooden Wall done");
    }
}

class ConcreteHouse extends HouseTemplate{
    protected void buildRoof() {
        System.out.println("Cement Roof done");
    }
}