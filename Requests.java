
import java.util.Date;

public class Requests {
    private Student student;
    private BorrowedItem items;
    private Date requestedDate;
    private boolean accepted;
    private boolean returned;

    public Requests(Student student, BorrowedItem items, Date requestedDate) {
        this.student = student;
        this.items = items;
        this.requestedDate = requestedDate;
        this.accepted = false;
        this.returned = false;
    }

    public Student getStudent() {
        return student;
    }

    public BorrowedItem  getItem() {
        return items;
    }

    public Date getRequestedDate() {
        return requestedDate;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}
