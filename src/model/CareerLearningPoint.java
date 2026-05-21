package model;

// career/professional learning points track skill development with industry levels and salary impact
public class CareerLearningPoint extends LearningPoint {
    private String skillTrack;                  // main skill being developed
    private String requiredCertifications;      // certifications needed)
    private IndustryLevel industryLevel;        // beginner, intermediate, advanced, expert
    private boolean mentorRequired;             // whether mentorship would help
    private double salaryImpactEstimate;        // estimated salary increase % upon completion

    public CareerLearningPoint() {}

    public CareerLearningPoint(int id, String pointTitle, String pointDescription,
            DifficultyLevel difficultyLevel, PriorityLevel priorityLevel,
            EnergyLevel requiredEnergyLevel, Category category, String skillTrack,
            String requiredCertifications, IndustryLevel industryLevel,
            boolean mentorRequired, double salaryImpactEstimate) {
        super(id, pointTitle, pointDescription, difficultyLevel, priorityLevel, requiredEnergyLevel, category);
        this.skillTrack = skillTrack;
        this.requiredCertifications = requiredCertifications;
        this.industryLevel = industryLevel;
        this.mentorRequired = mentorRequired;
        this.salaryImpactEstimate = salaryImpactEstimate;
    }

    public String getSkillTrack() {
        return skillTrack;
    }

    public void setSkillTrack(String skillTrack) {
        this.skillTrack = skillTrack;
    }

    public String getRequiredCertifications() {
        return requiredCertifications;
    }

    public void setRequiredCertifications(String requiredCertifications) {
        this.requiredCertifications = requiredCertifications;
    }

    public IndustryLevel getIndustryLevel() {
        return industryLevel;
    }

    public void setIndustryLevel(IndustryLevel industryLevel) {
        this.industryLevel = industryLevel;
    }

    public boolean isMentorRequired() {
        return mentorRequired;
    }

    public void setMentorRequired(boolean mentorRequired) {
        this.mentorRequired = mentorRequired;
    }

    public double getSalaryImpactEstimate() {
        return salaryImpactEstimate;
    }

    public void setSalaryImpactEstimate(double salaryImpactEstimate) {
        this.salaryImpactEstimate = salaryImpactEstimate;
    }

    // estimate career progression level based on completion and industry level
    // track career advancement 
    public String estimateCareerProgression() {
        if (!this.isCompleted()) {
            return "In Progress (" + industryLevel + ")";
        }

        String progression = "Completed";
        if (industryLevel == IndustryLevel.EXPERT) {
            progression += " - EXPERT LEVEL reached!";
        } else if (industryLevel == IndustryLevel.ADVANCED) {
            progression += " - Advanced skills acquired";
        } else if (industryLevel == IndustryLevel.INTERMEDIATE) {
            progression += " - Intermediate skills acquired";
        }

        if (salaryImpactEstimate > 0) {
            progression += String.format(" (+%.1f%% salary impact)", salaryImpactEstimate);
        }

        return progression;
    }

    // check if required certifications are satisfied
    public boolean hasPrerequisiteCertifications() {
        // In a real app, this would check against a Learner's certification list
        // For now, return true if there are no required certifications or if completed
        if (requiredCertifications == null || requiredCertifications.isEmpty()) {
            return true;
        }
        return this.isCompleted();
    }

    @Override
    public String toString() {
        return String.format("%s | Skill: %s, Level: %s, Salary Impact: +%.1f%%, Mentor Required: %s",
                super.toString(), skillTrack, industryLevel, salaryImpactEstimate, mentorRequired);
    }
}
