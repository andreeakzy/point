package model;

// LearningPoint e clasa de baza pentru toate tipurile de learning activities
// comparable pt treeset

public abstract class LearningPoint extends BaseEntity implements Comparable<LearningPoint> {

    private String pointTitle;
    private String pointDescription;
    private DifficultyLevel difficultyLevel;
    private PriorityLevel priorityLevel;
    private EnergyLevel requiredEnergyLevel;
    private Category category;
    private boolean completed;

    public LearningPoint() {
    }

    public LearningPoint(int id, String pointTitle, String pointDescription, DifficultyLevel difficultyLevel, PriorityLevel priorityLevel, EnergyLevel requiredEnergyLevel, Category category) {
        super(id);
        this.pointTitle = pointTitle;
        this.pointDescription = pointDescription;
        this.difficultyLevel = difficultyLevel;
        this.priorityLevel = priorityLevel;
        this.requiredEnergyLevel = requiredEnergyLevel;
        this.category = category;
        this.completed = false;
    }

    public String getPointTitle() {
        return pointTitle;
    }

    public void setPointTitle(String pointTitle) {
        this.pointTitle = pointTitle;
    }

    public String getPointDescription() {
        return pointDescription;
    }

    public void setPointDescription(String pointDescription) {
        this.pointDescription = pointDescription;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public PriorityLevel getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(PriorityLevel priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public EnergyLevel getRequiredEnergyLevel() {
        return requiredEnergyLevel;
    }

    public void setRequiredEnergyLevel(EnergyLevel requiredEnergyLevel) {
        this.requiredEnergyLevel = requiredEnergyLevel;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // ordonare activities
    @Override
    public int compareTo(LearningPoint otherPoint) {
        int difficultyCompare = Integer.compare(this.difficultyLevel.ordinal(), otherPoint.difficultyLevel.ordinal());

        if (difficultyCompare != 0) {
            return difficultyCompare;
        }

        int priorityCompare = Integer.compare(otherPoint.priorityLevel.ordinal(), this.priorityLevel.ordinal());
        if (priorityCompare != 0) {
            return priorityCompare;
        }

        int titleCompare = this.pointTitle.compareToIgnoreCase(otherPoint.pointTitle);
        if (titleCompare != 0) {
            return titleCompare;
        }

        return Integer.compare(this.getId(), otherPoint.getId());
    }

    @Override
    public String toString() {
        return String.format("%s: %s (ID: %d) - %s. Difficulty: %s, Priority: %s, Energy: %s, Category: %s, Completed: %s",
                getClass().getSimpleName(), pointTitle, getId(), pointDescription, difficultyLevel, priorityLevel, requiredEnergyLevel, category.getCategoryName(), completed);
    };
    }