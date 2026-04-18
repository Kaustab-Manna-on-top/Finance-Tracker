import java.util.*;
import java.io.*;

public class SelfFinance extends Password {
    
    static String user, full, phone, pw, pw1,GP, month;
    static int sum=0,flag=0;
    static Thread t = new Thread();

    static String asset[] = new String[10];
    static int value[] = new int[10];

    static String Source[] = new String[10];
    static int Amt[] = new int[10];

    static FileWriter fw;
    static Scanner sc = new Scanner(System.in);
    

    // ----------- Drawing a single line ------------ //
    public static void DrawLine(char c) throws Exception {
        System.out.println();
        for (int i = 1; i <= 150; i++) {
            System.out.print(c);
            t.sleep(10);
        }
    }

    // ----------- Drawing a String ------------ //
    public static void DrawText(String s) throws Exception {
        int l = (150 - s.length()) / 2;
        int i;

        System.out.println();

        for (i = 1; i < l; i++) {
            System.out.print(" ");
        }

        for (i = 0; i < s.length(); i++) {
            System.out.print(s.charAt(i));
            t.sleep(10);
        }
    }




    public static void Registration() throws Exception {

        DrawLine('=');
        DrawText("REGISTRATION DETAILS");
        DrawLine('=');

        System.out.print("\n\tUser Name        : ");
        user = sc.next().trim();

        System.out.print("\tFull Name        : ");
        full = sc.next();

        System.out.print("\tPhone Number     : ");
        phone = sc.next();

        if(phone.length()!=10){
            System.out.print("\tInvalid Phone Number");
            System.out.print("\t\t\tPlease Enter Again");
            System.out.print("\tPhone Number     : ");
            phone = sc.next();
        }

        System.out.print("\tPassword         : ");
        pw = sc.next().trim();

        System.out.print("\tRetype Password  : ");
        pw1 = sc.next().trim();

        int size = 8;
        GP = Reshape(size);
        System.out.println("\tGenerated Password  : " + GP + "\n\t**Note : This is a system generated password and can be used for login if you forget your password**\n");
        
        while(!pw.equals(pw1)){
            System.out.print("\tPassword Mismatch");
            System.out.print("\t\t\bEnter Password Again\n");
            System.out.print("\tPassword         : ");
            pw = sc.next().trim();
            System.out.print("\tRetype Password  : ");
            pw1 = sc.next().trim();
        }
        flag=1;
        fw.write("\n,,Finance Report,,\n");
        fw.write("\n,Section,Field,Value\n");
        fw.write(",Registration,User Name," + user + "\n");
        fw.write(",Registration,Full Name," + full + "\n");
        fw.write(",Registration,Phone Number," + phone + "\n");
        fw.write(",Registration,Generated Password," + GP + "\n");
        fw.write("\n");

        System.out.print("\tRegistration Complete Redirecting to Login Page");
        Thread.sleep(2000);
        DrawLine('*');

        Login();
    }

    public static void Login() throws Exception {

        DrawLine('-');
        DrawText("LOGIN DETAILS");
        DrawLine('-');

        System.out.print("\n\tUser Name        : ");
        String u = sc.next().trim();

        System.out.print("\tPassword         : ");
        String p = sc.next().trim();

        if ((p.equals(pw) || p.equals(GP)) && u.equals(user)) {
            System.out.print("\tWelcome to Self Finance");
            opening();
        } else {
            if(flag==0){
                System.out.print("\t\t\tUser credentials not Found");
                System.out.print("\t\t\tPlease Register First");
                System.out.print("\t\t\tRedirecting to Registration Page");
                Thread.sleep(2000);
                DrawLine(':');
                Registration();
            }
            else{
                System.out.print("\tEither user name or password mismatch");
                System.out.print("\t\t\tPlease Try Again");
                Thread.sleep(100);
                Login();
            }
                
        }
    }

    public static void opening() throws Exception {

        DrawText("MAIN OPERATIONS");
        DrawLine('~');

        System.out.println("\nEnter Number of Properties < 11 ");
        int n = sc.nextInt();
      
        fw.write(",Asset,Name,Balance\n");
        

        for (int i = 0; i < n; i++) {

            System.out.println("Enter name of Properties ");
            asset[i] = sc.next();

            System.out.println("Enter Opening Balance ");
            value[i] = sc.nextInt();

            
            fw.write(",Asset," + asset[i] + "," + value[i] + "\n");
        }

        display(n);
    }

