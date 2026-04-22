package model;

// clasa pt learning activities career related
// extinde clasa LearningPoint

public class CareerLearningPoint extends LearningPoint {
    private String skillTrack;

    public CareerLearningPoint() {}

    public CareerLearningPoint(int id, String pointTitle, String pointDescription,DifficultyLevel difficultyLevel, PriorityLevel priorityLevel, EnergyLevel requiredEnergyLevel, Category category, String skillTrack) {
        super(id, pointTitle, pointDescription, difficultyLevel, priorityLevel, requiredEnergyLevel, category);

        this.skillTrack = skillTrack;
    }

    public String getSkillTrack() {
        return skillTrack;
    }

    // setter pentru aria profesionala
    public void setSkillTrack(String skillTrack) {
        this.skillTrack = skillTrack;
    }

    @Override
    public String toString() {
        return super.toString() + ", skillTrack='" + skillTrack + '\'';
    }
}