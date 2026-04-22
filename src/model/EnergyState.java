package model;

import java.time.LocalDate;

// clasa care descrie energia utilizatorului intr-o anumita zi
public class EnergyState extends BaseEntity {
    private LocalDate energyDate;
    private EnergyLevel energyLevel;
    private String stateNotes;

    public EnergyState() {
    }

    public EnergyState(int id, LocalDate energyDate, EnergyLevel energyLevel, String stateNotes) {
        super(id);
        this.energyDate = energyDate;
        this.energyLevel = energyLevel;
        this.stateNotes = stateNotes;
    }

    // getter pentru data la care a fost setata energia
    public LocalDate getEnergyDate() {
        return energyDate;
    }

    // setter pentru data energiei
    public void setEnergyDate(LocalDate energyDate) {
        this.energyDate = energyDate;
    }

    // getter pentru nivelul de energie
    public EnergyLevel getEnergyLevel() {
        return energyLevel;
    }

    // setter pentru nivelul de energie
    public void setEnergyLevel(EnergyLevel energyLevel) {
        this.energyLevel = energyLevel;
    }

    // getter pentru notite
    public String getStateNotes() {
        return stateNotes;
    }

    // setter pentru notite
    public void setStateNotes(String stateNotes) {
        this.stateNotes = stateNotes;
    }

    @Override
    public String toString() {
        return "EnergyState{" +
                "id=" + getId() +
                ", energyDate=" + energyDate +
                ", energyLevel=" + energyLevel +
                ", stateNotes='" + stateNotes + '\'' +
                '}';
    }
}