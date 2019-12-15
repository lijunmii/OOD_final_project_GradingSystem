package backend;

import java.util.Date;

public class Assignment {
    private String category;
    private String name;
    private Double fullScore;
    private Double weight;
    private String startDate;
    private String dueDate;
    private String comment;

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

    public String getComment() {
        return comment;
    }

    public void setFullScore(Double fullScore) {
        this.fullScore = fullScore;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return name;
    }
}
