

/*A class that is used for the driver. It contain five attributes ( title, author, ISBN, price and book count).
 * There are various methods such as setter and getters, a method to see if books are identical, a class to find books created, and many more.
 */

public class Book {

	private String title;
	private String author;
	private long isbn;
	private double price;
	static int bookCount =0;
	
	//constructor
	public Book(String title, String author, long isbn, double price) {
		this.author = author;
		this.title = title;
		this.price = price;
		this.isbn = isbn;
		bookCount++;
	}
	
	//accessor methods
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}         
	public long getISBN() {
		return isbn;
	}
	public double getPrice() {
		return price;
	}
	
	//mutator methods
	public void setTitle(String title) {
		this.title = title;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setISBN(long isbn) {
		this.isbn = isbn;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	//method to find amount of books
	public void findNumberOfCreatedBooks() {
		if (bookCount>0)
			System.out.println("There has been " + bookCount + " books created\n");
		else 
			System.out.println("0\n");
	}
	
	//method to compare books
	public boolean equals(Book otherBook) {
		return otherBook.getPrice() == this.price && otherBook.getISBN() == this.isbn;
	}
	
	//method to display book 
	public String toString() {
		return "Author: " + author + "\nTitle: " + title + "\nISBN: " + isbn + "\nPrice: $" +price;
	}
}

