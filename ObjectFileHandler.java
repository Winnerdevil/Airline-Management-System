import java.io.*;
import java.util.*;

class ObjectFileHandler {

	// Function to read the Passenger detail from given file and return 
	// in form of ArrayList.
	public static ArrayList<Passenger> readFromFile(String filePath) {
		
		ArrayList<Passenger> passInfo = null;

		try {
			FileInputStream fin = new FileInputStream(filePath);
			ObjectInputStream oos = new ObjectInputStream(fin);

			passInfo = (ArrayList<Passenger>)oos.readObject();
			
			oos.close();
			fin.close();
		} catch(FileNotFoundException e) {
			System.out.println("FileNotFoundException");
			return null;
		} catch(Exception e) {
			return null;
		}

		return passInfo;
	}

	// Function to Write/Overwrite the given data into file
	public static void writeToFile(String filePath, ArrayList<Passenger> passInfo) {
		try {
			FileOutputStream fin = new FileOutputStream(filePath);
			ObjectOutputStream oos = new ObjectOutputStream(fin);

			oos.writeObject(passInfo);
			oos.close();
			fin.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	// Function to append the given data into the file
	public static void appendToFile(String filePath, ArrayList<Passenger> passInfo) {
		ArrayList<Passenger> temp = readFromFile(filePath);

		if(temp != null) {
			temp.addAll(passInfo);
			writeToFile(filePath, temp);
		} else {
			writeToFile(filePath, passInfo);
		}
	}
}