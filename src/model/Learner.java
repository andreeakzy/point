package model;

// clasa care reprezinta utilizatorul aplicatiei point
public class Learner extends BaseEntity {
    private String fullName;
    private String emailAddress;
    private String growthDirection;

    public Learner() {
    }

    public Learner(int id, String fullName, String emailAddress, String growthDirection) {
        super(id);
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.growthDirection = growthDirection;
    }

    // getter pentru numele complet
    public String getFullName() {
        return fullName;
    }

    // setter pentru numele complet
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // getter pentru email
    public String getEmailAddress() {
        return emailAddress;
    }

    // setter pentru email
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    // getter pentru directia de crestere
    public String getGrowthDirection() {
        return growthDirection;
    }

    // setter pentru directia de crestere
    public void setGrowthDirection(String growthDirection) {
        this.growthDirection = growthDirection;
    }

    @Override
    public String toString() {
        return "Learner{" +
                "id=" + getId() +
                ", fullName='" + fullName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", growthDirection='" + growthDirection + '\'' +
                '}';
    }
}