public class Item {
    private String name;
    private String model;
    private double estimatedValue;
    private boolean toReturn;
    private int availableQuantity;

    // Constructor
    public Item(String name, String model, double estimatedValue, boolean toReturn, int availableQuantity) {
        this.name = name;
        this.model = model;
        this.estimatedValue = estimatedValue;
        this.toReturn = toReturn;
        this.availableQuantity = availableQuantity;
    }

    // Getter and setter methods for all attributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(double estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    public boolean isToReturn() {
        return toReturn;
    }

    public void setToReturn(boolean toReturn) {
        this.toReturn = toReturn;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
}
