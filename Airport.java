import java.io.*;
import java.util.*;

// Airport class take the detail of the User and proceed it to the Airport Manager for the Checking.
public class Airport {
	
	public static void main(String args[]) {
		Scanner Scan = new Scanner(System.in);
		
		// ------- Taking Information from User -------- 
		System.out.print("Enter name: ");
		String name = Scan.nextLine();
		
		System.out.print("Enter age: ");
		int age = Scan.nextInt();

		System.out.print("Enter weight: ");
		float weight = Scan.nextFloat();
		
		System.out.print("Enter Passport ID:\n");
		int passportID = Scan.nextInt();
		
		System.out.print("Have Metal Objects? (Enter 1 for YES, any other key for NO) :");
		String metalOb = Scan.next();
		System.out.println();
		// ------- Taking Information end --------
 
		boolean hasMetalObject;
		
		if(metalOb.equals("1")) {
			hasMetalObject = true;
		}
		else {
			hasMetalObject = false;
		}
		// System.out.println("name: " + name + " age: " + age + " weight: " + weight + " passportID: " + passportID + " metalOb: " + hasMetalObject);

		Passenger P = new Passenger(passportID, weight, hasMetalObject, name, age);
		Luggage L = new Luggage(weight, false); // by default keeping that luggage doesn't contain the metal object.

		ArrayList<Passenger> infoLists = new ArrayList<Passenger>();
		infoLists.add(P);

		ObjectFileHandler.writeToFile("passengerinfo.txt", infoLists);

		// proceeding the passenger to Airport manager to verify its detail and to check for the security.
		Boolean passed = AirportManager.checkAll(P, L);
		if (passed) {
			System.out.println("\n\n--------------------------------");
			System.out.println("Thanks For visiting.");
		} else {
			System.out.println("Sorry to say that you have failed in checking, Please Check the detail and Come again later!!!");
		}
	}
}