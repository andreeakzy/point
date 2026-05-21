package model;

import java.time.LocalDate;

// descrie un obiectiv de invatare
public class Milestone extends BaseEntity {

    private String milestoneTitle;
    private String milestoneDescription;
    private int targetValue;
    private int currentValue;
    private LocalDate deadline;
    private boolean completed;

    public Milestone() {
    }

    public Milestone(int id, String milestoneTitle, String milestoneDescription,
            int targetValue, int currentValue, LocalDate deadline) {
        super(id);
        this.milestoneTitle = milestoneTitle;
        this.milestoneDescription = milestoneDescription;
        this.targetValue = targetValue;
        this.currentValue = currentValue;
        this.deadline = deadline;
        this.completed = currentValue >= targetValue;
    }

    public String getMilestoneTitle() {
        return milestoneTitle;
    }

    public void setMilestoneTitle(String milestoneTitle) {
        this.milestoneTitle = milestoneTitle;
    }

    public String getMilestoneDescription() {
        return milestoneDescription;
    }

    public void setMilestoneDescription(String milestoneDescription) {
        this.milestoneDescription = milestoneDescription;
    }

    public int getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(int targetValue) {
        this.targetValue = targetValue;
        this.completed = this.currentValue >= this.targetValue;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
        this.completed = this.currentValue >= this.targetValue;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean isCompleted() {
        return completed;
    }

    public double getCompletionPercentage() {
        if (targetValue == 0) {
            return 0;
        }
        return (double) currentValue * 100 / targetValue;
    }

    @Override
    public String toString() {
        return "Milestone{"
                + "id=" + getId()
                + ", milestoneTitle='" + milestoneTitle + '\''
                + ", milestoneDescription='" + milestoneDescription + '\''
                + ", targetValue=" + targetValue
                + ", currentValue=" + currentValue
                + ", deadline=" + deadline
                + ", completed=" + completed
                + ", completionPercentage=" + String.format("%.2f", getCompletionPercentage()) + "%"
                + '}';
    }
}
