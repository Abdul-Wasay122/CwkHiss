package refdefcwk;

public interface HISS {
    double getAccount();
    boolean isOverdrawn();
    boolean isJob(int num);
    String getAllJobs();
    String getJob(int no);
    String getStaff(String name);
    String getAllAvailableStaff();
    String hireStaff(String name);
    boolean isInTeam(String name);
    String getTeam();
    String doJob(int jbNo);
    String staffRejoinTeam(String name);

       // New methods for Task 4
    String getPlayer();
    String getExpensiveStaff();
}