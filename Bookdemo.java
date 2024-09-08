//---------------------------------------------------------
// Assignment 0
// Written by: Jakson Rabinovitch - 40285726 //
//---------------------------------------------------------

/*A software that helps the store owner in keeping track of the books at the store. 
 * The driver contain a menu to display different options to keep track of the inventory (an array of books).
 * Through the use of methods, loops and if statements, the software can keep track of author names, prices, add books, etc..
*/

import java.util.Scanner;

public class Bookdemo {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        int currentIndex = 0;
        Book[] inventory;

        // Welcome message
        System.out.println("Welcome to The Book Store\n");

        // Max number of books
        System.out.print("What is the maximum amount of books your bookstore can contain? ");
        int numBooks = keyboard.nextInt();
        inventory = new Book[numBooks];

        // String for the main menu
        String menu = ("\nWhat do you want to do?\n\t1. Enter new books (password required)\n\t2. Change information of a book (password required)"
                + "\n\t3. Display all books by a specific author\n\t4. Display all books under a certain price\n\t5. Quit\n");

        final String password = "249";
        int choice;
        int attempt = 1;
        int attempt2 = 1;

        do {
            System.out.println(menu);
            System.out.print("Please enter your choice: ");
            choice = keyboard.nextInt();
            System.out.println("");

            // Switch for each case
            switch (choice) {

            // Adding a book to inventory
            case 1:
                // Loop to handle password attempts (max 12 attempts)
                while (attempt <= 12) {
                    System.out.print("Enter user password: ");
                    String userPassword = keyboard.next();

                    // Check if the entered password is correct
                    if (userPassword.equals(password)) {
                        System.out.print("Password correct.\n\nEnter the number of books you want to add: ");
                        int numBooksToAdd = keyboard.nextInt();
                        keyboard.nextLine();

                        // Check if there is enough space in the inventory for the requested number of books
                        if (numBooksToAdd <= (inventory.length - currentIndex)) {
                            // Loop to input details for each book
                            for (int i = 0; i < numBooksToAdd; i++) {
                                System.out.print("\nEnter details for Book " + (i + 1));

                                // Input book details
                                System.out.print("\nTitle: ");
                                String title = keyboard.nextLine();

                                System.out.print("Author: ");
                                String author = keyboard.nextLine();

                                System.out.print("ISBN: ");
                                long isbn = keyboard.nextLong();

                                System.out.print("Price: ");
                                double price = keyboard.nextDouble();
                                keyboard.nextLine();

                                // Create a new Book object using the parameterized constructor
                                Book newBook = new Book(title, author, isbn, price);

                                // Add the new Book object to the inventory array
                                inventory[currentIndex++] = newBook;
                            }
                            System.out.println("Books added successfully\n");

                        } else {
                            // Not enough space in the inventory
                            int remainingSpace = inventory.length - currentIndex;
                            System.out.println("Not enough space. Maximum remaining places in the inventory: " + remainingSpace);
                        }
                        // Exit the loop after successfully adding books
                        break;
                    } else if (attempt % 3 == 0 && attempt != 12) {
                        // Incorrect password (every 3rd attempt)
                        System.out.println("Incorrect password.");
                        attempt++;
                        // Exit the loop if max attempts reached
                        break;
                    } else if (attempt == 12) {
                        // Program detected suspicious activities and terminates
                        System.out.println("Program detected suspicious activities and will terminate immediately!");
                        System.exit(0);
                    } else {
                        // Incorrect password, prompt for a retry
                        System.out.println("Incorrect password. Try Again: ");
                        attempt++;
                    }
                }
                // Exit the switch statement
                break;

                
            // Change information of a book
            case 2:
                // Loop to handle password attempts
                while (attempt2 > 0) {
                    System.out.print("Enter user password: ");
                    String userPassword = keyboard.next();

                    // Check if the entered password is correct
                    if (userPassword.equals(password)) {
                        System.out.print("Enter the book index you wish to update (0 to " + (currentIndex - 1) + "): ");
                        int bookIndex = keyboard.nextInt();

                        // Check if the entered book index is valid
                        if (bookIndex < 0 || bookIndex >= currentIndex || inventory[bookIndex] == null) {
                            System.out.println("Invalid option. Will exit option.");
                            break;
                        } else if (bookIndex >= 0 && bookIndex < currentIndex && inventory[bookIndex] != null) {
                            // Display current information of the selected book
                            System.out.println(inventory[bookIndex].toString());

                            int attributeChoice;
                            do {
                                // Display menu for attribute changes
                                System.out.println("\nChoose an attribute to change:\n\t1. Title\n\t2. Author\n\t3. ISBN\n\t4. Price\n\t5. Quit");
                                System.out.print("Enter your choice> ");
                                attributeChoice = keyboard.nextInt();
                                keyboard.nextLine();

                                switch (attributeChoice) {
                                    case 1:
                                        System.out.print("Enter new title: ");
                                        String newTitle = keyboard.nextLine();
                                        inventory[bookIndex].setTitle(newTitle);
                                        break;

                                    case 2:
                                        System.out.print("Enter new author: ");
                                        String newAuthor = keyboard.nextLine();
                                        inventory[bookIndex].setAuthor(newAuthor);
                                        break;

                                    case 3:
                                        System.out.print("Enter new ISBN: ");
                                        long newISBN = keyboard.nextLong();
                                        inventory[bookIndex].setISBN(newISBN);
                                        break;

                                    case 4:
                                        System.out.print("Enter new price: ");
                                        double newPrice = keyboard.nextDouble();
                                        inventory[bookIndex].setPrice(newPrice);
                                        break;

                                    case 5:
                                        System.out.println("Quitting attribute change.");
                                        break;

                                    default:
                                        System.out.println("Invalid Selection. Try Again");
                                }
                                // Display updated information of the book
                                System.out.println(inventory[bookIndex].toString());
                            } while (attributeChoice < 1 || attributeChoice > 5);
                            // Exit the switch statement
                            break;
                        }
                    } else if (attempt2 % 3 == 0) {
                        // Incorrect password (every 3rd attempt)
                        System.out.println("Incorrect password.");
                        attempt2++;
                        // Exit the loop if max attempts reached
                        break;
                    } else {
                        // Incorrect password, prompt for a retry
                        System.out.println("Incorrect password. Try Again: ");
                        attempt2++;
                    }
                }
                // Exit the switch statement
                break;
                
                //Display books from same author 
                case 3:
                	keyboard.nextLine(); //junk
                    System.out.print("Enter author's name: ");
                    String authorName = keyboard.nextLine();
                    boolean foundBooks = false;

                    // Check if there are books by the specified author
                    for (Book book : inventory) {
                        if (book != null && book.getAuthor().equalsIgnoreCase(authorName)) {
                            // Display the book information
                            System.out.println("");
                        	System.out.println(book.toString());
                            foundBooks = true;
                        }
                    }

                    if (!foundBooks) {
                        System.out.println("\nNo books found by the author: " + authorName);
                    }
                    break;
                
                   // Display all books under a certain price
                case 4:
                    System.out.print("Enter the maximum price: ");
                    double maxPrice = keyboard.nextDouble();

                    // Check and display books under the specified price
                    boolean foundBooksUnderPrice = false;

                    // Iterate through the inventory to find books under the specified price
                    for (Book book : inventory) {
                        if (book != null && book.getPrice() < maxPrice) {
                            // Display the book information
                        	System.out.println("");
                            System.out.println(book.toString());
                            foundBooksUnderPrice = true;
                        }
                    }

                    // Check if any books were found under the specified price
                    if (!foundBooksUnderPrice) {
                        System.out.println("No books found under the specified price: " + maxPrice + "$");
                    }

                    // Exit the switch statement
                    break;

                case 5:
                    // Display closing message
                    System.out.println("Thank you for using The Book Store. Goodbye!");

                    // Exit the program
                    System.exit(0);

                // Default case for invalid choices
                default:
                    System.out.println("Sorry that is not a valid choice. Try again.");
                    break;
                    }  
       
        } while (choice != 5);
        keyboard.close(); // Close scanner 
    }
}

		
	
            
	
          

