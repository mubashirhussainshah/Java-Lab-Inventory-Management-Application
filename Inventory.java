import java.util.ArrayList;

public interface Inventory {
    public void addItem(Item item);
    public void removeItem(Item item);
    public void editItem(Item old, Item newItem);
    public ArrayList<Item> getInventory();
    public Item searchItem(String name);
    public ArrayList<Item> searchItems(String name);
    public void addBorrowedItem(BorrowedItem item);
    public void removeBorrowedItem(BorrowedItem item);
    public ArrayList<BorrowedItem> getBorrowedItems();
}
