import java.io.*;
import java.util.*;

// Entity Class to store the Luguage and baggage detail of passenger
class Entity implements Serializable {
	float weight;
	boolean hasMetalObject;

	Entity(float weight, boolean hasMetalObject) {
		this.weight = weight;
		this.hasMetalObject = hasMetalObject;
	}
}