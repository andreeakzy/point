package model;

// clasa pt learning activities academice
// extinde clasa LearningPoint 

public class AcademicLearningPoint extends LearningPoint {
    private String subjectName;

    public AcademicLearningPoint() {}

    public AcademicLearningPoint(int id, String pointTitle, String pointDescription, DifficultyLevel difficultyLevel, PriorityLevel priorityLevel, EnergyLevel requiredEnergyLevel, Category category, String subjectName) {
        super(id, pointTitle, pointDescription, difficultyLevel, priorityLevel, requiredEnergyLevel, category);

        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return super.toString() + ", subjectName='" + subjectName + '\'';
    }
}