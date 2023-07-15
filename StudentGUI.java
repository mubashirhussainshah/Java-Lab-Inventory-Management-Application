import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class StudentGUI extends JFrame {
    
    private JPanel searchPanel, cartPanel, checkoutPanel, borrowedPanel;
    private JTextField searchField;
    private JButton searchButton, checkoutButton;
    private JTable inventoryTable, cartTable, borrowedTable;
    private Item selectedItem;
    //private Item cartSelectedItem;
    Technician t;
    Student s;

    ArrayList <Item> Cart;
    ArrayList<BorrowedItem> Borrowed;
    
    DefaultTableModel inventoryTableModel ; 
    DefaultTableModel cartTableModel;
    DefaultTableModel borrowedTableModel;
    
    
    public StudentGUI(Technician t,Student s) {
        this.s = s;
        s.setGUI(this);
        t.addBorrower(s);
        this.t = t;
        Cart = new ArrayList<>();
        Borrowed = s.getBorrowedItems();
        setTitle("Student Borrowing System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create search panel
searchPanel = new JPanel(new BorderLayout());
searchPanel.setBorder(BorderFactory.createTitledBorder("Search Items"));
searchField = new JTextField();
searchField.setBackground(Color.ORANGE);
searchButton = new JButton("Search");
searchButton.setBackground(Color.yellow);
JPanel searchFieldPanel = new JPanel(new BorderLayout());
searchFieldPanel.add(searchField, BorderLayout.CENTER);
searchFieldPanel.add(searchButton, BorderLayout.EAST);
searchPanel.add(searchFieldPanel, BorderLayout.NORTH);

inventoryTableModel = new DefaultTableModel();

// Add table columns
inventoryTableModel.addColumn("Name");
inventoryTableModel.addColumn("Model");
inventoryTableModel.addColumn("Quantity");
inventoryTableModel.addColumn("Value");
inventoryTableModel.addColumn("Type");

t.displayInventory(inventoryTableModel);
inventoryTable = new JTable(inventoryTableModel);
ListSelectionModel selectionModel = inventoryTable.getSelectionModel();

// Add selection listener to the table
selectionModel.addListSelectionListener((ListSelectionListener) (e) -> {
    int selectedRow = inventoryTable.getSelectedRow();
    if (selectedRow != -1) {
        selectedItem = t.getInventory().get(selectedRow);
    }
});

JScrollPane itemScrollPane = new JScrollPane(inventoryTable);

searchPanel.add(itemScrollPane, BorderLayout.CENTER);

// Create quantity panel
JPanel quantityPanel = new JPanel(new FlowLayout());
quantityPanel.add(new JLabel("Quantity:"));
JTextField quantityField = new JTextField(5); // create text field for quantity input
quantityField.setBackground(Color.ORANGE);
quantityPanel.add(quantityField);
JButton addToCartButton = new JButton("Add to Cart");
addToCartButton.setBackground(Color.yellow);


quantityPanel.add(addToCartButton);
searchPanel.add(quantityPanel, BorderLayout.SOUTH);

// Create cart panel
cartPanel = new JPanel(new BorderLayout());
cartPanel.setBorder(BorderFactory.createTitledBorder("Cart"));
cartTableModel = new DefaultTableModel();

// Add table columns
cartTableModel.addColumn("Item Name");
cartTableModel.addColumn("Model");
cartTableModel.addColumn("Quantity");
cartTableModel.addColumn("Estimated Value");


// Add data to table model
Object[] cartRow = new Object[4];
for (Item item : Cart) {
    cartRow[0] = item.getName();
    cartRow[1] = item.getModel();
    cartRow[2] = item.getAvailableQuantity();
    cartRow[3] = item.getEstimatedValue();
    cartTableModel.addRow(cartRow);
}

cartTable = new JTable(cartTableModel);
//ListSelectionModel cartSelectionModel = cartTable.getSelectionModel();
// cartSelectionModel.addListSelectionListener((ListSelectionListener) (e) -> {
//     int selectedRow = cartTable.getSelectedRow();
//     if (selectedRow != -1) {
//         // Perform some action when a row in the cart table is selected
//         cartSelectedItem = Cart.get(selectedRow);
//     }
// });


JScrollPane cartScrollPane = new JScrollPane(cartTable);

// Add cart button to cart panel
JPanel cartButtonPanel = new JPanel(new FlowLayout());
JButton removeButton = new JButton("Remove");
removeButton.setBackground(Color.yellow);
cartButtonPanel.add(removeButton);
cartPanel.add(cartScrollPane, BorderLayout.CENTER);
cartPanel.add(cartButtonPanel, BorderLayout.SOUTH);

// Create checkout panel
checkoutPanel = new JPanel(new BorderLayout());
checkoutPanel.setBorder(BorderFactory.createTitledBorder("Checkout"));
checkoutButton = new JButton("Confirm Checkout");
checkoutButton.setBackground(Color.yellow);
JPanel checkoutButtonPanel = new JPanel(new FlowLayout());
checkoutButtonPanel.add(checkoutButton);
checkoutPanel.add(checkoutButtonPanel, BorderLayout.SOUTH);

// Create borrowed panel
borrowedPanel = new JPanel(new BorderLayout());
borrowedPanel.setBorder(BorderFactory.createTitledBorder("Borrowed Items"));
borrowedTableModel = new DefaultTableModel();

// Add table columns
borrowedTableModel.addColumn("Item Name");
borrowedTableModel.addColumn("Quantity");
borrowedTableModel.addColumn("Value");
borrowedTableModel.addColumn("Borrowed");
borrowedTableModel.addColumn("To Return");

// Add data to table model
Object[] borrowedRow = new Object[5];
for (BorrowedItem item : Borrowed) {
    borrowedRow[0] = item.getItem().getName();
    borrowedRow[1] = item.getQuantity();
    borrowedRow[2] = item.getItem().getEstimatedValue() * item.getQuantity();
    borrowedRow[3] = item.getBorrowDate().toLocalDate();
    borrowedRow[4] = item.getReturnDate().toLocalDate();
    borrowedTableModel.addRow(borrowedRow);
}

borrowedTable = new JTable(borrowedTableModel);
JScrollPane borrowedScrollPane = new JScrollPane(borrowedTable);

borrowedPanel.add(borrowedScrollPane, BorderLayout.CENTER);

// Add panels to frame
add(searchPanel, BorderLayout.WEST);
add(cartPanel, BorderLayout.CENTER);
add(checkoutPanel, BorderLayout.SOUTH);
add(borrowedPanel, BorderLayout.EAST);

// Pack and display frame
pack();
setVisible(true);

searchButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String searchTerm = searchField.getText();
        ArrayList<Item> searchResults = t.searchItems(searchTerm);
        inventoryTableModel.setRowCount(0);
        for (Item item : searchResults) {
            Object[] rowData = {item.getName(), item.getModel(), item.getAvailableQuantity(), item.getEstimatedValue()};
            inventoryTableModel.addRow(rowData);
        }
        selectedItem = null;
        if (searchTerm.isEmpty()) {
            t.displayInventory(inventoryTableModel);
        }
    }
});

