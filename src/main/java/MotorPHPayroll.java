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
    
    //File Reading
    
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
                    System.out.print("Enter your Employee ID: ");
                    int searchID = input.nextInt();
                    
                    boolean found = false;
                    
                    for(int i = 0; i < count; i++){
                        if(employeeID[i] == searchID){
                            System.out.println("Employee Found!");
                            System.out.println("Employee ID: " + employeeID[i]);                    
                            System.out.println("Employee Name: "  + employeeName[i]);
                            System.out.println("Birthday: " + birthDay[i]);
                            System.out.println("Hourly Rate: " + hourlyRate[i]);
                            
                            found = true;
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
                        if(subChoice ==1){
                            System.out.print("Enter employee id: ");
                            int searchID = input.nextInt();
                            
                            boolean found = false;
                            
                            //finding the employee ID from array
                            
                            for(int i = 0; i < count; i++){
                                if(employeeID[i] == searchID){
                                    System.out.println("Employee Found!");
                                    System.out.println("Employee ID: " + employeeID[i]);
                                    System.out.println("Employee Name: " + employeeName[i]);
                                           
                                    found = true;
                                    break;
                                }
                            }if(!found){
                            System.out.println("Employee number does not exist!");
                        }
                        
                            
                            //Should be printing all employee payroll
                        }else if(subChoice == 2){
                            for(int i = 0; i < count; i++){
                                System.out.println("Employee ID: " + employeeID[i] + " | Employee Name: " + employeeName[i]);
                                
                                
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
            
            
       
        
      
         }
         }
  
                
  
            }

           
        

    


    

