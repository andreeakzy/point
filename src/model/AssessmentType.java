package model;

// academic assessment types
public enum AssessmentType {
    EXAM("Exam - written or oral test"),
    PROJECT("Project - hands-on deliverable"),
    PRESENTATION("Presentation - oral presentation"),
    ASSIGNMENT("Assignment - homework or task");

    private final String description;

    AssessmentType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
