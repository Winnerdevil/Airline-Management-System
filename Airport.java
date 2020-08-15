import java.io.*;
import java.util.*;

// Airport class take the detail of the User and proceed it 
// to the Airport Manager for the Checking.
public class Airport {
	
	public static void airportManagerStep(Passenger P, Luggage L) {
		// proceeding the passenger to Airport manager to verify its detail and to check for the security.
		Boolean passed = AirportManager.checkAll(P, L);
		if (passed) {
			System.out.println("Successfully passed all Checking");
		} else {
			System.out.println("Sorry to say that you have failed in checking," 
				+ " Please Check the detail and Come again later!!!");
		}
		System.out.println("\n\n--------------------------------");
		System.out.println("Thanks For visiting.");
	}

	public static void main(String args[]) {
		Scanner Scan = new Scanner(System.in);
		
		// ------- Taking Information from User -------- 

		// if the user has not passed, and if it came again.
		System.out.println("Old user?(1 YES or any other key for NO):");
		Boolean isOld = Scan.next().equals("1");

		if (isOld) {
			ArrayList<Passenger> infoLists = ObjectFileHandler.readFromFile("passengerinfo.txt");
			
			// if the list is empty then there is no user left to verify.
			if (infoLists == null) {
				System.out.println("There is no person left in" 
					+ " verifying. Please enter as new User!!!");

				return;
			}

			int passId, idx = -1;

			while(true) {
				try {
					System.out.print("Enter Passport ID:\n");
					passId = Scan.nextInt();

					if (passId < 0) throw new Exception();

					idx = AirportManager.searchPassenger(passId);
					if (idx == -1) {
						throw new Exception();
					}

					break;
				} catch (Exception e){
					System.out.println("Either you have enter negative" 
							  + " numbers or invalid type data!"
							  + " or passportID is already present!!");
					Scan.next();
				}
			}

			Passenger P = infoLists.get(idx);

			P.baggage.hasMetalObject = false;
			Luggage L = new Luggage(P.baggage.weight, false);
			
			airportManagerStep(P, L);

			return;
		}

		Scan.nextLine();

		System.out.println("Enter name: ");
		String name = Scan.nextLine();

		// for age
		int age;
		while(true) {
			try {
				System.out.println("Enter age: ");
				age = Scan.nextInt();
				if (age <= 0) throw new Exception();
				break;
			} catch (Exception e){
				System.out.println("Either you have enter negative numbers"
				          + " or invalid type data!");
				Scan.next();
			}
		}
		
		// for weight
		float weight;
		while(true) {
			try {
				System.out.println("Enter weight: ");
				weight = Scan.nextFloat();

				if (weight <= 0) throw new Exception();
				break;
			} catch (Exception e){
				System.out.println("Either you have enter negative numbers" 
					      + " or invalid type data!");
				Scan.next();
			}
		}
		
		// for passport ID
		int passportID;
		while(true) {
			try {
				System.out.print("Enter Passport ID:\n");
				passportID = Scan.nextInt();
				if (passportID < 0) throw new Exception();
				if (AirportManager.searchPassenger(passportID) != -1) {
					throw new Exception();
				}
				break;
			} catch (Exception e){
				System.out.println("Either you have enter negative" 
						  + " numbers or invalid type data!"
						  + " or passportID is already present!!");
				Scan.next();
			}
		}
		
		
		System.out.print("Have Metal Objects? " + 
						 "(Enter 1 for YES, any other key for NO) :");
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
		Passenger P = new Passenger(passportID, weight, 
									hasMetalObject, name, age);

		// by default keeping that luggage doesn't contain the metal object.
		Luggage L = new Luggage(weight, false); 

		ArrayList<Passenger> infoLists = new ArrayList<Passenger>();
		infoLists.add(P);

		ObjectFileHandler.appendToFile("passengerinfo.txt", infoLists);

		airportManagerStep(P, L);
	}
}
