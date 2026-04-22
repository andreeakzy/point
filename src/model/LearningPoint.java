package model;

// clasa abstracta de baza pentru toate activitatile de invatare
// implementeaza comparable pentru a putea sorta obiectele in treeset
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

    public LearningPoint(int id, String pointTitle, String pointDescription,
                         DifficultyLevel difficultyLevel, PriorityLevel priorityLevel,
                         EnergyLevel requiredEnergyLevel, Category category) {
        super(id);
        this.pointTitle = pointTitle;
        this.pointDescription = pointDescription;
        this.difficultyLevel = difficultyLevel;
        this.priorityLevel = priorityLevel;
        this.requiredEnergyLevel = requiredEnergyLevel;
        this.category = category;
        this.completed = false;
    }

    // getter pentru titlu
    public String getPointTitle() {
        return pointTitle;
    }

    // setter pentru titlu
    public void setPointTitle(String pointTitle) {
        this.pointTitle = pointTitle;
    }

    // getter pentru descriere
    public String getPointDescription() {
        return pointDescription;
    }

    // setter pentru descriere
    public void setPointDescription(String pointDescription) {
        this.pointDescription = pointDescription;
    }

    // getter pentru dificultate
    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    // setter pentru dificultate
    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    // getter pentru prioritate
    public PriorityLevel getPriorityLevel() {
        return priorityLevel;
    }

    // setter pentru prioritate
    public void setPriorityLevel(PriorityLevel priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    // getter pentru energia necesara
    public EnergyLevel getRequiredEnergyLevel() {
        return requiredEnergyLevel;
    }

    // setter pentru energia necesara
    public void setRequiredEnergyLevel(EnergyLevel requiredEnergyLevel) {
        this.requiredEnergyLevel = requiredEnergyLevel;
    }

    // getter pentru categorie
    public Category getCategory() {
        return category;
    }

    // setter pentru categorie
    public void setCategory(Category category) {
        this.category = category;
    }

    // getter pentru status finalizare
    public boolean isCompleted() {
        return completed;
    }

    // setter pentru status finalizare
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // metoda de comparare folosita automat de treeset
    // ordonam activitatile dupa dificultate, apoi dupa prioritate, apoi alfabetic, apoi dupa id
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
        return getClass().getSimpleName() + "{" +
                "id=" + getId() +
                ", pointTitle='" + pointTitle + '\'' +
                ", pointDescription='" + pointDescription + '\'' +
                ", difficultyLevel=" + difficultyLevel +
                ", priorityLevel=" + priorityLevel +
                ", requiredEnergyLevel=" + requiredEnergyLevel +
                ", category=" + category.getCategoryName() +
                ", completed=" + completed +
                '}';
    }
}