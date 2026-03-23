/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Mark Anthony Villarante | S1101 | Group 59
 */

//Libraries
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MotorPHPayroll {
    
    public static void main(String[] args){
        
        //ARRAYS
        
    String line;
    int count = 0;
    
    int[] employeeID = new int[100];
    String[] employeeName = new String[100];
    String[] birthDay = new String[100];
    double[] hourlyRate = new double[100];
    double[]totalHours = new double[100];
    
//************************************************************************
//EMPLOYEE LOADING
//************************************************************************
    
    try{
    
    BufferedReader br = new BufferedReader(new FileReader("employees.csv"));  
    
    br.readLine();
    

    
    while((line = br.readLine()) != null){
        
        String[] data = line.split(",");
        
        employeeID[count] = Integer.parseInt(data[0]);
        employeeName[count] = data[1] + " " + data[2];
        birthDay[count] = data[3];
        hourlyRate[count] = Double.parseDouble(data[18]);
        
        count++;    
    }
    
    br.close();  
    
    
    }catch(Exception e){
        System.out.println("Error: " + e.getMessage());
    }
    
//**********************************************************************
//ATTENDANCE LOGIC
//**********************************************************************
    
    try {
    BufferedReader br2 = new BufferedReader(new FileReader("attendance.csv"));
    br2.readLine();

    while ((line = br2.readLine()) != null) {
        String[] data = line.split(",");

        int empID = Integer.parseInt(data[0]);
        String date = data[3];      // 6/3/2024
        String logIn = data[4];     // 8:59
        String logOut = data[5];    // 18:31

        // =========================
        // GET MONTH
        // =========================
        String[] dateParts = date.split("/");
        int month = Integer.parseInt(dateParts[0]);

        if (month < 6 || month > 12) continue;

        // =========================
        // CONVERT TIME
        // =========================
        String[] inParts = logIn.split(":");
        double timeIn = Integer.parseInt(inParts[0]) + Integer.parseInt(inParts[1]) / 60.0;

        String[] outParts = logOut.split(":");
        double timeOut = Integer.parseInt(outParts[0]) + Integer.parseInt(outParts[1]) / 60.0;

        // =========================
        // APPLY 8AM–5PM RULE
        // =========================
        double start = Math.max(timeIn, 8.0);
        double end = Math.min(timeOut, 17.0);

        double hoursWorked = 0;
        if (end > start) {
            hoursWorked = end - start;
        }

        // =========================
        // MATCH EMPLOYEE
        // =========================
        for (int i = 0; i < count; i++) {
            if (employeeID[i] == empID) {
                totalHours[i] += hoursWorked;
                break;
            }
        }
    }

    br2.close();

} catch (Exception e) {
    System.out.println("Attendance Error: " + e.getMessage());
}
    
    //Login 
   
        Scanner input = new Scanner(System.in);
        
        System.out.print("Username: ");
        String username = input.nextLine();
        System.out.print("Password: ");
        String password = input.nextLine();
        
         // ********************** EMPLOYEE **********************************
        
        if(username.equals("employee") && password.equals("12345")){
            System.out.print("Welcome Employee");
            
            int choice;
            
            
                   //Employee's menu
            do{
                System.out.println("---Employee Menu0--");
                System.out.println("1. Enter your Employee ID");
                System.out.println("2. Exit");
                
                System.out.print("Enter your choice: ");
                choice = input.nextInt();
                
                          //If employee will press 1
                if(choice == 1){
    System.out.print("Enter employee id: ");
    int searchID = input.nextInt();

    boolean found = false;

    for(int i = 0; i < count; i++){
        if(employeeID[i] == searchID){

            found = true;

            double grossPay = totalHours[i] * hourlyRate[i];

            double sss = 0;
            if (grossPay <= 3250) sss = 135;
            else if (grossPay <= 3750) sss = 157.5;
            else if (grossPay <= 4250) sss = 180;

            double philhealth = grossPay * 0.02;
            double pagibig = 100;

            double tax = 0;
            if (grossPay > 20833) {
                tax = (grossPay - 20833) * 0.20;
            }

            double deductions = sss + philhealth + pagibig + tax;
            double netPay = grossPay - deductions;

            System.out.println("\n===== EMPLOYEE PAYROLL =====");
            System.out.println("Employee ID: " + employeeID[i]);
            System.out.println("Name: " + employeeName[i]);
            System.out.println("Total Hours: " + totalHours[i]);
            System.out.println("Gross Pay: " + grossPay);
            System.out.println("SSS: " + sss);
            System.out.println("PhilHealth: " + philhealth);
            System.out.println("Pag-IBIG: " + pagibig);
            System.out.println("Tax: " + tax);
            System.out.println("Net Pay: " + netPay);

            break;
        }
    }

    if(!found){
        System.out.println("Employee number does not exist!");
    }
                    
                    //If employee chose 2 (exit)
                }else if(choice == 2){
                    System.out.println("Program Terminated.");
                   
                }else{
                    System.out.println("Invalid choice, please choose either 1 or 2");
                    
                }
            }while(choice !=2);
            
            
       
        
         // ********************** Payroll **********************************
         }else if(username.equals("payroll_staff") && password.equals("12345")){
             System.out.println("Welcome Payroll Staff");
             
             int choice;
             //Payroll's menu 
             
             do{
                System.out.println("---Payroll Menu--");
                System.out.println("1. Process Payroll");
                System.out.println("2. Exit");
                
                System.out.print("Enter your choice: ");
                choice = input.nextInt();
                
                          //If Payroll will press 1
                if(choice == 1){
                    int subChoice;
                    
                    do{
                        System.out.println("----Process Payroll----");
                        System.out.println("1. One employee");
                        System.out.println("2. All employees");
                        System.out.println("3. Exit");
                        System.out.print("Enter your choice: ");
                        subChoice = input.nextInt();
                        
                        // Under option 1, one employee
                        if(subChoice == 1){
                            System.out.print("Enter employee ID: ");
                            int searchID = input.nextInt();
                            
                            boolean found = false;
                            
                            for(int i = 0; i < count; i++){
                                if(employeeID[i] == searchID){
                                    found = true;
                                    
                                    double grossPay = totalHours[i] * hourlyRate[i];
                                    double sss = 0;
                                    if(grossPay <= 3250) sss = 135;
                                    else if(grossPay <= 3750) sss = 157.5;
                                    else if(grossPay <= 4250) sss = 180;
                                    
                                    double philhealth = grossPay * 0.02;
                                    double pagibig = 100;

                                    double tax = 0;
                                    if (grossPay > 20833) {
                                    tax = (grossPay - 20833) * 0.20;
                                    
                                }
                                     double netPay = grossPay - (sss + philhealth + pagibig + tax);

                                     System.out.println("\n===== PAYROLL =====");
                                     System.out.println("Employee ID: " + employeeID[i]);
                                     System.out.println("Name: " + employeeName[i]);
                                     System.out.println("Total Hours: " + totalHours[i]);
                                     System.out.println("Gross Pay: " + grossPay);
                                     System.out.println("SSS: " + sss);
                                     System.out.println("PhilHealth: " + philhealth);
                                     System.out.println("Pag-IBIG: " + pagibig);
                                     System.out.println("Tax: " + tax);
                                     System.out.println("Net Pay: " + netPay);

                                     break;
                                    }
                            }if(!found){
                                System.out.println("Employee number does not exist!");
                               
                            }

                            
//****************************************************
//Should be printing all employee payroll
//****************************************************
                        }else if(subChoice == 2){
                            for(int i = 0; i < count; i++){
                                double grossPay = totalHours[i] * hourlyRate[i];
                                double sss = 0;
                                if (grossPay <= 3250) sss = 135;
                                else if (grossPay <= 3750) sss = 157.5;
                                else if (grossPay <= 4250) sss = 180;
                                
                                double philhealth = grossPay * 0.02;
                                double pagibig = 100;
                                double tax = 0;
                                if (grossPay > 20833) {
                                tax = (grossPay - 20833) * 0.20;
        
    }
    


    double netPay = grossPay - (sss + philhealth + pagibig + tax);

    System.out.println("\n=======================");
    System.out.println("Employee ID: " + employeeID[i]);
    System.out.println("Name: " + employeeName[i]);
    System.out.println("Total Hours: " + totalHours[i]);
    System.out.println("Gross Pay: " + grossPay);
    System.out.println("Net Pay: " + netPay);
}
                                

    

    
                            //Exiting, going back to previous page
                        }else if(subChoice == 3){
                            System.out.println("Returning to Payroll Menu...");
                        }else{
                            System.out.println("Invalid Choice");
                        }
                        
                    }while(subChoice != 3);
                    
//***************************************************************************************************************
                  
                        //If Payroll chose 2 (exit)
                }else if(choice == 2){
                    System.out.println("Program Terminated.");
                   
                }else{
                    System.out.println("Invalid choice, please choose either 1 or 2");
                    
                }
            }while(choice !=2);
            
            
       
        
      
         }else{
             System.out.println("Invalid username and/or password");
             
         }
         }
   
                
  
            
}


           
        

    


    

