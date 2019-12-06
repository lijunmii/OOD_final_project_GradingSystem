public class CategoryPart extends Category {

    private Date assignDate;
    private Date dueDate;
    private Grade grade;

    public CategoryPart(String label, double weight, Date assignDate, Date dueDate, double fullScore) {
        super(label, weight);
        this.assignDate = assignDate;
        this.dueDate = dueDate;
        this.grade = new Grade(fullScore);
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public double getPercentageGrade() {
        return grade.getPercentageGrade();
    }

    public double getRawGrade() {
        return grade.getRawScore();
    }

    public void setPercentageGrade(double percentageGrade){
        grade.setPercentageGrade(percentageGrade);
    }

    public boolean setRawGrade(double pointsMinus) {
        return grade.enterRawScore(pointsMinus);
    }
}
