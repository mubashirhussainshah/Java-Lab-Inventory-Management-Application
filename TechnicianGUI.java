import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;



public class TechnicianGUI extends JFrame {
    
    private JTextField searchField;
    private JTable inventoryTable;
    private JTable borrowedTable;
    DefaultTableModel inventoryTableModel ; 
    private Item selectedItem;
    Technician t ;

    
    public TechnicianGUI(Technician t) {
        
        this.t = t;
        setTitle("Store Inventory Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // create components
        JLabel inventoryLabel = new JLabel("Inventory");
        JLabel borrowedLabel = new JLabel("Borrowed Items");
        JLabel searchLabel = new JLabel("Search by name:");
        searchField = new JTextField(20);
        searchField.setBackground(Color.ORANGE);
        JButton addButton = new JButton("Add");
        addButton.setBackground(Color.YELLOW);
        JButton editButton = new JButton("Edit");
        editButton.setBackground(Color.YELLOW);
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(Color.YELLOW);
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(Color.YELLOW);

        // create a font with size 16
        Font font = new Font("Arial", Font.PLAIN, 25);
        inventoryLabel.setFont(font);
        borrowedLabel.setFont(font);
        
        // create inventory table with dummy data

        inventoryTableModel = new DefaultTableModel();

        

         // Add table columns
        inventoryTableModel.addColumn("Name");
        inventoryTableModel.addColumn("Model");
        inventoryTableModel.addColumn("Quantity");
        inventoryTableModel.addColumn("Value");


        t.displayInventory(inventoryTableModel);
        inventoryTable = new JTable(inventoryTableModel);
        ListSelectionModel selectionModel = inventoryTable.getSelectionModel();


        // Add selection listener to the table
        selectionModel.addListSelectionListener((ListSelectionListener) (e) -> {
            // your code here
            // Get the selected row index
            int selectedRow = inventoryTable.getSelectedRow();
            if (selectedRow != -1) {
                // Get the item object for the selected row
                selectedItem = t.getInventory().get(selectedRow);
            }
        });
        

        JScrollPane inventoryScrollPane = new JScrollPane(inventoryTable);
        
       // create borrowed items table with data from borrowedItems list
        String[] borrowedColumnNames = {"Name","Quantity","Date", "Return", "Student", "Penalty"};
        DefaultTableModel borrowedTableModel = new DefaultTableModel();
        borrowedTableModel.setColumnIdentifiers(borrowedColumnNames);
        t.displayBorrowedItems(borrowedTableModel);
        borrowedTable = new JTable(borrowedTableModel);
        JScrollPane borrowedScrollPane = new JScrollPane(borrowedTable);

        
        
       
        
        // create search panel
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        
        // create buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);
        
        // add components to content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // create a button to manage requests
        JButton requestButton = new JButton("Requests");

        // create a panel for the inventory table and label
        JPanel inventoryPanel = new JPanel(new BorderLayout());
        inventoryPanel.add(inventoryLabel, BorderLayout.NORTH);
        inventoryPanel.add(inventoryScrollPane, BorderLayout.CENTER);

        // create a panel for the borrowed items table and label
        JPanel borrowedPanel = new JPanel(new BorderLayout());
        borrowedPanel.add(borrowedLabel, BorderLayout.NORTH);
        borrowedPanel.add(borrowedScrollPane, BorderLayout.CENTER);
        
        borrowedPanel.add(requestButton, BorderLayout.SOUTH);

        // create a panel for the search field and buttons
        JPanel searchButtonsPanel = new JPanel(new FlowLayout());
        searchButtonsPanel.add(addButton);
        searchButtonsPanel.add(editButton);
        searchButtonsPanel.add(deleteButton); 
        searchButtonsPanel.add(searchPanel);

        



// create a panel for the whole bottom section (search field and buttons)
JPanel bottomPanel = new JPanel(new BorderLayout());
bottomPanel.add(searchButtonsPanel, BorderLayout.EAST);

    // add the inventory and borrowed items panels to the content pane
    contentPane.add(inventoryPanel, BorderLayout.CENTER);
    contentPane.add(borrowedPanel, BorderLayout.EAST);
    // add the bottom panel to the content pane
    contentPane.add(bottomPanel, BorderLayout.SOUTH);



       
        // set window size and visibility
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        // add action listener to request button
requestButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // create a new window for managing requests
        JFrame requestFrame = new JFrame("Requests");
        requestFrame.setSize(600, 400);

        // create a table for displaying requests
        JTable requestTable = new JTable();
        JScrollPane requestScrollPane = new JScrollPane(requestTable);
        requestFrame.getContentPane().add(requestScrollPane);

        // create a panel for buttons
        JPanel buttonPanel = new JPanel();

        // add accept button for each row in the table
        JButton acceptButton = new JButton("Accept");
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get the selected request
                int row = requestTable.getSelectedRow();
                if (row != -1) {
                    Requests selectedRequest = t.getRequests().get(row);

                    // for(Student s: t.getAllBorrowers()){
                    //     s.getGUI().updateMethod();
                    // }
                    //selectedRequest.getStudent().getGUI().updateMethod();
                    
                    // remove the request from the list
                    t.getRequests().remove(selectedRequest);
                    // add the item to the borrowed items list
                    t.addBorrowedItem(selectedRequest.getItem());
                    updateMethod();
                    
                    // update the borrowed items table
                    DefaultTableModel borrowedTableModel = (DefaultTableModel) borrowedTable.getModel();
                    //borrowedTableModel.setRowCount(0);
                    t.displayBorrowedItems(borrowedTableModel);
                    
                    // update the request table
                    DefaultTableModel requestTableModel = (DefaultTableModel) requestTable.getModel();
                   // requestTableModel.setRowCount(0);
                    t.displayRequests(requestTableModel);
                }
            }
        });
        buttonPanel.add(acceptButton);

        JButton rejectButton = new JButton("Reject");
rejectButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // get the selected request
        int row = requestTable.getSelectedRow();
        if (row != -1) {
            Requests selectedRequest = t.getRequests().get(row);
            Item item = selectedRequest.getItem().getItem();
            Student student = selectedRequest.getStudent();
            
            // add the quantity of the item back to the available quantity
         //   t.searchItem(item.getName()).setAvailableQuantity(item.getAvailableQuantity() + selectedRequest.getItem().getQuantity());
            for(BorrowedItem i: student.getBorrowedItems()){
                if(i.getItem().getName().equals(item.getName())){
                    i.getItem().setAvailableQuantity(item.getAvailableQuantity() + selectedRequest.getItem().getQuantity());
                }
            }
            
            // remove the item from the borrowed items list of the student
            t.removeBorrowedItem(selectedRequest.getItem());
            student.removeBorrowedItem(selectedRequest.getItem());
            student.getGUI().updateMethod();
            
            // remove the request from the list
            t.getRequests().remove(selectedRequest);
            
            // update the request table
            DefaultTableModel requestTableModel = (DefaultTableModel) requestTable.getModel();
            requestTableModel.setRowCount(0);
            t.displayRequests(requestTableModel);
        }
    }
});

        buttonPanel.add(rejectButton);

        requestFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // populate the table with data from the requests list
        DefaultTableModel requestTableModel = new DefaultTableModel();
        requestTableModel.addColumn("Item");
        requestTableModel.addColumn("Student");
        requestTableModel.addColumn("Date");

        // add data from requests to table
        for (Requests req : t.getRequests()) {
            Object[] row = new Object[3];
            row[0] = req.getItem().getItem().getName();
            row[1] = req.getStudent().getName();
            row[2] = req.getRequestedDate();
            requestTableModel.addRow(row);
        }

        requestTable.setModel(requestTableModel);

        requestFrame.setVisible(true);
    }
});
        
        // add event listeners
        addButton.addActionListener(e -> {
           AddFunctionality addForm= new AddFunctionality(t);
           addForm.setVisible(true);

        });
        
        editButton.addActionListener(e -> {
    

            Item old = selectedItem;
            // Get the selected item object
            if (selectedItem != null) {
                // Create a new dialog to edit the selected item
                JDialog editDialog = new JDialog();
                editDialog.setTitle("Edit Item");

                // Create the form to edit the item details
                JLabel nameLabel = new JLabel("Name:");
                JTextField nameField = new JTextField(selectedItem.getName(), 20);
                JPanel namePanel = new JPanel(new BorderLayout());
                namePanel.add(nameLabel, BorderLayout.WEST);
                namePanel.add(nameField, BorderLayout.CENTER);

                JLabel priceLabel = new JLabel("Price:");
                JTextField priceField = new JTextField(String.valueOf(selectedItem.getEstimatedValue()), 20);
                JPanel pricePanel = new JPanel(new BorderLayout());
                pricePanel.add(priceLabel, BorderLayout.WEST);
                pricePanel.add(priceField, BorderLayout.CENTER);

                JLabel quantityLabel = new JLabel("Quantity:");
                JTextField quantityField = new JTextField(String.valueOf(selectedItem.getAvailableQuantity()), 20);
                JPanel quantityPanel = new JPanel(new BorderLayout());
                quantityPanel.add(quantityLabel, BorderLayout.WEST);
                quantityPanel.add(quantityField, BorderLayout.CENTER);    

                editDialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        editDialog.dispose();
                    }
                });

                JButton saveButton = new JButton("Save");
        saveButton.addActionListener(saveEvent -> {
            // Save the edited item details
            selectedItem.setName(nameField.getText());
            selectedItem.setEstimatedValue(Double.parseDouble(priceField.getText()));
            selectedItem.setAvailableQuantity(Integer.parseInt(quantityField.getText()));
            t.editItem(old, selectedItem);
            
            updateMethod();

            // Update the table display
            inventoryTableModel.fireTableDataChanged();

            editDialog.dispose();
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(cancelEvent -> {
            editDialog.dispose();
        });


                JPanel formPanel = new JPanel(new GridLayout(0, 1, 10, 10));
                formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                formPanel.add(namePanel);
                formPanel.add(pricePanel);
                formPanel.add(quantityPanel);
                formPanel.add(saveButton);
                formPanel.add(cancelButton);

                editDialog.add(formPanel);
                editDialog.pack();
                editDialog.setLocationRelativeTo(null);
                editDialog.setVisible(true);
            }
        });

            

        
        // deleteButton.addActionListener(e -> {
        //     // TODO: handle delete button click
        // });

        deleteButton.addActionListener(e -> {
            // Get the selected item object
            if (selectedItem != null) {
                // Create a confirmation dialog before deleting the item
                int confirmResult = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to delete this item?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);
        
                if (confirmResult == JOptionPane.YES_OPTION) {
                    // Remove the selected item from the inventory
                    t.removeItem(selectedItem);
                    updateMethod();
        
                    // Clear the selected item
                    selectedItem = null;
        
                    // Update the table display
                    inventoryTableModel.fireTableDataChanged();
        
                    // Show a success message
                    JOptionPane.showMessageDialog(
                            null,
                            "Item successfully deleted.",
                            "Delete Success",
                            JOptionPane.INFORMATION_MESSAGE);

                            for(Student s: t.getAllBorrowers()){
                                s.getGUI().updateMethod();
                            }

                            
                }
            } else {
                // Show an error message if no item is selected
                JOptionPane.showMessageDialog(
                        null,
                        "No item selected.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        
        
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();
                ArrayList<Item> searchResults = t.searchItems(searchTerm);
                // Clear the inventory table model
                inventoryTableModel.setRowCount(0);
                // Add search results to the table model
                for (Item item : searchResults) {
                    Object[] rowData = {item.getName(), item.getModel(), item.getAvailableQuantity(), item.getEstimatedValue()};
                    inventoryTableModel.addRow(rowData);
                }
                // Clear the selected item
                selectedItem = null;
                // If search term is empty, show all items
                if (searchTerm.isEmpty()) {
                    t.displayInventory(inventoryTableModel);
                } else if (searchResults.isEmpty()) {
                    // Display message when no search results found
                    JOptionPane.showMessageDialog(null, "No items found matching the search criteria", "Search Results", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        
        
        
        
        



     
    }
    public void updateMethod(){
        inventoryTableModel.setRowCount(0);
        t.displayInventory(inventoryTableModel);
        inventoryTable.setModel(inventoryTableModel);
        inventoryTable.repaint();
    }
    private class AddFunctionality extends JFrame  {

        private JPanel contentPane;
        private JTextField txtItemName, txtItemQuantity, txtItemModel, txtItemEstValue;
        private JLabel lblItemName, lblItemQuantity, lblItemModel, lblItemEstValue;
        private JButton btnAddItem;
        private JComboBox<String> cmbItemType;
    
        public AddFunctionality(Technician t) {
            setTitle("Add Item Form");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setBounds(100, 100, 450, 450);
    
            contentPane = new JPanel();
            contentPane.setLayout(null);
            setContentPane(contentPane);
    
            // Add item form panel
            JPanel itemFormPanel = new JPanel();
            itemFormPanel.setBorder(BorderFactory.createTitledBorder("Add New Item"));
            itemFormPanel.setBounds(20, 20, 400, 400);
            itemFormPanel.setLayout(null);
            contentPane.add(itemFormPanel);
    
            // Add item form fields and labels
            
    
            lblItemName = new JLabel("Name:");
            lblItemName.setFont(new Font("Tahoma", Font.PLAIN, 12));
            lblItemName.setBounds(20, 60, 70, 15);
            itemFormPanel.add(lblItemName);
    
            txtItemName = new JTextField();
            txtItemName.setBounds(100, 60, 150, 20);
            itemFormPanel.add(txtItemName);
    
            String[] itemTypes = { "Equipment", "Consumable" };
            cmbItemType = new JComboBox<String>(itemTypes);
            cmbItemType.setBounds(100, 90, 150, 20);
            itemFormPanel.add(cmbItemType);
    
            lblItemQuantity = new JLabel("Quantity:");
            lblItemQuantity.setFont(new Font("Tahoma", Font.PLAIN, 12));
            lblItemQuantity.setBounds(20, 120, 70, 15);
            itemFormPanel.add(lblItemQuantity);
    
            txtItemQuantity = new JTextField();
            txtItemQuantity.setBounds(100, 120, 150, 20);
            itemFormPanel.add(txtItemQuantity);
    
            lblItemModel = new JLabel("Item Model:");
            lblItemModel.setFont(new Font("Tahoma", Font.PLAIN, 12));
            lblItemModel.setBounds(20, 160, 70, 15);
            itemFormPanel.add(lblItemModel);
    
            txtItemModel = new JTextField();
            txtItemModel.setBounds(100, 160, 150, 20);
            itemFormPanel.add(txtItemModel);
    
            lblItemEstValue = new JLabel("Item Estimated Value:");
            lblItemEstValue.setFont(new Font("Tahoma", Font.PLAIN, 12));
            lblItemEstValue.setBounds(20, 200, 130, 15);
            itemFormPanel.add(lblItemEstValue);
    
            txtItemEstValue = new JTextField();
            txtItemEstValue.setBounds(150, 200, 100, 20);
            itemFormPanel.add(txtItemEstValue);
    
            btnAddItem = new JButton("Add Item");
            btnAddItem.setBounds(150, 280, 100, 25);
            itemFormPanel.add(btnAddItem);
    

            btnAddItem.addActionListener(e -> {
                // Code to add item to inventory

                Boolean toReturn = false;
                String selectedType = cmbItemType.getSelectedItem().toString();
                if(selectedType.equals("Equipment")) toReturn = true;

                String itemName = txtItemName.getText();
                String itemModel = txtItemModel.getText();
                String estValueStr = txtItemEstValue.getText();
                String quantityStr = txtItemQuantity.getText();

                // Check if the fields are empty
                if (itemName.isEmpty() || itemModel.isEmpty() || estValueStr.isEmpty() || quantityStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Check if the quantity is a number
                int quantity;
                try {
                    quantity = Integer.parseInt(quantityStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Quantity must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Check if the estimated value is a number
                double estValue;
                try {
                    estValue = Double.parseDouble(estValueStr);
                } catch (NumberFormatException x) {
                    JOptionPane.showMessageDialog(null, "Estimated value must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Item i = new Item(itemName, itemModel, estValue, toReturn, quantity);
                t.addItem(i);
                updateMethod();
                for(Student s: t.getAllBorrowers()){
                    s.getGUI().updateMethod();
                }
                JOptionPane.showMessageDialog(null, "Item added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();

                
            });

}
    }
}
    
//     public static void main(String[] args) {
//         Technician t = new Technician("tech", "hello", "t2023");
//         new TechnicianGUI(t);
//     }
// }    