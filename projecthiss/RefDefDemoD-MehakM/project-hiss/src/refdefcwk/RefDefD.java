package refdefcwk;
/**
 *@ name : mehak maan singh
 * @student id :  22065819
 * @name : Angel Thomas 
 * 
 */
public class RefDefD {

    public static void main(String[] args) {
        // Create simulation using polymorphic reference
        HISS hisDemo = new Manager("Stan", 770);

        
        System.out.println("Hiring Firat");
        System.out.println(hisDemo.hireStaff("Ceri"));
        System.out.println(hisDemo.getAccount());

        
        System.out.println("Hiring Ceri");
        System.out.println(hisDemo.hireStaff("Ceri"));
        System.out.println( hisDemo.getAccount());

        System.out.println("Hiring Zac");
        System.out.println(hisDemo.hireStaff("Zac"));
        System.out.println(+ hisDemo.getAccount());

        System.out.println("Hiring Eli");
        System.out.println(hisDemo.hireStaff("Eli"));
        System.out.println(hisDemo.getAccount());

        System.out.println("Hiring Hui");
System.out.println(hisDemo.hireStaff("Hui"));
System.out.println("Account after hiring Hui: £" + hisDemo.getAccount());



        
        // 1.5 Do jobs in order: 1002, 1003, 1001, 1002
        System.out.println("\nPerforming jobs:");
        System.out.println(hisDemo.doJob(1002));
        System.out.println(hisDemo.doJob(1003));
        System.out.println(hisDemo.doJob(1001));
        System.out.println(hisDemo.doJob(1002));
        
        // 1.6 Display team, allow Eli to return from leave, and do Job1002 again
        System.out.println("\nCurrent Team:");
        System.out.println(hisDemo.getTeam());
        
        // Allow Eli to return from leave
        System.out.println("\nReturning Eli from leave:");
        System.out.println(hisDemo.staffRejoinTeam("Eli"));
        
        // Do Job1002 again
        System.out.println("\nPerforming job 1002 again:");
        System.out.println(hisDemo.doJob(1002));
        
        //3.3
        System.out.println(hisDemo.hireStaff("Witek"));
        
        //4.3
        System.out.println(hisDemo.getExpensiveStaff());


    }
}
