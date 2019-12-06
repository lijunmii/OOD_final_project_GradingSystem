import java.util.ArrayList;
import java.util.List;

public class Category {

    private String label;
    private double weight;
    private Grade grade;
    private List<Category> content;

    public Category(String label, double weight) {
        this.label = label;
        this.weight = weight;
        this.grade = null;
        this.content = new ArrayList<>();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public List<Category> getContent() {
        return content;
    }

    public void addContent(Category part) {
        content.add(part);
    }
}
