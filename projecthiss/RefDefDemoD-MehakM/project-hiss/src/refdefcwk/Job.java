package refdefcwk;

import java.io.Serializable;

public class Job implements Serializable {
    private int jobNo;
    private JobType type;
    private String location;
    private int experienceRequired;
    private int hours;
    private int penalty;

    public Job(int jobNo, JobType type, String location, int experienceRequired, int hours, int penalty) {
        this.jobNo = jobNo;
        this.type = type;
        this.location = location;
        this.experienceRequired = experienceRequired;
        this.hours = hours;
        this.penalty = penalty;
    }

    public int getJobNo() { return jobNo; }
    public JobType getType() { return type; }
    public String getLocation() { return location; }
    public int getExperienceRequired() { return experienceRequired; }
    public int getHours() { return hours; }
    public int getPenalty() { return penalty; }

    @Override
    public String toString() {
        return jobNo + "," + type + "," + location + "," + experienceRequired + "," + hours + "," + penalty;
    }
}