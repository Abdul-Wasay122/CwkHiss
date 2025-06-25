package refdefcwk;

import java.util.Scanner;

public class ManagerUI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter project budget: ");
        int budget = Integer.parseInt(sc.nextLine());

        Manager manager = new Manager(name, budget);
        System.out.println("Welcome, " + name + "!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. List all staff available");
            System.out.println("2. Hire staff");
            System.out.println("3. Show account balance");
            System.out.println("4. List team");
            System.out.println("5. List all jobs");
            System.out.println("6. Do a job");
            System.out.println("7. Show staff details");
            System.out.println("8. Staff rejoin team");
            System.out.println("9. Save project");
            System.out.println("10. Project Information");
            System.out.println("11. Load project");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println(manager.getAllAvailableStaff());
                    break;
                case "2":
                    System.out.print("Enter staff name to hire: ");
                    String hireName = sc.nextLine();
                    System.out.println(manager.hireStaff(hireName));
                    break;
                case "3":
                    System.out.println("Account: £" + String.valueOf(manager.getAccount()));
                    break;
                case "4":
                    System.out.println(manager.getTeam());
                    break;
                case "5":
                    System.out.println(manager.getAllJobs());
                    break;
                case "6":
                    System.out.print("Enter job number: ");
                    int jobNo = Integer.parseInt(sc.nextLine());
                    System.out.println(manager.doJob(jobNo));
                    break;
                case "7":
                    System.out.print("Enter staff name: ");
                    String detailName = sc.nextLine();
                    System.out.println(manager.getStaff(detailName));
                    break;
                case "8":
                    System.out.print("Enter staff name to rejoin team: ");
                    String rejoin = sc.nextLine();
                    System.out.println(manager.staffRejoinTeam(rejoin));
                    break;
                case "9":
                    System.out.print("Enter filename to save to: ");
                    String saveFile = sc.nextLine();
                    manager.saveManager(saveFile);
                    System.out.println("Project saved.");
                    break;
                case "10": // task 5 
                    System.out.println(manager.toString());
                    break;
                case "11": 
                    System.out.print("Enter filename to load from: ");
                    String loadFile = sc.nextLine();
                    Manager loaded = Manager.restoreManager(loadFile);
                    if (loaded != null) {
                        manager = loaded;
                        System.out.println("Project loaded.");
                    }
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}