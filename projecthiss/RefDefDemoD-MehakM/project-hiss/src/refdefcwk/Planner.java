package refdefcwk;

import java.io.Serializable;

public class Planner extends Staff {
    private String make;

    public Planner(String name, int experience, String make) {
        super(name, experience, 300);
        this.make = make;
        this.hourlyRate = experience * 15;
    }

    @Override
    public boolean canDo(JobType type, boolean isTrained) {
        return type == JobType.DESIGN;
    }

    public String getMake() {
        return make;
    }

    @Override
    public String toString() {
        return super.toString() + ", Available, " + make; // Match test expectation for Amir
    }

    @Override
    protected String getRole() {
        return "Planner";
    }
}