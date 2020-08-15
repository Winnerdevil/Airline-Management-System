import java.io.*;
import java.util.*;

// Airport Manager update Annoucement, and maintain all checking 
// for the passenger.
public class AirportManager{

	// It find the index of the given passenger in the ArrayList.
	public static int searchPassenger(int passportID) {

		ArrayList<Passenger> passengerList = ObjectFileHandler.readFromFile("passengerinfo.txt");
		int i;
		if(passengerList == null)
			return -1;
		for(i = 0; i < passengerList.size(); ++i) {
			if(passengerList.get(i).passportID == passportID)
				return i;
		}
		return -1;
	}
	
	// It just Annouce to the passenger to proceed for the Verification process.
	public static void updateAnnoucement() {

		ArrayList<Passenger> passengerList = ObjectFileHandler.readFromFile("passengerinfo.txt");
		
		System.out.println("\n---------------------------ANNOUNCEMENTS-----------------------------");
		if (passengerList != null) {
			for(int i = 0; i < passengerList.size(); ++i) {
				System.out.println("\n" + passengerList.get(i).name +" please verify your "+ passengerList.get(i).level + " check");
			}
		}
		System.out.println("---------------------------------------------------------------------");
		
	}
	
	// Check all the Luggage, baggage, flightCheckIn etc...
	public static boolean checkAll(Passenger P, Luggage L) {
		
		// Call the passenger to verify.
		updateAnnoucement();
		
		int flag = 0;

		// X-ray the lugugage
		if(XRAY(L) == true) {
			P.level++;
			System.out.println("Passed X-Ray Check");
		} else {
			System.out.println("Failed X-Ray Check");
			flag = 1;
		}
		
		// Check the lugugage 
		if(flag == 0 && bCheckIn(L) == true) {
			P.level++;
			System.out.println("Passed Baggage Check-In");
		} else if(flag == 0) {
			System.out.println("Failed Baggage Check");
			flag = 1;
		}
		
		// Security Check (checking the baggage for containing metal object)
		if(flag == 0 && securityCheck(P) == true) {
			P.level++;
			System.out.println("Passed Security Check");
		} else if(flag == 0) {
			System.out.println("Failed securityCheck Check");
			flag = 1;
		}
		
		// Flight Check (checking for the Boarding pass and stamp)
		if(flag == 0 && flightCheck(P) == true) {
			P.level++;
			System.out.println("Passed Flight Check-In");

			ArrayList<Passenger> passengerList = ObjectFileHandler.readFromFile("passengerinfo.txt");
			int l = searchPassenger(P.passportID);

			if(l != -1) {
				passengerList.remove(l);
			}

			ObjectFileHandler.writeToFile("passengerinfo.txt", passengerList);
			return true;
		}

		// if it doesn't pass the checking then return false and if 
		// the passenger is not present then insert into the passenger info
		ArrayList<Passenger> passengerList = ObjectFileHandler.readFromFile("passengerinfo.txt");
		if(searchPassenger(P.passportID) == -1) {	
			passengerList = new ArrayList<Passenger>();
			passengerList.add(P);
		} else {
			passengerList.get(searchPassenger(P.passportID)).level = P.level;
		}
		
		ObjectFileHandler.writeToFile("passengerinfo.txt", passengerList);
		
		return false;
	}
	// X-raying the Luggage if it doesn't have any metalObject
	public static boolean XRAY(Luggage L){
		if(L.hasMetalObject == false) {
			L.hasSticker = true;
			return true;
		} else {
			return false;
		}
	}

	// If in baggage CheckIn It does not have sticker then checkIn fails
	public static boolean bCheckIn(Luggage L){

		if(L.hasSticker == false) {
			System.out.println("Not have an Sticker. Repeat Procedure!!!\n");
			return false;
		}

		// If the weight of the Laguage is more then the 35 then they have to
		// pay extra charge.
		if(L.weight > 35) {
			System.out.print("Rs. 100 per extra kilo. Do you want to pay: (press)\n1 for Yes\nAny other key for No\n>> ");
			
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();

			if(choice == 1) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	// Security Check (checking for containing metal object)
	public static boolean securityCheck(Passenger P) {
		if(P.baggage.hasMetalObject == false) {
			P.stamped = true;
			P.hasBoardingPass = true;
			return true;		
		} else {
			System.out.println("Security Check not Passed. Repeat procedure!!!.\n");
			return false;
		}
	}

	// Flight Check (checking for the Boarding pass and stamp)
	public static boolean flightCheck(Passenger P) {

		if(P.hasBoardingPass == true && P.stamped == true) {
			// System.out.println("Passed Flight Check-In\n");
			return true;
		} else {
			return false;
		}
	}
}
