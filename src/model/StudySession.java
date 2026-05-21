package model;

import java.time.LocalDate;

// descrie un study session asociat unui learning point
public class StudySession extends BaseEntity {

    private LearningPoint linkedPoint;
    private LocalDate sessionDate;
    private int plannedMinutes;
    private SessionStatus sessionStatus;
    private String sessionNotes;

    public StudySession() {
    }

    public StudySession(int id, LearningPoint linkedPoint, LocalDate sessionDate, int plannedMinutes, SessionStatus sessionStatus, String sessionNotes) {
        super(id);
        this.linkedPoint = linkedPoint;
        this.sessionDate = sessionDate;
        this.plannedMinutes = plannedMinutes;
        this.sessionStatus = sessionStatus;
        this.sessionNotes = sessionNotes;
    }

    public LearningPoint getLinkedPoint() {
        return linkedPoint;
    }

    public void setLinkedPoint(LearningPoint linkedPoint) {
        this.linkedPoint = linkedPoint;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    public int getPlannedMinutes() {
        return plannedMinutes;
    }

    public void setPlannedMinutes(int plannedMinutes) {
        this.plannedMinutes = plannedMinutes;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public String getSessionNotes() {
        return sessionNotes;
    }

    public void setSessionNotes(String sessionNotes) {
        this.sessionNotes = sessionNotes;
    }

    @Override
    public String toString() {
        return "StudySession{"
                + "id=" + getId()
                + ", linkedPoint=" + linkedPoint.getPointTitle()
                + ", sessionDate=" + sessionDate
                + ", plannedMinutes=" + plannedMinutes
                + ", sessionStatus=" + sessionStatus
                + ", sessionNotes='" + sessionNotes + '\''
                + '}';
    }
}
