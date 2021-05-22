package dependencyinversion;

public class DMain {
    public static void main(String[] args) {
        Controller controller = new Controller();
        IDataAccess dao = new NoSqlDataAccesslayer();//new SqlDataAccessLayer();---only one change made it easy to plug another sub-module for dao layer.
        //This is advantage of using abstraction over concrete class.
        controller.save(dao, "Test Message");
    }
}

class Controller{
    IService service = new Service();

    void save(IDataAccess dao, String msg){
        service.save(dao, msg);
    }

}

interface IService{
    void save(IDataAccess dao, String data);
}
class Service implements IService{
    public void save(IDataAccess dao, String data){
        dao.save(data);
    }
}

interface IDataAccess{
    void save(String data);
}
class SqlDataAccessLayer implements IDataAccess{

    @Override
    public void save(String data) {
        System.out.println("Saved data to SQL");
    }
}

class NoSqlDataAccesslayer implements IDataAccess{

    @Override
    public void save(String data) {
        System.out.println("Saved data to NoSQL");
    }
}