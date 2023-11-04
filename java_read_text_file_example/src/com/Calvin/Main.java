package com.Calvin;
import java.io.*;
import java.util.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.util.Date;

public class Main {
    //Java Console Colors
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    //Classes
    public static class Employee {
        //Data Members
        private String Name;
        private String Surname;
        private String BirthDate;
        private int EmployeeNumber;
        private double Salary;
        private String Role_designation;
        private String ReportsTo;

        //Default Constructors

        public Employee() {
            setEmployee("User", "Surname", "1970/01/01", 0, 0.00, "Employee", "Owner");
        }

        public Employee(String a, String b, String c, int d, double e, String f, String g) {
            setEmployee(a, b, c, d, e, f, g);
        }

        //Methods


        //Set Attributes
        public void setEmployee(String a, String b, String c, int d, double e, String f, String g) {
            setName(a);
            setSurname(b);
            setBirthDate(c);
            setEmployeeNumber(d);
            setSalary(e);
            setRoleDesignation(f);
            setReportsTo(g);
        }

        public void setName(String a) {
            Name = a;
        }

        public void setSurname(String b) {
            Surname = b;
        }

        public void setBirthDate(String c) {
            BirthDate = c;
        }

        public void setEmployeeNumber(int d) {
            EmployeeNumber = d;
        }

        public void setSalary(double e) {
            Salary = e;
        }

        public void setRoleDesignation(String f) {
            Role_designation = f;
        }

        public void setReportsTo(String g) {
            ReportsTo = g;
        }

        //Get Attributes
        public String getName() {
            return Name;
        }

        public String getSurname() {
            return Surname;
        }

        public String getBirthDate() {
            return BirthDate;
        }

        public int getEmployeeNumber() {
            return EmployeeNumber;
        }

        public double getSalary() {
            return Salary;
        }

        public String getRoleDesignation() {
            return Role_designation;
        }

        public String getReports() {
            return ReportsTo;
        }

        //OverRide
        @Override
        public String toString() {
            return String.format("%s,%s,%s,%s,%s,%s,%s", getName(), getSurname(), getBirthDate(), getEmployeeNumber(), getSalary(), getRoleDesignation(), getReports());
        }

    }
    public static class MyTimer implements ActionListener {
        private JLabel timerLabel = new JLabel("    ");

        Timer t;
        private int countdownPeriod = 0;
        private final int ONE_SECOND = 1000;

        private void write() {
            System.out.printf("%s... ", this.countdownPeriod);
            timerLabel.setText("" + countdownPeriod);
        }

        public MyTimer(int seconds) {
            this.countdownPeriod = seconds;
            timerLabel.setText("" + countdownPeriod);
            t = new Timer(this.ONE_SECOND, this);
            t.setInitialDelay(0);
            t.start();
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (this.countdownPeriod == 0) {
                t.stop();

            } else {
                this.countdownPeriod--;
                write();
            }
        }
    }

