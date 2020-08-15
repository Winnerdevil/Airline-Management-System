import java.io.*;
import java.util.*;

// Store the Passenger Detail
// Passenger 'Has-A' Entity (Aggregation)
class Passenger implements Serializable {

	int passportID;
	String name;
	int age;
	boolean hasBoardingPass;
	boolean stamped;
	int level;
	Entity baggage;

	Passenger(int passportID, float weight, boolean hasMetalObject, String name, int age) {
		baggage = new Entity(weight, hasMetalObject);
		
		this.passportID = passportID;
		this.name = name;
		this.age = age;
		this.hasBoardingPass = false;
		this.stamped = false;
		this.level = 0;
	}
}