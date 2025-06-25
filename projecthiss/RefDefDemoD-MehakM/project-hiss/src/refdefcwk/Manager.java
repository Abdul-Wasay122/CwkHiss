package refdefcwk;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * tm1 : @name : Angel Thomas
 * @student id : 22052408
 * tm2 : @name : mehak maan singh 
 *  @student id : 22065819
 * Manager class implements the HISS interface to manage a household installation service simulation.
 * It handles staff hiring, job assignments, project account, and data persistence.
 */
public class Manager implements HISS, Serializable {

    private static final long serialVersionUID = 1L;

    private String managerName;
    private int projectAccount;
    private Map<String, Staff> allStaff;
    private Map<String, Staff> team;
    private Map<Integer, Job> jobs;

    /**
     * Constructor to create a new Manager with a default staff and job setup.
     * 
     * @param name   the name of the manager
     * @param budget the initial project account balance
     */
    public Manager(String name, int budget) {
        this.managerName = name;
        this.projectAccount = budget;
        this.allStaff = new LinkedHashMap<>();
        this.team = new LinkedHashMap<>();
        this.jobs = new LinkedHashMap<>();
        setUpStaff();
        setUpJobs();
    }

    /**
     * Constructor that initializes the manager with staff and loads jobs from a file.
     * 
     * @param name     the manager's name
     * @param budget   the budget to initialize the project account
     * @param filename the filename from which jobs will be read
     */
    public Manager(String name, int budget, String filename) {
        this.managerName = name;
        this.projectAccount = budget;
        this.allStaff = new LinkedHashMap<>();
        this.team = new LinkedHashMap<>();
        this.jobs = new LinkedHashMap<>();
        setUpStaff();
        readJobs(filename);
    }

    /**
     * Initializes the staff list with predefined staff members.
     */
    private void setUpStaff() {
        allStaff.put("Amir", new Planner("Amir", 2, "Homebase"));
        allStaff.put("Bela", new Consultant("Bela", 2, 100, false));
        allStaff.put("Ceri", new Consultant("Ceri", 4, 250, true));
        allStaff.put("Dana", new Installer("Dana", 2, false));
        allStaff.put("Eli", new Installer("Eli", 7, true));
        allStaff.put("Firat", new Planner("Firat", 6, "Hygena"));
        allStaff.put("Gani", new Installer("Gani", 2, true));
        allStaff.put("Hui", new Consultant("Hui", 8, 450, true));
        allStaff.put("Jaga", new Planner("Jaga", 4, "Homebase"));
        //task 2.2
        
          allStaff.put("Arpad", new Planner("Arpad", 4, "Magnet"));
        //task 3.2
        
           allStaff.put("Witek", new Joiner("Witek", 4, false));
    }

    /**
     * Sets up the default set of jobs.
     */
    private void setUpJobs() {
        jobs.put(1000, new Job(1000, JobType.DESIGN, "Kitchen", 3, 10, 200));
        jobs.put(1001, new Job(1001, JobType.MAINTENANCE, "Lounge", 3, 20, 150));
        jobs.put(1002, new Job(1002, JobType.INSTALLATION, "Kitchen", 3, 30, 100));
        jobs.put(1003, new Job(1003, JobType.DESIGN, "Bathroom", 9, 25, 250));
        jobs.put(1004, new Job(1004, JobType.INSTALLATION, "Lounge", 7, 15, 350));
        jobs.put(1005, new Job(1005, JobType.MAINTENANCE, "Kitchen", 8, 35, 300));
        jobs.put(1006, new Job(1006, JobType.MAINTENANCE, "Bathroom", 5, 20, 400));
        jobs.put(1007, new Job(1007, JobType.INSTALLATION, "Bathroom", 1, 5, 120));
        jobs.put(1008, new Job(1008, JobType.DESIGN, "Kitchen", 1, 8, 175));
       
        //task2.1
        
        jobs.put(1009, new Job(1009, JobType.INSTALLATION, "Bedroom", 7, 20, 400));
    }

