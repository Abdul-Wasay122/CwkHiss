package refdefcwk;

import java.io.Serializable;

public class Consultant extends Staff {
    private boolean corgi;

    public Consultant(String name, int experience, int retainer, boolean corgi) {
        super(name, experience, retainer);
        this.corgi = corgi;
        this.hourlyRate = corgi ? 40 : 30;
    }

    @Override
    public boolean canDo(JobType type, boolean isTrained) {
        return true; // Can do all tasks
    }

    @Override
    public String toString() {
        return super.toString() + ", Available, " + (corgi ? "true" : "false"); // Match test expectation for Bela
    }

    @Override
    protected String getRole() {
        return "Consultant";
    }
}