   //Main Class
    public static void main(String[] args) throws IOException, ParseException {
        FileReader fr = new FileReader("./src/com/Calvin/EmployeeFile");
        List<Employee> employees = new ArrayList<Employee>();

        try (BufferedReader br = new BufferedReader(fr)) {
            String fileLine = "";
            while ((fileLine = br.readLine()) != null) {
                String[] fields = fileLine.split(" ");
                Employee employee = new Employee(fields[0], fields[1], fields[2], Integer.parseInt(fields[3]), Double.parseDouble(fields[4]), fields[5], fields[6]);
                employees.add(employee);
            }
        } catch (Exception e){
            System.out.println("------ Whoops ------");
            System.out.println(e);
            System.out.println("--------------------");
        } finally {
            System.out.println(" ");
            System.out.println(ANSI_BLUE + "-------------------------------------------");
            System.out.println(ANSI_BLUE + "Welcome To CalTech Employee Admin Program");
            System.out.println(ANSI_BLUE + "-------------------------------------------");
            System.out.println(" ");
            System.out.println(ANSI_GREEN + "Please Specify what Action you would like to perform");
            System.out.println(" ");
            System.out.println(ANSI_GREEN + "--------------------Options-----------------------");
            System.out.println(ANSI_WHITE + "A - Search for Employee");
            System.out.println("B - Search for Employees older than Specified Date");
            System.out.println("C - View Organisational Structure");
            System.out.println("D - View highest earning member on each tier level");
            System.out.println(ANSI_GREEN + "--------------------------------------------------");


            /////////////////////Get Input/////////////////////////////////
            // Reading data using readLine
            Scanner in = new Scanner(System.in);
            String Option = in.nextLine();
            ////////////////////////////////////////////////////////////////


            /////////////////////Check Input/////////////////////////////////
            if (Option.equals("A") || Option.equals("a") || Option.equals("1")) {
                System.out.println(ANSI_BLUE + "Please Enter an Employee Name");
                System.out.println(" ");

                  // Reading data using readLine
                  String name = in.nextLine();
                  //capitalise first letter of input
                  String s1 = name.substring(0, 1).toUpperCase() + name.substring(1);

                 try { Object result = employees.stream()
                         .filter(item -> item.Name.equals(s1))
                         .map(Employee -> "\n" + "Name:" + " " + ANSI_BLUE + Employee.getName() + "\n" + ANSI_WHITE
                                 + "Surname: " + " " + ANSI_BLUE + Employee.getSurname() + "\n" + ANSI_WHITE
                                 + "BirthDate:" + " " + ANSI_BLUE + Employee.getBirthDate() + "\n" + ANSI_WHITE
                                 + "Employee Number:" + " " + ANSI_BLUE + Employee.getEmployeeNumber() + "\n" + ANSI_WHITE
                                 + "Salary:" + " " + ANSI_BLUE + Employee.getSalary() + "\n" + ANSI_WHITE
                                 + "Role Designation:" + " " + ANSI_BLUE + Employee.getRoleDesignation() + "\n" + ANSI_WHITE
                                 + "Reports To:" + " " + ANSI_BLUE + Employee.getReports() + "\n" + ANSI_WHITE)
                         .collect(Collectors.toList());

                     if (!result.equals(null)) {
                         System.out.println(ANSI_BLUE + "------ Employees Details ------");
                         System.out.println(ANSI_WHITE + result);
                         System.out.println(ANSI_BLUE + "-------------------------------");
                     }
                     else {
                         System.out.println(ANSI_RED + "------------------- Whoops !! -----------------------");
                         System.out.println(ANSI_RED + "| *** The Employee Does'nt Exist In the System  *** |");
                         System.out.println(ANSI_RED + "-----------------------------------------------------");
                     }

                 }
                 catch (Exception e){
                     System.out.println(ANSI_RED + "------------- Error !! ------------------");
                     System.out.println(ANSI_RED + "|" + e + "|");
                     System.out.println(ANSI_RED + "-----------------------------------------");
                 }

            }
            if (Option.equals("B") || Option.equals("b") || Option.equals("2")) {
                System.out.println(ANSI_BLUE + "Please Enter a Date in Format" + ANSI_WHITE + " [ yyyy/MM/dd ]" + "- - - " + ANSI_WHITE + "[ E.G 2019/01/01 ] ");
                System.out.println(" ");

                // Reading data using readLine
                String Dat = in.nextLine();

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                boolean checkParse;

              try {
                  Date strDate = formatter.parse(Dat);
                  Date Today = new Date();
                  if (strDate.compareTo(Today) >= 0 || strDate.compareTo(Today) <= 0) {
                      checkParse = false;
                  } else {
                      checkParse = true;
                  }
                  if (checkParse = true) {

                      Dat = formatter.format(strDate);
                      String finalDat = Dat;

                      Object result = employees.stream()
                              .filter(item -> item.BirthDate.equals(finalDat) || finalDat.compareTo(item.BirthDate) > 0)
                              .map(Employee -> "\n" + " " + ANSI_GREEN + "Name:" + " " + ANSI_WHITE + Employee.getName() + " " + ANSI_GREEN + "BirthDate:" + " " + ANSI_WHITE + Employee.getBirthDate() + " " + "\n")
                              .collect(Collectors.toList());
                      System.out.println();
                      System.out.println(ANSI_GREEN + "Employees Older Than this Date ");
                      System.out.println();
                      System.out.println(ANSI_WHITE + result);
                  }
                  else {
                      System.out.println(ANSI_RED + "----------- Error !! -----------");
                      System.out.println(ANSI_RED + "|" + "Something Went Wrong" + "|");
                      System.out.println(ANSI_RED + "--------------------------------");
                  }

              }catch (Exception e){
                  System.out.println(ANSI_RED + "------------- Error !! ------------------");
                  System.out.println(ANSI_RED + "|" + e + "|");
                  System.out.println(ANSI_RED + "-----------------------------------------");
              }
            }
            if (Option.equals("C") || Option.equals("c") || Option.equals("3")) {
                System.out.println(ANSI_BLUE + "The company org. structure is:");
                System.out.println(" ");

                Object Owner = employees.stream()
                        .filter(item -> item.getRoleDesignation().equals("Owner"))
                        .map(Employee::getName)
                        .collect(Collectors.toList());

                Object Managers = employees.stream()
                        .filter(item -> item.getRoleDesignation().equals("Manager"))
                        .map(Employee -> "\n" +  ANSI_GREEN + Employee.getName() + " " + "(" + Employee.getRoleDesignation() + ")" + ANSI_WHITE + " " + "Reports To ---> " + ANSI_BLUE + Employee.getReports() +  ANSI_GREEN + "\n")
                        .collect(Collectors.toList());

                Object Employees = employees.stream()
                        .filter(item -> item.getRoleDesignation().equals("Employee"))
                        .map(Employee -> "\n" +  ANSI_CYAN + Employee.getName() + " " + "(" + Employee.getRoleDesignation() + ")" + ANSI_WHITE + " " + "Reports To ---> " + ANSI_GREEN + Employee.getReports() +  ANSI_CYAN + "\n")
                        .collect(Collectors.toList());

                Object Trainees = employees.stream()
                        .filter(item -> item.getRoleDesignation().equals("Trainee"))
                        .map(Employee -> "\n" +  ANSI_PURPLE + Employee.getName() + " " + "(" + Employee.getRoleDesignation() + ")" + ANSI_WHITE + " " + "Reports To ---> " + ANSI_CYAN + Employee.getReports() +  ANSI_PURPLE + "\n")
                        .collect(Collectors.toList());

                System.out.println(ANSI_WHITE + "--------------------");
                System.out.println(ANSI_BLUE + "Owners");
                System.out.println(ANSI_WHITE + "--------------------");
                System.out.println(ANSI_BLUE + Owner);


                System.out.println(" ");

                System.out.println(ANSI_WHITE + "--------------------");
                System.out.println(ANSI_GREEN + "Managers");
                System.out.println(ANSI_WHITE + "--------------------");
                System.out.println(ANSI_GREEN + Managers);


                System.out.println(" ");

                System.out.println(ANSI_WHITE + "--------------------");
                System.out.println(ANSI_CYAN + "Employees");
                System.out.println(ANSI_WHITE + "--------------------");
                System.out.println(ANSI_CYAN + Employees);


                System.out.println(" ");

                System.out.println(ANSI_WHITE + "--------------------");
                System.out.println(ANSI_PURPLE + "Trainees");
                System.out.println(ANSI_WHITE + "--------------------");
                System.out.println(ANSI_PURPLE + Trainees);


                System.out.println(" ");


            }
            if (Option.equals("D") || Option.equals("d") || Option.equals("4")) {
                System.out.println(ANSI_WHITE + "The highest earning member on each tier level is:");
                System.out.println(" ");

                Object Owners = employees.stream()
                        .filter(item -> item.getRoleDesignation().equals("Owner"))
                        .max(Comparator.comparing(Employee::getSalary))
                        .map(Employee -> " " + "Owners:" + " "+ ANSI_WHITE + Employee.getName() + " " + ANSI_BLUE + "- R" + " "+ ANSI_WHITE +  Employee.getSalary() + " ");
                System.out.println(ANSI_BLUE + Owners);

                Object Managers = employees.stream()
                        .filter(item -> item.getRoleDesignation().equals("Manager"))
                        .max(Comparator.comparing(Employee::getSalary))
                        .map(Employee -> " " + "Managers:" + " " + ANSI_WHITE + Employee.getName() + " " + ANSI_GREEN + "- R" + " " + ANSI_WHITE  + Employee.getSalary() + " ");
                System.out.println(ANSI_GREEN + Managers);

                Object Employees = employees.stream()
                        .filter(item -> item.getRoleDesignation().equals("Employee"))
                        .max(Comparator.comparing(Employee::getSalary))
                        .map(Employee -> " " + "Employees:" + " " + ANSI_WHITE + Employee.getName() + " " + ANSI_CYAN + "- R" + " " + ANSI_WHITE + Employee.getSalary() + " ");
                System.out.println(ANSI_CYAN + Employees);

                Object Trainees = employees.stream()
                        .filter(item -> item.getRoleDesignation().equals("Trainee"))
                        .max(Comparator.comparing(Employee::getSalary))
                        .map(Employee -> " " + "Trainees:" + " " + ANSI_WHITE + Employee.getName() + " " + ANSI_PURPLE + "- R" + " " + ANSI_WHITE + Employee.getSalary() + " ");
                System.out.println(ANSI_PURPLE + Trainees);

            }
            if (Option.isBlank() || Option.length() != 1) {
                System.out.println(ANSI_RED + "------------- Error !! ------------------");
                System.out.println(ANSI_RED + "|     ***  Option not Permitted  ***       |");
                System.out.println(ANSI_RED + "| ** Input Has to be one of the Options ** |");
                System.out.println(ANSI_RED + "|     *** Program will Terminate in ***    |");
                System.out.println(ANSI_RED + "-----------------------------------------");

                MyTimer tom = new MyTimer(6);

            }
        }
    }
}





