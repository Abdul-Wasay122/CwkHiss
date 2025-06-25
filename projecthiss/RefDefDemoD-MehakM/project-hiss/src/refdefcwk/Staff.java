package refdefcwk;

import java.io.Serializable;

public abstract class Staff implements Serializable {
    protected String name;
    protected int experience;
    protected int retainer;
    protected int hourlyRate;
    protected StaffState state;

    public Staff(String name, int experience, int retainer) {
        this.name = name;
        this.experience = experience;
        this.retainer = retainer;
        this.hourlyRate = 0; // Set by subclasses
        this.state = StaffState.AVAILABLE;
    }

    public String getName() { return name; }
    public int getExperience() { return experience; }
    public int getRetainer() { return retainer; }
    public int getHourlyRate() { return hourlyRate; }
    public StaffState getState() { return state; }
    public void setState(StaffState state) { this.state = state; }

    public abstract boolean canDo(JobType type, boolean isTrained);

    @Override
    public String toString() {
        return name + "," + getRole() + "," + experience + "," + retainer + "," + hourlyRate;
    }

    protected abstract String getRole();
}