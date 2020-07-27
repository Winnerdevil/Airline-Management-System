import java.io.*;
import java.util.*;

// Store the luggage Detail
// Luggage 'Is-A' Entity (Inheritance)
class Luggage extends Entity {
	
	boolean hasSticker;

	Luggage(float weight, boolean hasMetalObject) {
		super(weight, hasMetalObject);
		this.hasSticker = false;
	}
}