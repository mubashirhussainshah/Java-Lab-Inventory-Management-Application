import java.time.LocalDateTime;
import java.util.ArrayList;

public class Student {
    private String name;
    private String userId;
    private String password;
    private String phoneNumber;
    private ArrayList<BorrowedItem> borrowedItems;
    StudentGUI gui;
    
    public Student(String userId, String name , String password, String phoneNumber) {
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.borrowedItems = new ArrayList<BorrowedItem>();

    }

    public void setGUI(StudentGUI gui){
        this.gui = gui;
    }

    public StudentGUI getGUI(){
        return gui;
    }
    
    public String getName() {
        return name;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public ArrayList<BorrowedItem> getBorrowedItems() {
        return borrowedItems;
    }
    
    public void addBorrowedItem(BorrowedItem borrowedItem) {
        borrowedItems.add(borrowedItem);
    }
    
    public void removeBorrowedItem(BorrowedItem borrowedItem) {
        borrowedItems.remove(borrowedItem);
    }




    
    public void renewBorrowedItem(BorrowedItem borrowedItem, LocalDateTime newDeadlineDate) {
        for (BorrowedItem item : borrowedItems) {
            if (item.equals(borrowedItem)) {
                item.setReturnDate(newDeadlineDate);
            }
        }
    }
}
