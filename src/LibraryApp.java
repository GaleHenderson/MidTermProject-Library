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
import java.util.ArrayList;
import java.util.Scanner;



public class LibraryApp {
	public static void main(String[] args) {

		// initiate scanner
		Scanner scan = new Scanner(System.in);

		// initialize variables
		String directoryFolder = "library";
		String fileName = "library.txt";

		// createDirectory();
		// createFile(directoryFolder, fileName);

		// writeToFile(directoryFolder, fileName);
		ArrayList<Book> books = new ArrayList<>();

		// writeToFile(directoryFolder, fileName);

		System.out.println("Welcome to the Grand Circus Library!");
		System.out.println("What would you like to do today!?");
		int userPick = LabValidator.getInt(scan, "Press 1 to check out a book or 2 to return a book", 1, 2);
		int userChoice = 0;

		if (userPick == 1) {
			do {
				System.out.println("1 - See list of books");
				System.out.println("2 - Search by the author");
				System.out.println("3 - Search by the title ");
				System.out.println("4 - Return to main menu");

				userChoice = LabValidator.getInt(scan, "Enter menu number: ", 1, 5);
				if (userChoice == 1) {
					books = readFromFile(directoryFolder, fileName);
					int counter = 1;
					for (Book book : books) {
						System.out.println(counter++ + ". " + book);
					}
					String user1st = LabValidator.getString(scan, "Would you like to check out a book? y/n");
					if (user1st.equalsIgnoreCase("y")) {

					}
				} else if (userChoice == 2) {
					// String userAuthor = LabValidator.myRegex(scan, "Enter the Author's name: ",
					// "^[A-z]+${30}");
					searchForAuthorOfBook(scan, readFromFile(directoryFolder, fileName));
				} else if (userChoice == 3) {

					SearchForTitleOfBook(scan , readFromFile(directoryFolder , fileName));
					//					String userKeyword = LabValidator.myRegex(scan, "Enter the Title's keyword name: ", "^[A-z]+${30}");
				} else if (userChoice == 4) {
					int userCheckout = LabValidator.getInt(scan, "Pick a book to checkout from the list: ", 1, 12);
				}
			} while (!(userChoice == 5));
		} else if (userPick == 2) {
			System.out.println("Bye!");
		}
	}

	public static void searchForAuthorOfBook(Scanner scan, ArrayList<Book> bookArrayList) {

		String userSelection = LabValidator.getString(scan, "Please enter the name of the author.");
		int available = 0;
		for (Book book : bookArrayList) {
			if (book.getAuthor().contains(userSelection.toUpperCase())) {      // added the toUpperCase to make sure user input will be understood by the search 
				System.out.println(book);
				available = available + 1;
			}

		}

		if (available < 1) {
			System.out.println("Sorry thats not available");

		}

	
	}
	public static void SearchForTitleOfBook(Scanner scan, ArrayList<Book> searchForTitle) {

		String userSelection = LabValidator.getString(scan, "Please enter the name of the book your searching for:");
		int available = 0;
		for (Book book : searchForTitle) {
			if (book.getTitle().contains(userSelection.toUpperCase())) {         
				System.out.println(book);
				

				available = available + 1;
			}

		}

		if (available < 1) {
			System.out.println("Sorry thats not available");

		}

	}
	

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

	public static ArrayList<Book> readFromFile(String diretoryFolder, String fileName) {
		Path filePath = Paths.get(diretoryFolder, fileName);
		File file = filePath.toFile();
		ArrayList<Book> bookList = new ArrayList<>();

		try {
			FileReader fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);

			String line = reader.readLine();
			String[] bookLine = new String[4];

			while (line != null) {
				bookLine = line.split(",");

				Book a = new Book();
				a.setTitle(bookLine[0].toUpperCase());        // added the toUpperCase to ensure that our search method will recognize any case entered by user 
				a.setAuthor(bookLine[1].toUpperCase());
				a.setStatus(bookLine[2].toUpperCase());
				a.setDueDate(bookLine[3].toUpperCase());
				bookList.add(a);
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
		return bookList;
	}
}
