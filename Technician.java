
import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.DefaultTableModel;


public class Technician extends User implements Inventory{

    private static final String TECHNICIAN_NAME = "tech";
    private static final String TECHNICIAN_PASSWORD = "t2023";
    
    private ArrayList<Item> inventory;
    private ArrayList<BorrowedItem> borrowedItems;
    private ArrayList<Student> borrowers;
    private ArrayList<Requests> requestList;
    

    // Constructor
    public Technician( String name, String password) {
        super("001",name,password);  
        inventory = new ArrayList<>();
        borrowedItems = new ArrayList<>();
        borrowers = new ArrayList<>();
        requestList = new ArrayList<>();

        //put some dummy data in inventory
        inventory.add(new Item("Laptop", "Dell", 1000, true, 5));
        inventory.add(new Item("Projector", "Sony", 2000, true, 2));
        inventory.add(new Item("Mouse", "Logitech", 50, false, 10));
        inventory.add(new Item("Keyboard", "Logitech", 100, false, 10));
        inventory.add(new Item("Monitor", "Dell", 500, true, 5));
    }


    // Method to add item to inventory
    public void addItem(Item item) {
        inventory.add(item);
    }

    // Method to remove item from inventory
    public void removeItem(Item item) {
        inventory.remove(item);
    }

    // Method to edit item in inventory
    public void editItem(Item oldItem, Item newItem) {
        int index = inventory.indexOf(oldItem);
        if (index != -1) {
            inventory.set(index, newItem);
        }
    }

    public ArrayList<Item> getInventory(){
        return inventory;
    }

    // Method to display inventory
    public void displayInventory(DefaultTableModel model) {
        model.setRowCount(0);
        for (Item item : inventory) {
            String type;
            if (item.isToReturn()) 
                type = "Equipment";
            else 
                type = "Consumable";
            model.addRow(new Object[]{item.getName(), item.getModel(), item.getAvailableQuantity(), item.getEstimatedValue(),type });
        }
    }

    // Method to search for an item by name
    public Item searchItem(String name) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<Item> searchItems(String name) {
        ArrayList<Item> searchResults = new ArrayList<Item>();
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(name)) {
                searchResults.add(item);
            }
        }
        return searchResults;
    }
    

    // Method to get borrowed items
    public ArrayList<BorrowedItem> getBorrowedItems() {
        return borrowedItems;
    }

    // Method to add borrowed item to the list
    public void addBorrowedItem(BorrowedItem borrowedItem) {
        borrowedItems.add(borrowedItem);
    }

    // Method to remove borrowed item from the list
    public void removeBorrowedItem(BorrowedItem borrowedItem) {
        borrowedItems.remove(borrowedItem);
    }

    // Method to display borrowed items
    public void displayBorrowedItems(DefaultTableModel model) {
        model.setRowCount(0);
        for (BorrowedItem borrowedItem : borrowedItems) {

            if(!borrowedItem.getItem().isToReturn()) borrowedItem.setReturnDate(null);
            model.addRow(new Object[]{borrowedItem.getItem().getName(), borrowedItem.getQuantity(), borrowedItem.getBorrowDate(), 
                    borrowedItem.getReturnDate(), borrowedItem.getBorrowedBy().getName(), borrowedItem.getPenalty()});
        }
    }

    // Method to display borrowed items
    public void displayBorrowedItems(DefaultTableModel model, Student s) {
        
        model.setRowCount(0);
        for (BorrowedItem borrowedItem : borrowedItems) {
            if(s.getName().equals(borrowedItem.getBorrowedBy().getName())){
            model.addRow(new Object[]{borrowedItem.getItem().getName(), borrowedItem.getQuantity(), borrowedItem.getBorrowDate(), 
                    borrowedItem.getReturnDate(), borrowedItem.getBorrowedBy().getName(), borrowedItem.getPenalty()});
            }
        }
    }



    // Method to check for expired borrowed items
    public ArrayList<BorrowedItem> allExpiredItems() {
        ArrayList<BorrowedItem> expired = new ArrayList<>(); 
        for (BorrowedItem borrowedItem : borrowedItems) {
            if(borrowedItem.expiredItem()) 
            expired.add(borrowedItem);
        }
        return expired;
    }

    public void displayRequests(DefaultTableModel requestTableModel) {
        // Clear the existing data from the table model
        requestTableModel.setRowCount(0);
    
        // Loop through each request in the store's request list
        for (Requests request : requestList) {
            // Create a new row to add to the table model
            Object[] row = new Object[3];
            row[0] = request.getItem().getItem().getName();
            row[1] = request.getStudent().getName();
            row[2] = request.getRequestedDate();
            requestTableModel.addRow(row);
        }
    }
    

    public ArrayList<Requests> getRequests() {
        return requestList;
    }
    

    public void addRequest(Student student, BorrowedItem item, Date requestedDate) {
        // Create a new request and add it to the request list
        Requests newRequest = new Requests(student, item, requestedDate);
        requestList.add(newRequest);
    }


    public void addBorrower(Student s){
        borrowers.add(s);
    }
    
    

    
    //get all the students who borrowed before 
    public ArrayList<Student> getAllBorrowers() {
        return borrowers;
    }
    

    // Method to search for a student by id
    public Student searchStudent(String userId) {
        borrowers = getAllBorrowers();
        for (Student student : borrowers) {
        if (student.getUserId().equals(userId)) {
        return student;
        }
        }
        return null; // student not found
        }

    // Method to sign in
    public boolean signIn(String username, String password) {
        if (username.equals(TECHNICIAN_NAME) && password.equals(TECHNICIAN_PASSWORD)) {
                return true;
            }
        return false;
        }
               
    //Method to log out
    public void logout() {
            // Clear the technician object
            this.name = null;
            this.password = null;
            inventory.clear();
            borrowedItems.clear();
            borrowers.clear();
        }   
 
}