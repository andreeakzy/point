package model;

import java.time.LocalDate;

//energia utilizatorului intr-o anumita zi
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

    public LocalDate getEnergyDate() {
        return energyDate;
    }

    public void setEnergyDate(LocalDate energyDate) {
        this.energyDate = energyDate;
    }

    public EnergyLevel getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(EnergyLevel energyLevel) {
        this.energyLevel = energyLevel;
    }

    public String getStateNotes() {
        return stateNotes;
    }

    public void setStateNotes(String stateNotes) {
        this.stateNotes = stateNotes;
    }

    @Override
    public String toString() {
        return String.format("EnergyState: %s (ID: %d) - Energy Level: %s, Notes: %s", energyDate, getId(), energyLevel, stateNotes);
    }
}
