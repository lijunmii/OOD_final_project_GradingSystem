import java.util.Map;

public class Grade {

    private char letterGrade;
    private double percentageGrade;
    private double rawScore;
    private double fullScore;
    private GradeScheme scheme;

    public Grade(double fullScore) {
        this.letterGrade = '-';
        this.percentageGrade = 0;
        this.rawScore = 0;
        this.fullScore = fullScore;
    }

//    public Grade(double score, GradeScheme gradeScheme) {
//        letterGrade = '-';
//        percentageGrade = 0;
//        rawScore = 0;
//        fullScore = score;
//        this.scheme = gradeScheme;
//    }

    public double getPercentageGrade() {
        return percentageGrade;
    }

    public void setPercentageGrade(double percentageGrade) {
        this.percentageGrade = percentageGrade;
    }

    public double getRawScore() {
        return rawScore;
    }

    public void setRawScore(double rawScore) {
        this.rawScore = rawScore;
    }

    public boolean enterRawScore(double score) {
        if (score > fullScore) return false;
        rawScore = fullScore - score;
        percentageGrade = rawScore / fullScore * 100;
//        for (Map.Entry<Character, Double> entry : scheme.getGradeScheme().entrySet()) {
//            if (percentageGrade >= entry.getValue()) {
//                letterGrade = entry.getKey();
//            }
//        }
        return true;
    }
}
