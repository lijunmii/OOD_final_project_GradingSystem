package backend;

public class Grade {
    private Double score;
    private Double fullScore;
    private String comment = "Leave your comment for this grade here.";

    public Grade(double score, double fullScore) {
        this.score = score;
        this.fullScore = fullScore;
    }

    public Grade(double fullScore) {
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
        if (score >= 0) {
            this.score = score;
        } else {
            this.score = fullScore + score;
        }
    }

    public void setScore(String scoreStr) {
        String scoreTempStr = scoreStr.substring(0, scoreStr.length() - 1);
        if (Tools.isNumeric(scoreTempStr)) {
            Double scoreTemp = Double.parseDouble(scoreTempStr);
            score = fullScore * scoreTemp / 100;
        }
    }

    public void setFullScore(Double fullScore) {
        this.fullScore = fullScore;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        if (score == null) {
            return "";
        }
        return score.toString();
    }
}
