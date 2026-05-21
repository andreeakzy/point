package model;

// academic learning points track formal education with credits, assessments, and grade targets

public class AcademicLearningPoint extends LearningPoint {
    private String subjectName;
    private double creditPoints;              // ECTS credits or equivalent
    private AssessmentType assessmentType;    // Type of evaluation (exam, project, etc.)
    private String targetGrade;               // Expected grade (A, B+, C, etc.)

    public AcademicLearningPoint() {}

    public AcademicLearningPoint(int id, String pointTitle, String pointDescription, 
            DifficultyLevel difficultyLevel, PriorityLevel priorityLevel, 
            EnergyLevel requiredEnergyLevel, Category category, String subjectName,
            double creditPoints, AssessmentType assessmentType, String targetGrade) {
        super(id, pointTitle, pointDescription, difficultyLevel, priorityLevel, requiredEnergyLevel, category);
        this.subjectName = subjectName;
        this.creditPoints = creditPoints;
        this.assessmentType = assessmentType;
        this.targetGrade = targetGrade;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public double getCreditPoints() {
        return creditPoints;
    }

    public void setCreditPoints(double creditPoints) {
        this.creditPoints = creditPoints;
    }

    public AssessmentType getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(AssessmentType assessmentType) {
        this.assessmentType = assessmentType;
    }

    public String getTargetGrade() {
        return targetGrade;
    }

    public void setTargetGrade(String targetGrade) {
        this.targetGrade = targetGrade;
    }

    // calculate credits earned based on performance percentage
    public double calculateCreditsEarned(int scorePercentage) {
        if (scorePercentage < 50) {
            return 0;  // failed - no credits
        } else if (scorePercentage < 60) {
            return creditPoints * 0.5;  // 50% credits for poor pass
        } else if (scorePercentage < 80) {
            return creditPoints * 0.85;  // 85% credits for good performance
        } else {
            return creditPoints;  // full credits for excellent
        }
    }

    // check if assessment is complete (academic point must be marked as completed)

    public boolean isAssessmentComplete() {
        return this.isCompleted();
    }

    @Override
    public String toString() {
        return String.format("%s | Subject: %s, Credits: %.1f, Assessment: %s, Target: %s",
                super.toString(), subjectName, creditPoints, assessmentType, targetGrade);
    }
}