    /**
     * Reads job data from a file and populates the job list.
     * 
     * @param filename the file containing job data
     */
    public void readJobs(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    int jobNo = Integer.parseInt(parts[0].trim());
                    JobType type = JobType.valueOf(parts[1].trim().toUpperCase());
                    String location = parts[2].trim();
                    int expRequired = Integer.parseInt(parts[3].trim());
                    int hours = Integer.parseInt(parts[4].trim());
                    int penalty = Integer.parseInt(parts[5].trim());
                    jobs.put(jobNo, new Job(jobNo, type, location, expRequired, hours, penalty));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading jobs file: " + e.getMessage());
        }
    }

    /**
     * Returns a string representation of the manager's status.
     * 
     * @return summary of manager, account status, and team
     */
    @Override
    public String toString() {
        String teamStatus = team.isEmpty() ? "No staff" : team.values().stream()
                .map(Staff::toString)
                .collect(Collectors.joining("\n"));
        String overdrawn = isOverdrawn() ? " (ITProject is overdrawn)" : "";
        return "Manager: " + managerName + ", Account: £" + projectAccount + overdrawn + "\nTeam:\n" + teamStatus;
    }

    /**
     * Returns the current value of the project account.
     * 
     * @return project account balance
     */
    @Override
    public double getAccount() {
        return projectAccount;
    }

    /**
     * Checks whether the account is overdrawn and no staff is on leave.
     * 
     * @return true if overdrawn with no staff on leave, false otherwise
     */
    @Override
    public boolean isOverdrawn() {
        boolean hasLeaveableStaff = team.values().stream().anyMatch(s -> s.getState() == StaffState.ON_LEAVE);
        return projectAccount <= 0 && !hasLeaveableStaff;
    }

    /**
     * Checks if a job exists.
     * 
     * @param num the job number
     * @return true if the job exists, false otherwise
     */
    @Override
    public boolean isJob(int num) {
        return jobs.containsKey(num);
    }

    /**
     * Retrieves all available jobs as a formatted string.
     * 
     * @return string list of all jobs
     */
    @Override
    public String getAllJobs() {
        return jobs.isEmpty() ? "No jobs available." :
               jobs.values().stream()
                   .map(Job::toString)
                   .collect(Collectors.joining("\n"));
    }

    /**
     * Retrieves details for a specific job.
     * 
     * @param no job number
     * @return job description or error if not found
     */
    @Override
    public String getJob(int no) {
        Job job = jobs.get(no);
        return job != null ? job.toString() : "No such job: " + no;
    }

    /**
     * Gets details about a specific staff member.
     * 
     * @param name the name of the staff
     * @return details about the staff member
     */
    @Override
    public String getStaff(String name) {
        Staff staff = allStaff.get(name);
        if (staff == null) {
            return "No such staff: " + name;
        }
        String status = team.containsKey(name) ? staff.getState().toString() : "Available";
        return staff.toString() + ", " + status + (staff instanceof Planner ? ", " + ((Planner) staff).getMake() : "");
    }

    /**
     * Lists all staff members who are available for hire.
     * 
     * @return formatted list of available staff
     */
    @Override
    public String getAllAvailableStaff() {
        return allStaff.entrySet().stream()
                .filter(entry -> !team.containsKey(entry.getKey()))
                .map(entry -> entry.getValue().toString() + ", Available")
                .collect(Collectors.joining("\n"));
    }

    /**
     * Hires a staff member and deducts the retainer from the project account.
     * 
     * @param name the staff member's name
     * @return hiring result message
     */
    @Override
    public String hireStaff(String name) {
        Staff staff = allStaff.get(name);
        if (staff == null) {
            return "Not found: " + name + ", Account: £" + projectAccount;
        }
        if (team.containsKey(name)) {
            return "Already hired: " + name + ", Account: £" + projectAccount;
        }
        if (projectAccount < staff.getRetainer()) {
            return "Not enough money: " + name + ", Account: £" + projectAccount;
        }
        team.put(name, staff);
        staff.setState(StaffState.WORKING);
        projectAccount -= staff.getRetainer();
        return "Hired: " + name + ", Account: £" + projectAccount;
    }

    /**
     * Checks if a staff member is currently hired in the team.
     * 
     * @param name the staff member's name
     * @return true if in the team, false otherwise
     */
    @Override
    public boolean isInTeam(String name) {
        return team.containsKey(name);
    }

    /**
     * Returns the current list of hired staff.
     * 
     * @return team description
     */
    @Override
    public String getTeam() {
        return team.isEmpty() ? "No staff hired" :
               team.values().stream()
                   .map(Staff::toString)
                   .collect(Collectors.joining("\n"));
    }

    /**
     * Attempts to assign a job to a staff member.
     * 
     * @param jbNo the job number
     * @return result of the job attempt
     */
    @Override
    public String doJob(int jbNo) {
        Job job = jobs.get(jbNo);
        if (job == null) {
            return "No such job: " + jbNo;
        }

        for (Staff staff : team.values()) {
            if (staff.getState() != StaffState.WORKING) {
                continue;
            }

            if (!canStaffPerformJob(staff, job)) {
                continue;
            }

            if (staff.getExperience() < job.getExperienceRequired()) {
                projectAccount -= job.getPenalty();
                String overdrawnMsg = isOverdrawn() ? " (ITProject is overdrawn)" : "";
                return "Job not completed due to staff inexperience: " + jbNo +
                       ", Penalty: £" + job.getPenalty() + ", Account: £" + projectAccount + overdrawnMsg;
            }

            int earnings = job.getHours() * staff.getHourlyRate();
            projectAccount += earnings;
            staff.setState(StaffState.ON_LEAVE);
            return "Job completed by " + staff.getName() + ": " + jbNo +
                   ", Earned: £" + earnings + ", Account: £" + projectAccount;
        }

        projectAccount -= job.getPenalty();
        String overdrawnMsg = isOverdrawn() ? " (ITProject is overdrawn)" : "";
        return "Job not completed as no staff available: " + jbNo +
               ", Penalty: £" + job.getPenalty() + ", Account: £" + projectAccount + overdrawnMsg;
    }

    /**
     * Determines if a staff member is eligible to perform a job.
     * 
     * @param staff the staff member
     * @param job   the job to check
     * @return true if the staff can perform the job, false otherwise
     */
    private boolean canStaffPerformJob(Staff staff, Job job) {
        JobType jobType = job.getType();
        if (staff instanceof Planner) {
            return jobType == JobType.DESIGN;
        } else if (staff instanceof Installer) {
            Installer installer = (Installer) staff;
            return jobType == JobType.INSTALLATION || (jobType == JobType.MAINTENANCE && installer.isTrained());
        } else if (staff instanceof Consultant) {
            return true;
        }
        return false;
    }

    /**
     * Allows a staff member who was on leave to rejoin the working team.
     * 
     * @param name the name of the staff member
     * @return result message of rejoining
     */
    @Override
    public String staffRejoinTeam(String name) {
        Staff staff = team.get(name);
        if (staff == null) {
            return name + " is not in the team.";
        }
        if (staff.getState() != StaffState.ON_LEAVE) {
            return name + " is not on leave.";
        }
        staff.setState(StaffState.WORKING);
        return name + " has rejoined the team.";
    }

    /**
     * Saves the current manager object to a file.
     * 
     * @param filename the file to save to
     */
    public void saveManager(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
        } catch (IOException e) {
            System.err.println("Failed to save manager: " + e.getMessage());
        }
    }

    /**
     * Restores a manager object from a saved file.
     * 
     * @param filename the file to read from
     * @return the restored Manager object, or null if failure occurs
     */
    public static Manager restoreManager(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Manager) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to restore manager: " + e.getMessage());
            return null;
        }
    }

//task 4.1
    @Override
public String getPlayer() {
    return this.managerName;
}

/**
 * Returns details of all staff with retainer >= 400
 * @return formatted string of expensive staff details
 */
@Override
public String getExpensiveStaff() {
    StringBuilder result = new StringBuilder();
    for (Staff staff : allStaff.values()) {
        if (staff.getRetainer() >= 400) {
            result.append(staff.toString())
                  .append(", Retainer: £")
                  .append(staff.getRetainer())
                  .append("\n");
        }
    }
    return result.length() > 0 ? result.toString() : "No staff with retainer >= £400";
}
}
