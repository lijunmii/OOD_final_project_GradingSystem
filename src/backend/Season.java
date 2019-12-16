package backend;

import java.io.Serializable;

public enum Season implements Serializable {
    Spring("Q1 Spring", 1),
    Summer("Q2 Summer", 2),
    Fall("Q3 Fall", 3),
    Winter("Q4 Winter", 4);

    private final String name;
    private final int value;

    Season(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name;
    }
}
