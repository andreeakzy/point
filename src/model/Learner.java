package model;

public class Learner extends BaseEntity {

    private String fullName;
    private String emailAddress;

    public Learner() {
    }

    public Learner(int id, String fullName, String emailAddress) {
        super(id);
        this.fullName = fullName;
        this.emailAddress = emailAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return String.format("Learner: %s (ID: %d) - Email: %s", fullName, getId(), emailAddress);
    }
}
