package model;

import java.time.LocalDate;

// clasa care descrie un obiectiv de invatare
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

    // getter pentru titlul obiectivului
    public String getMilestoneTitle() {
        return milestoneTitle;
    }

    // setter pentru titlul obiectivului
    public void setMilestoneTitle(String milestoneTitle) {
        this.milestoneTitle = milestoneTitle;
    }

    // getter pentru descrierea obiectivului
    public String getMilestoneDescription() {
        return milestoneDescription;
    }

    // setter pentru descrierea obiectivului
    public void setMilestoneDescription(String milestoneDescription) {
        this.milestoneDescription = milestoneDescription;
    }

    // getter pentru valoarea tinta
    public int getTargetValue() {
        return targetValue;
    }

    // setter pentru valoarea tinta
    public void setTargetValue(int targetValue) {
        this.targetValue = targetValue;
        this.completed = this.currentValue >= this.targetValue;
    }

    // getter pentru valoarea curenta
    public int getCurrentValue() {
        return currentValue;
    }

    // setter pentru progresul curent
    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
        this.completed = this.currentValue >= this.targetValue;
    }

    // getter pentru deadline
    public LocalDate getDeadline() {
        return deadline;
    }

    // setter pentru deadline
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    // getter pentru status finalizare
    public boolean isCompleted() {
        return completed;
    }

    // metoda care calculeaza procentul de progres
    public double getCompletionPercentage() {
        if (targetValue == 0) {
            return 0;
        }
        return (double) currentValue * 100 / targetValue;
    }

    @Override
    public String toString() {
        return "Milestone{" +
                "id=" + getId() +
                ", milestoneTitle='" + milestoneTitle + '\'' +
                ", milestoneDescription='" + milestoneDescription + '\'' +
                ", targetValue=" + targetValue +
                ", currentValue=" + currentValue +
                ", deadline=" + deadline +
                ", completed=" + completed +
                ", completionPercentage=" + String.format("%.2f", getCompletionPercentage()) + "%" +
                '}';
    }
}