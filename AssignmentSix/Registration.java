// Assignment 6: Exception Handling - Custom Exception
// Task: Implement a custom exception named InvalidAgeException that gets thrown 
// when a user tries to register with an age less than 18. Demonstrate the usage 
// of try, catch, finally, and throw statements.

// Steps:
// 1. Create a custom exception class named InvalidAgeException.
// 2. In the registration process, check if the age is less than 18.
// 3. If age is less than 18, throw the InvalidAgeException.
// 4. Demonstrate the use of try-catch-finally and handle the exception appropriately
package AssignmentSix;
import java.util.Scanner;
class InvalidAgeException extends Exception{
    private String message;
    public InvalidAgeException(String message){
        this.message = message;
    }

}
public class Registration {
    public static void registerUser(String name, int age) throws InvalidAgeException{
       if (age<18){
            System.out.println("Age must be atleat greater than 18");
       } else{
       System.out.println("Registration successfull for "+name+" Age: "+age);
       }
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter your name : ");
        String name = sc.nextLine();

        System.out.println("Enter your age: ");
        int age = sc.nextInt();

        try{
            registerUser(name, age);
        } catch (InvalidAgeException e){
            System.out.println("Registration failed. Please try again !!");
        } finally{
            System.out.println("Thankyou.");
            sc.close();
        }
    }
}
