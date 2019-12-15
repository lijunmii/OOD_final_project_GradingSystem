package backend;

public class Grade {
    private Double score;
    private Double fullScore;
    private String comment = "Leave your comment for this grade here.";

    public Grade(double score, double fullScore) {
        this.score = score;
        this.fullScore = fullScore;
    }

    public Double getScore() {
        return score;
    }

    public Double getFullScore() {
        return fullScore;
    }

    public String getComment() {
        return comment;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public void setFullScore(Double fullScore) {
        this.fullScore = fullScore;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return score.toString();
    }
}
