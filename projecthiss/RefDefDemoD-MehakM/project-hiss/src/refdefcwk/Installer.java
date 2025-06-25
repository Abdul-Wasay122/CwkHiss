package refdefcwk;

import java.io.Serializable;

public class Installer extends Staff {
    private boolean trained;

    public Installer(String name, int experience, boolean trained) {
        super(name, experience, 200);
        this.trained = trained;
        this.hourlyRate = 20;
    }

    @Override
    public boolean canDo(JobType type, boolean isTrained) {
        return type == JobType.INSTALLATION || (type == JobType.MAINTENANCE && trained);
    }

    public boolean isTrained() {
        return trained;
    }

    @Override
    public String toString() {
        return super.toString() + ", Available"; // Match test expectation for Eli
    }

    @Override
    protected String getRole() {
        return "Installer";
    }
}