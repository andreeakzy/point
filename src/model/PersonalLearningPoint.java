package model;

// clasa derivata pentru activitatile personale
public class PersonalLearningPoint extends LearningPoint {
    private String personalFocusArea;

    public PersonalLearningPoint() {
    }

    public PersonalLearningPoint(int id, String pointTitle, String pointDescription,
                                 DifficultyLevel difficultyLevel, PriorityLevel priorityLevel,
                                 EnergyLevel requiredEnergyLevel, Category category,
                                 String personalFocusArea) {
        super(id, pointTitle, pointDescription, difficultyLevel, priorityLevel, requiredEnergyLevel, category);
        this.personalFocusArea = personalFocusArea;
    }

    // getter pentru zona personala de interes
    public String getPersonalFocusArea() {
        return personalFocusArea;
    }

    // setter pentru zona personala de interes
    public void setPersonalFocusArea(String personalFocusArea) {
        this.personalFocusArea = personalFocusArea;
    }

    @Override
    public String toString() {
        return super.toString() + ", personalFocusArea='" + personalFocusArea + '\'';
    }
}