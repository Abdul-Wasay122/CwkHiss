package refdefcwk;

/**
 * Joiner class represents a staff member specialized in joinery work.
 * Joiners can do "Design" and "Installation" jobs, with some able to work with vinyl.
 */
public class Joiner extends Staff {
    private boolean canWorkWithVinyl;
    private static final int JOINER_RETAINER = 300; // All joiners have £300 retainer

    public Joiner(String name, int experience, boolean canWorkWithVinyl) {
        super(name, experience, JOINER_RETAINER);
        this.canWorkWithVinyl = canWorkWithVinyl;
        // Hourly rate is set to 2× experience level in the Staff constructor
    }

    public boolean canWorkWithVinyl() {
        return canWorkWithVinyl;
    }

    
    public boolean canDoJob(Job job) {
        JobType type = job.getType();
        return type == JobType.DESIGN || type == JobType.INSTALLATION;
    }

    @Override
    public String toString() {
        return super.toString() + " (Joiner, Vinyl: " + canWorkWithVinyl + ")";
    }

    @Override
    public String getRole() {
        return "Joiner";
    }

    @Override
    public boolean canDo(JobType type, boolean isTrained) {
        return type == JobType.DESIGN || type == JobType.INSTALLATION;
    }
}