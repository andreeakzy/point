package model;

import java.time.LocalDate;

// clasa care descrie o sesiune concreta de studiu
public class StudySession extends BaseEntity {
    private LearningPoint linkedPoint;
    private LocalDate sessionDate;
    private int plannedMinutes;
    private SessionStatus sessionStatus;
    private String sessionNotes;

    public StudySession() {
    }

    public StudySession(int id, LearningPoint linkedPoint, LocalDate sessionDate,
                        int plannedMinutes, SessionStatus sessionStatus, String sessionNotes) {
        super(id);
        this.linkedPoint = linkedPoint;
        this.sessionDate = sessionDate;
        this.plannedMinutes = plannedMinutes;
        this.sessionStatus = sessionStatus;
        this.sessionNotes = sessionNotes;
    }

    // getter pentru activitatea asociata
    public LearningPoint getLinkedPoint() {
        return linkedPoint;
    }

    // setter pentru activitatea asociata
    public void setLinkedPoint(LearningPoint linkedPoint) {
        this.linkedPoint = linkedPoint;
    }

    // getter pentru data sesiunii
    public LocalDate getSessionDate() {
        return sessionDate;
    }

    // setter pentru data sesiunii
    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    // getter pentru minutele planificate
    public int getPlannedMinutes() {
        return plannedMinutes;
    }

    // setter pentru minutele planificate
    public void setPlannedMinutes(int plannedMinutes) {
        this.plannedMinutes = plannedMinutes;
    }

    // getter pentru statusul sesiunii
    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    // setter pentru statusul sesiunii
    public void setSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    // getter pentru notitele sesiunii
    public String getSessionNotes() {
        return sessionNotes;
    }

    // setter pentru notitele sesiunii
    public void setSessionNotes(String sessionNotes) {
        this.sessionNotes = sessionNotes;
    }

    @Override
    public String toString() {
        return "StudySession{" +
                "id=" + getId() +
                ", linkedPoint=" + linkedPoint.getPointTitle() +
                ", sessionDate=" + sessionDate +
                ", plannedMinutes=" + plannedMinutes +
                ", sessionStatus=" + sessionStatus +
                ", sessionNotes='" + sessionNotes + '\'' +
                '}';
    }
}