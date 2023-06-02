public class Enums {
    public static void main(String[] args) {
        System.out.println(Status.SUCCESS.ordinal());
    }

}

//class EnumChild implements Status{
//
//}
interface EmumInter{

}
enum Status implements EmumInter{
    SUCCESS(1),
    FAILURE(0);
    private int code;
    Status(int code){
        this.code =code;
    }


}
