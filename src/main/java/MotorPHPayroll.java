/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Mark Anthony Villarante | S1101 | Group 59
 */

//imports for file reading and scanner for username and password
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class MotorPHPayroll {

    //array to store employee information
    static String[] empNumber = new String[40];
    static String[] lastName = new String[40];
    static String[] firstName = new String[40];

    static double[] basicSalary = new double[40];
    static double[] hourlyRate = new double[40];

    static int employeeCount = 0;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        
        //load employee data from CSV file
        loadEmployees("target/classes/employees.csv");
        
        System.out.println("MOTORPH PAYROLL SYSTEM");
        
        // Login - Employees should input their employee # as their both username and password
        System.out.print("Username (Employee #): ");
        String username = input.nextLine();

        System.out.print("Password: ");
        String password = input.nextLine();

        //Verify login credentials
        int index = verifyLogin(username, password);

        if (index == -1) {
            System.out.println("\nLogin Failed.");
        } else {

            System.out.println("\nLogin Successful\n");
            
            //Display employee information
            showEmployeeInfo(index);
            
            //load attendance records and compute total hours
            double totalHours = loadAttendance("target/classes/attendance.csv", username);
            
             //compute gross salary
            double salary = totalHours * hourlyRate[index];
            
            //display payroll summary
            System.out.println("\nPAYROLL SUMMARY");
            System.out.println("---------------------");
            System.out.println("Total Hours Worked: " + totalHours);
            System.out.println("Hourly Rate: " + hourlyRate[index]);
            System.out.println("Gross Salary: " + salary);
        }
    }
       //read employee info from employees.csv file then store to the right arrays.
    public static void loadEmployees(String file) {

        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));
            
             // skip header row
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                
                 //split csv columns
                String[] data = line.split(",");
                
                  //store employee data
                empNumber[employeeCount] = data[0];
                lastName[employeeCount] = data[1];
                firstName[employeeCount] = data[2];

                basicSalary[employeeCount] = Double.parseDouble(data[13]);
                hourlyRate[employeeCount] = Double.parseDouble(data[18]);

                employeeCount++;
            }

            reader.close();

        } catch (Exception e) {
    System.out.println("Error loading employee file.");
    e.printStackTrace();
}
    }
        //verification
        // check if the entered username and password are correct
        //Since there's no password given on the CSV file, I decided to use
        // their employee # as the username and password.
        // If the employee # is in the system, it should match the username and password.
        // Otherwise, it should result in failed login.
    public static int verifyLogin(String user, String pass) {

        for (int i = 0; i < employeeCount; i++) {

            if (empNumber[i].equals(user) && empNumber[i].equals(pass)) {
                return i;
            }
        }

        return -1;
    }
    
    //Once logged in, this will show the employee info of the logged in Employee #
    public static void showEmployeeInfo(int i) {

        System.out.println("EMPLOYEE INFORMATION");
        System.out.println("---------------------");

        System.out.println("Employee #: " + empNumber[i]);
        System.out.println("Name: " + firstName[i] + " " + lastName[i]);
        System.out.println("Basic Salary: " + basicSalary[i]);
        System.out.println("Hourly Rate: " + hourlyRate[i]);
    }
       // This reads attendance records from attendance.csv
    public static double loadAttendance(String file, String employeeID) {

        double totalHours = 0;

        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));
            
              // skip header row
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                String emp = data[0];
               
                 //process only the logged in employee
                if (emp.equals(employeeID)) {

                    String login = data[4];
                    String logout = data[5];

                    double hours = computeHours(login, logout);

                    totalHours = totalHours + hours;
                }
            }

            reader.close();

        } catch (Exception e) {
    System.out.println("Error reading attendance file.");
    e.printStackTrace();
}

        return totalHours;
    }

         // compute workings hours between login and logout
         // No overtime
    public static double computeHours(String login, String logout) {

        String[] in = login.split(":");
        String[] out = logout.split(":");

        double inHour = Integer.parseInt(in[0]);
        double inMin = Integer.parseInt(in[1]);

        double outHour = Integer.parseInt(out[0]);
        double outMin = Integer.parseInt(out[1]);
        
        // converting to decimal
        double timeIn = inHour + (inMin / 60);
        double timeOut = outHour + (outMin / 60);

        if (timeIn < 8) {
            timeIn = 8;
        }

        if (timeOut > 17) {
            timeOut = 17;
        }

        double hoursWorked = timeOut - timeIn;

        if (hoursWorked < 0) {
            hoursWorked = 0;
        }

        return hoursWorked;
    }
}