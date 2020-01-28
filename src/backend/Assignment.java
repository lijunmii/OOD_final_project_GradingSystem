package backend;

import java.io.Serializable;

public class Assignment implements Serializable {
    private String category;
    private String name;
    private Double fullScore;
    private Double weight;
    private String startDate = "";
    private String dueDate = "";
    private String note = "";

    public Assignment(String category, String name, double fullScore, double weight) {
        this.category = category;
        this.name = name;
        this.fullScore = fullScore;
        this.weight = weight;
    }

    public Assignment(String category, String name, double fullScore, double weight, String startDate, String dueDate) {
        this.category = category;
        this.name = name;
        this.fullScore = fullScore;
        this.weight = weight;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public Double getFullScore() {
        return fullScore;
    }

    public Double getWeight() {
        return weight;
    }

    public String getNote() {
        return note;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setFullScore(Double fullScore) {
        this.fullScore = fullScore;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getInfo() {
        String info = "Assignment name: " + name;
        info += "\nCategory: " + category;
        info += "\nFull score: " + fullScore;
        info += "\nWeight: " + weight;
        info += "\nStart date: " + startDate;
        info += "\nDue date: " + dueDate;
        info += "\nNote: " + note;
        return info;
    }

    public void updateInfo(String category, String name, Double fullScore, Double weight, String startDate, String dueDate, String note) {
        this.category = category;
        this.name = name;
        this.fullScore = fullScore;
        this.weight = weight;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.note = note;
    }
}
