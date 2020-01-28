package backend;

import java.io.Serializable;

public class Semester implements Comparable, Serializable {
    private Integer year;
    private Season season;

    public Semester() {
        year = null;
        season = null;
    }
    public Semester(int year, Season season) {
        this.year = year;
        this.season = season;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Semester) {
            if (!year.equals(((Semester) o).year)) {
                return year - ((Semester) o).year;
            } else {
                return season.getValue() - ((Semester) o).getSeason().getValue();
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return year.toString() + " " + season.toString();
    }

    public Integer getYear() {
        return year;
    }

    public Season getSeason() {
        return season;
    }
}
