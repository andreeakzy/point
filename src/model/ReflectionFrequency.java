package model;

// reflection check-in frequency for personal learning points
public enum ReflectionFrequency {
    WEEKLY("Weekly - once per week"),
    BIWEEKLY("Bi-weekly - every two weeks"),
    MONTHLY("Monthly - once per month");

    private final String description;

    ReflectionFrequency(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
