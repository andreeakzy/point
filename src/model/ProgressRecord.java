package model;

import java.time.LocalDate;

// clasa care pastreaza istoricul unei modificari de progres
public class ProgressRecord extends BaseEntity {
    private String trackedItemName;
    private int previousValue;
    private int updatedValue;
    private LocalDate updateDate;
    private String updateNotes;

    public ProgressRecord() {
    }

    public ProgressRecord(int id, String trackedItemName, int previousValue,
                          int updatedValue, LocalDate updateDate, String updateNotes) {
        super(id);
        this.trackedItemName = trackedItemName;
        this.previousValue = previousValue;
        this.updatedValue = updatedValue;
        this.updateDate = updateDate;
        this.updateNotes = updateNotes;
    }

    // getter pentru numele elementului urmarit
    public String getTrackedItemName() {
        return trackedItemName;
    }

    // setter pentru numele elementului urmarit
    public void setTrackedItemName(String trackedItemName) {
        this.trackedItemName = trackedItemName;
    }

    // getter pentru valoarea anterioara
    public int getPreviousValue() {
        return previousValue;
    }

    // setter pentru valoarea anterioara
    public void setPreviousValue(int previousValue) {
        this.previousValue = previousValue;
    }

    // getter pentru valoarea noua
    public int getUpdatedValue() {
        return updatedValue;
    }

    // setter pentru valoarea noua
    public void setUpdatedValue(int updatedValue) {
        this.updatedValue = updatedValue;
    }

    // getter pentru data actualizarii
    public LocalDate getUpdateDate() {
        return updateDate;
    }

    // setter pentru data actualizarii
    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    // getter pentru notite
    public String getUpdateNotes() {
        return updateNotes;
    }

    // setter pentru notite
    public void setUpdateNotes(String updateNotes) {
        this.updateNotes = updateNotes;
    }

    @Override
    public String toString() {
        return "ProgressRecord{" +
                "id=" + getId() +
                ", trackedItemName='" + trackedItemName + '\'' +
                ", previousValue=" + previousValue +
                ", updatedValue=" + updatedValue +
                ", updateDate=" + updateDate +
                ", updateNotes='" + updateNotes + '\'' +
                '}';
    }
}