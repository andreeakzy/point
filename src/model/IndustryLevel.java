package model;

// career/industry skill levels
public enum IndustryLevel {
    BEGINNER("Beginner - foundational knowledge"),
    INTERMEDIATE("Intermediate - practical skills"),
    ADVANCED("Advanced - deep expertise"),
    EXPERT("Expert - mastery level");

    private final String description;

    IndustryLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
