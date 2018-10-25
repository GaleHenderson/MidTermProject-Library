import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class LibraryApp {
	public static void main(String[] args) {

		// initiate scanner
		Scanner scan = new Scanner(System.in);

		// initialize variables
		String directoryFolder = "library";
		String fileName = "library.txt";

		createDirectory();
		createFile(directoryFolder, fileName);
		// writeToFile(directoryFolder, fileName);

		System.out.println("Welcome to the Grand Circus Library!");
		System.out.println("What would you like to do today!?");
		int userPick = LabValidator.getInt(scan, "Press 1 to check out a book or 2 to return a book", 1, 2);
		int userChoice = 0;

		if (userPick == 1) {
			do {
				System.out.println("1 - See list of books");
				System.out.println("2 - Search by author");
				System.out.println("3 - Search by keyword");
				System.out.println("4 - Select a book to checkout");
				System.out.println("5 - Return to main menu");

				userChoice = LabValidator.getInt(scan, "Enter menu number: ", 1, 5);
				if (userChoice == 1) {
					LibraryApp.readFromFile(directoryFolder, fileName);
				} else if (userChoice == 2) {
					String userAuthor = LabValidator.myRegex(scan, "Enter the Author's name: ", "^[A-z]+${30}");
				} else if (userChoice == 3) {
					String userKeyword = LabValidator.myRegex(scan, "Enter the Title's keyword name: ", "^[A-z]+${30}");
				} else if (userChoice == 4) {
					int userCheckout = LabValidator.getInt(scan, "Pick a book to checkout from the list: ", 1, 12);
				}
			} while (!(userChoice == 5));
		} else if (userPick == 2) {
			System.out.println("Bye!");
		}
	}

//METHODS GO HERE
	public static void createFile(String directoryFolder, String fileName) {
		Path filePath = Paths.get(directoryFolder, fileName);
		if (Files.notExists(filePath)) {
			try {
				Files.createFile(filePath);
				System.out.println("File was created successfully");
			} catch (IOException e) {
				System.out.println("Something went wrong with the file creation");
				e.printStackTrace();
			}
		} else {
		}
	}

	public static void createDirectory() {
		String dirPath = "library";

		Path folder = Paths.get(dirPath);

		if (Files.notExists(folder)) {

			try {// this is an example of a checked exception
				Files.createDirectories(folder);
			} catch (IOException e) {
				System.out.println("Something went wrong with folder creation");
				e.printStackTrace();
			}

		}
	}

	public static void writeToFile(String directoryFolder, String fileName) {
		Path filePath = Paths.get(directoryFolder, fileName);
		File file = filePath.toFile();

		try {
			// the true parameter for the FileOutputStream() constructor
			// appends data to the end of the file
			// false rewrites over the file
			PrintWriter outW = new PrintWriter(new FileOutputStream(file, true));
			outW.close(); // mandatory: this needs to be closed when you are done or it may not write all
							// of your stuff
			// to the file
		} catch (FileNotFoundException e) {
			System.out.println("The file was not found");
			e.printStackTrace();
		}
	}

	public static void readFromFile(String diretoryFolder, String fileName) {
		Path filePath = Paths.get(diretoryFolder, fileName);
		File file = filePath.toFile();

		try {
			FileReader fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);

			String line = reader.readLine();
			String[] bookLine = new String[4];
			
			// it still needs to be formatted into a table and the status needs to be
			// converted to a boolean but we now have data we can manipulate from the text
			// file :)
			while (line != null) {
				// System.out.println(line);
				bookLine = line.split(",");
//				System.out.println(bookLine[0]);
				Book a = new Book();
				System.out.println();
				a.setTitle(bookLine[0]);
				a.setAuthor(bookLine[1]);
//				a.setStatus(bookLine[2]); is erroring out because it needs to be cast from String to boolean
				a.setDueDate(bookLine[3]);
				System.out.println(a);
				line = reader.readLine();
			}
			reader.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Contact customer service");
			e.printStackTrace();
		}
	}
}