// Define cart table model as an instance variable
final DefaultTableModel cartTableModel = (DefaultTableModel) cartTable.getModel();


addToCartButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        if (selectedItem == null) {
            JOptionPane.showMessageDialog(null, "Please select an item to add to the cart.");
            return;
        }
        try {
            int quantity = Integer.parseInt(quantityField.getText());
            if (quantity <= 0) {
                JOptionPane.showMessageDialog(null, "Please enter a valid quantity.");
                return;
            }
            if (quantity > selectedItem.getAvailableQuantity()) {
                JOptionPane.showMessageDialog(null, "Not enough quantity available.");
                return;
            }
            //;

          Item old=  selectedItem;

          selectedItem.setAvailableQuantity(selectedItem.getAvailableQuantity() - quantity);
            
            t.editItem(old,selectedItem );
            updateMethod();
            
            Object[] rowData = {selectedItem.getName(), selectedItem.getModel(), quantity, selectedItem.getEstimatedValue() * quantity};
            // Add row to cart table model
            cartTableModel.addRow(rowData);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid quantity.");
        }
    }
});


        
removeButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        int selectedRow = cartTable.getSelectedRow();
        if (selectedRow != -1) {
            // Get the item information from the selected row in the cart table
            String itemName = (String) cartTableModel.getValueAt(selectedRow, 0);
            int itemQuantity = (int) cartTableModel.getValueAt(selectedRow, 2);
            
            // Find the corresponding item in the inventory and update its quantity
            t.searchItem(itemName).setAvailableQuantity(selectedItem.getAvailableQuantity() + itemQuantity);

            updateMethod();
            
            // Remove the selected row from the cart table model
            cartTableModel.removeRow(selectedRow);
        }
    }
});



        
        
checkoutButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to checkout?", "Confirm Checkout", JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            double totalValue = 0.0;
            
            
            
            // Create a list to hold the requested items
            ArrayList<BorrowedItem> requestedItems = new ArrayList<BorrowedItem>();
            
            for (int i = 0; i < cartTableModel.getRowCount(); i++) {
                String name = (String) cartTableModel.getValueAt(i, 0);
                int quantity = (int) cartTableModel.getValueAt(i, 2);
                double itemValue = (double) cartTableModel.getValueAt(i, 3);
                Item item = t.searchItem(name);
                LocalDateTime borrowDate = LocalDateTime.now();
                LocalDateTime returnDate = borrowDate.plusDays(20); // set return date 20 days later
                BorrowedItem borrowedItem = new BorrowedItem(item, quantity, borrowDate, s);
                borrowedItem.setReturnDate(returnDate);
                Borrowed.add(borrowedItem);


                //item.setAvailableQuantity(selectedItem.getAvailableQuantity() - quantity);
                
                
                // Add borrowed item to requested items list
                requestedItems.add(borrowedItem);
                
                // Add borrowed item to borrowed panel
                Object[] borrowedRow = new Object[5];
                borrowedRow[0] = item.getName();
                borrowedRow[1] = quantity;
                borrowedRow[2] = itemValue ;
                borrowedRow[3] = borrowDate.toLocalDate();
            
                if(item.isToReturn())
                borrowedRow[4] = returnDate.toLocalDate();
                else
                borrowedRow[4] = "N/A";
                borrowedTableModel.addRow(borrowedRow);
                
                totalValue += itemValue;

                t.addRequest(s,borrowedItem, new Date());
            }
            
            
            
            
            // Update the cart and total value
            Cart.clear();   
            cartTableModel.setRowCount(0);
            //totalValueLabel.setText("$" + String.format("%.2f", totalValue));
            JOptionPane.showMessageDialog(null, "Checkout complete. Total value: $" + totalValue);
            cartTableModel.setRowCount(0);
             }
    }
});




    }

    // public void updateMethod(){
    //     inventoryTableModel.setRowCount(0);
    //     t.displayInventory(inventoryTableModel);
    //     inventoryTable.setModel(inventoryTableModel);
    //     inventoryTable.repaint();
    // }

    
    
    public void updateMethod(){
        // Update inventory table
        inventoryTableModel.setRowCount(0);
        t.displayInventory(inventoryTableModel);
        inventoryTable.setModel(inventoryTableModel);
        inventoryTable.repaint();
        
        Borrowed = s.getBorrowedItems();
        // Update borrowed table
        borrowedTableModel.setRowCount(0);
        for (BorrowedItem item : Borrowed) {
            Object[] borrowedRow = new Object[5];
            borrowedRow[0] = item.getItem().getName();
            borrowedRow[1] = item.getQuantity();
            borrowedRow[2] = item.getQuantity() * item.getItem().getEstimatedValue();
            borrowedRow[3] = item.getBorrowDate().toLocalDate();
            if(item.getItem().isToReturn())
            borrowedRow[4] = item.getReturnDate().toLocalDate();
            else
            borrowedRow[4] = "N/A";
            borrowedTableModel.addRow(borrowedRow);
        }
        borrowedTable.setModel(borrowedTableModel);
        borrowedTable.repaint();

    }
    

    // public static void main(String[] args) {
    //     Technician t= new Technician( null, null);
    //     Item i= new Item("Hello", null, ALLBITS, true, ABORT);
    //     t.addItem(i);
    //     Student s= new Student("Hello", null, null, null);
    //     new StudentGUI(t,s);

    // }

}