    public static int IncomeOutgoing() throws Exception {
            DrawText("Total Earnings and Spendings");
            DrawLine('~');
            int n,tot=0,c=0;

            fw.write("\n,,Total Earnings and Spendings\n");
            fw.write(",Type,Name,Amount\n");
            

            while(true){

                System.out.println("\nEnter 1 for Income and 2 for Outgoing and 3 for Exit");
                n = sc.nextInt();
                            
                if(n==1){
                    System.out.println("Enter name of Income ");
                    String inc = sc.next();
                    System.out.println("Enter amount ");
                    int amt = sc.nextInt();
                    Source[c] = inc;
                    Amt[c] = amt;
                    c++;
                    sum+=amt;
                    tot += amt;
                    fw.write(",Income," + inc + "," + amt + "\n");
                }
                else if(n==2){
                    System.out.println("Enter name of Outgoing ");
                    String out = sc.next();
                    System.out.println("Enter amount ");
                    int amt = sc.nextInt();
                    Source[c] = out;
                    Amt[c] = -amt;
                    c++;
                    sum-=amt;
                    fw.write(",Expense," + out + ",-" + amt + "\n");
                    tot -= amt;
                }
                else
                    break;
            }
            return tot;
    }

    public static void display(int m) throws Exception {

        int tot =IncomeOutgoing();

        DrawLine('~');
        DrawText("TRIAL BALANCE");
        DrawLine('~');
        
        System.out.println();

        System.out.println("\t\tSl No.\t\tASSET\t\tBALANCE");

     
        fw.write("\n,,Trial Balance,,\n");
        fw.write(",Sl No.,Asset,Balance\n");

        for (int i = 0; i < m; i++) {
            System.out.println("\t\t"+"["+(i+1)+"]"+"\t\t" + asset[i] + "\t\t" + value[i]);
            fw.write(","+(i + 1) + "," + asset[i] + "," + value[i] + "\n");
            tot += value[i];
        }

        System.out.println();
        fw.write("\n");

        System.out.printf("%-25s : %d\n", "Profit for a Single Month", sum);
        System.out.printf("%-25s : %d\n", "Net Balance", tot);
        System.out.printf("%-25s : %d\n", "Gross Balance", (tot - sum));

        fw.write("\n,,Section,Metric,Value\n");
        fw.write(",,Summary,Profit," + sum + "\n");
        fw.write(",,Summary,Net Balance," + tot + "\n");
        fw.write(",,Summary,Gross Balance," + (tot - sum) + "\n");
        DrawLine('`');
        DrawText("Sources of Income and Outgoing");
        DrawLine('`');
        System.out.println();

        System.out.println("\t\tSl No.\t\tSOURCE\t\tAMOUNT");
        for (int i = 0; i < Source.length && Source[i] != null; i++) {
            System.out.println("\t\t"+"["+(i+1)+"]"+"\t\t" + Source[i] + "\t\t" + Amt[i]);
        }

        System.out.println();
        fw.close();
    }

    public static void Menu() throws Exception {

        DrawText("REGISTRATION (R) \t\t LOGIN (L) \t\t EXIT \t\t YOUR CHOICE : ");

        char c = sc.next().toUpperCase().charAt(0);

        switch (c) {

            case 'R':
                Registration();
                break;

            case 'L':
                Login();
                break;

            default:
                System.out.println("Thank you and Good Bye");
                System.exit(2000);
        }
    }

    public static void main(String args[]) throws Exception {
        DrawLine('=');
        DrawText("SELF FINANCE");
        DrawLine('=');

        try {
            System.out.print("\n\t\t\tEnter the Month for which you want to track your expenses : ");
            month = sc.next();
            File f = new File( month + ".csv");
            if (f.createNewFile()) {
                System.out.println("\t\t\tFile created successfully");
            } else {
                System.out.println("\t\t\tFile already exists");
            }
            fw = new FileWriter(f, true);
        } catch (Exception e) {
            System.out.println("Error in creating file "+e.getMessage());
        }

        Menu();
    }
}