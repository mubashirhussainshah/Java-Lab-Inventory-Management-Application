import java.time.LocalDateTime;

public class BorrowedItem {
    private Item item;
    private int quantity;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private Student borrowedBy;
    private double penalty;
    

    public BorrowedItem(Item item, int quantity, LocalDateTime borrowDate, Student borrowedBy) {
        this.item = item;
        this.quantity = quantity;
        this.borrowDate = borrowDate;
        this.borrowedBy = borrowedBy;
        this.penalty = 0;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public Student getBorrowedBy() {
        return borrowedBy;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public double getPenalty() {
        calculatePenalty();
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public boolean expiredItem(){
        if (returnDate == null) {
            return false; // Return date not set yet, so no penalty
        }
        
        // Calculate penalty if current date is after return date
        LocalDateTime now = LocalDateTime.now();     
            return now.isAfter(returnDate);

    }

    public void calculatePenalty() {
        if (returnDate == null) {
            return; // Return date not set yet, so no penalty
        }
        if (expiredItem()) {
            setPenalty(item.getEstimatedValue());
        } else {
            setPenalty(0.0); // No penalty if current date is on or before return date
        }
    }
}
