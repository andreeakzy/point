package model;

import java.time.LocalDate;

// aici progresul utilizatorului
public class ProgressRecord extends BaseEntity {

    private String trackedItemName;
    private int previousValue;
    private int updatedValue;
    private LocalDate updateDate;
    private String updateNotes;

    public ProgressRecord() {
    }

    public ProgressRecord(int id, String trackedItemName, int previousValue, int updatedValue, LocalDate updateDate, String updateNotes) {
        super(id);
        this.trackedItemName = trackedItemName;
        this.previousValue = previousValue;
        this.updatedValue = updatedValue;
        this.updateDate = updateDate;
        this.updateNotes = updateNotes;
    }

    public String getTrackedItemName() {
        return trackedItemName;
    }

    public void setTrackedItemName(String trackedItemName) {
        this.trackedItemName = trackedItemName;
    }

    public int getPreviousValue() {
        return previousValue;
    }

    public void setPreviousValue(int previousValue) {
        this.previousValue = previousValue;
    }

    public int getUpdatedValue() {
        return updatedValue;
    }

    public void setUpdatedValue(int updatedValue) {
        this.updatedValue = updatedValue;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateNotes() {
        return updateNotes;
    }

    public void setUpdateNotes(String updateNotes) {
        this.updateNotes = updateNotes;
    }

    @Override
    public String toString() {
        return "ProgressRecord{"
                + "id=" + getId()
                + ", trackedItemName='" + trackedItemName + '\''
                + ", previousValue=" + previousValue
                + ", updatedValue=" + updatedValue
                + ", updateDate=" + updateDate
                + ", updateNotes='" + updateNotes + '\''
                + '}';
    }
}
