import java.util.ArrayList;

public abstract class  User {

    String userid,name,password;


    public User(String userid, String name, String password) {
        this.userid = userid;
        this.name = name;
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password= password;
    }

    abstract public ArrayList<BorrowedItem> getBorrowedItems();
    
    abstract public void addBorrowedItem(BorrowedItem borrowedItem);
    
    abstract public void removeBorrowedItem(BorrowedItem borrowedItem);
    
}
