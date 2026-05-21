package model;

// personal wellness dimensions
public enum WellnessCategory {
    PHYSICAL("Physical - exercise, health, nutrition"),
    MENTAL("Mental - cognitive skills, learning"),
    EMOTIONAL("Emotional - feelings, relationships"),
    SOCIAL("Social - community, networking"),
    SPIRITUAL("Spiritual - purpose, meaning");

    private final String description;

    WellnessCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
