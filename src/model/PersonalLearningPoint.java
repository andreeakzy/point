package model;

import java.time.LocalDate;

// Personal development learning points track wellbeing with reflection and self-assessment
public class PersonalLearningPoint extends LearningPoint {
    private String personalFocusArea;             // Main focus (habit, hobby, etc.)
    private WellnessCategory wellnessCategory;    // Physical, mental, emotional, social, spiritual
    private ReflectionFrequency reflectionFrequency;  // How often to reflect
    private String successMetrics;                // How to measure success
    private LocalDate lastReflectionDate;         // Track last reflection check-in

    public PersonalLearningPoint() {}

    public PersonalLearningPoint(int id, String pointTitle, String pointDescription,
            DifficultyLevel difficultyLevel, PriorityLevel priorityLevel,
            EnergyLevel requiredEnergyLevel, Category category, String personalFocusArea,
            WellnessCategory wellnessCategory, ReflectionFrequency reflectionFrequency,
            String successMetrics) {
        super(id, pointTitle, pointDescription, difficultyLevel, priorityLevel, requiredEnergyLevel, category);
        this.personalFocusArea = personalFocusArea;
        this.wellnessCategory = wellnessCategory;
        this.reflectionFrequency = reflectionFrequency;
        this.successMetrics = successMetrics;
        this.lastReflectionDate = LocalDate.now();
    }

    public String getPersonalFocusArea() {
        return personalFocusArea;
    }

    public void setPersonalFocusArea(String personalFocusArea) {
        this.personalFocusArea = personalFocusArea;
    }

    public WellnessCategory getWellnessCategory() {
        return wellnessCategory;
    }

    public void setWellnessCategory(WellnessCategory wellnessCategory) {
        this.wellnessCategory = wellnessCategory;
    }

    public ReflectionFrequency getReflectionFrequency() {
        return reflectionFrequency;
    }

    public void setReflectionFrequency(ReflectionFrequency reflectionFrequency) {
        this.reflectionFrequency = reflectionFrequency;
    }

    public String getSuccessMetrics() {
        return successMetrics;
    }

    public void setSuccessMetrics(String successMetrics) {
        this.successMetrics = successMetrics;
    }

    public LocalDate getLastReflectionDate() {
        return lastReflectionDate;
    }

    public void setLastReflectionDate(LocalDate lastReflectionDate) {
        this.lastReflectionDate = lastReflectionDate;
    }

    // Method: Schedule next reflection date based on frequency
    // Justification: Personal growth requires periodic self-reflection to maintain progress
    public LocalDate scheduleNextReflection() {
        LocalDate nextReflection;

        switch (reflectionFrequency) {
            case WEEKLY:
                nextReflection = lastReflectionDate.plusWeeks(1);
                break;
            case BIWEEKLY:
                nextReflection = lastReflectionDate.plusWeeks(2);
                break;
            case MONTHLY:
                nextReflection = lastReflectionDate.plusMonths(1);
                break;
            default:
                nextReflection = lastReflectionDate.plusWeeks(1);
        }

        return nextReflection;
    }

    // Method: Check if wellness-related and return category description
    // Justification: Helps organize personal growth across different wellbeing dimensions
    public String getWellnessDescription() {
        if (wellnessCategory != null) {
            return wellnessCategory.getDescription();
        }
        return "Wellness category not specified";
    }

    @Override
    public String toString() {
        return String.format("%s | Focus: %s, Wellness: %s, Reflection: %s, Metrics: %s",
                super.toString(), personalFocusArea, wellnessCategory, reflectionFrequency, successMetrics);
    }
}
