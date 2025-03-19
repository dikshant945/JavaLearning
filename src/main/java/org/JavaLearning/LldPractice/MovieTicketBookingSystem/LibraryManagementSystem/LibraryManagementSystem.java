package org.JavaLearning.LldPractice.MovieTicketBookingSystem.LibraryManagementSystem;
//
//Design a Library Management System where users can borrow and return books. Implement the following functions using OOP concepts:
//
//addBook(String bookTitle, String author, int copies)
//
//Adds a new book to the library.
//borrowBook(String bookTitle, String userName)
//
//Allows a user to borrow a book if copies are available.
//returnBook(String bookTitle, String userName)
//
//Allows a user to return a borrowed book.
//getAvailableCopies(String bookTitle)
//
//Returns the number of available copies of a book.
//getUserBorrowedBooks(String userName)
//
//Returns a list of book titles borrowed by a user.


import java.util.*;

class Book {
    private  String bookAuthor;
    private int copies;
    public Book(String bookAuthor, int copies) {
        this.bookAuthor = bookAuthor;
        this.copies = copies;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }
}

public class LibraryManagementSystem {

    private Map<String,Book> bookRecord;
    private Map<String,Set<String>> userBookings;

    public LibraryManagementSystem() {
        bookRecord = new HashMap<>();
        userBookings = new HashMap<>();
    }

    public void addBook(String bookTitle, String author, int copies) {
        Book book = new Book(bookTitle, copies);
        if(bookRecord.containsKey(bookTitle)) {
            System.out.println("Book Already Exists");
        }
        bookRecord.put(bookTitle, book);
    }

    public boolean borrowBook(String bookTitle, String userName) {
        if(!bookRecord.containsKey(bookTitle)){
            System.out.println("No Such book is available !");
            return false;
        }

        int copies = bookRecord.get(bookTitle).getCopies();
        if(copies <= 0) {
            return  false;
        }

        if(userBookings.containsKey(userName)){
            userBookings.get(userName).add(bookTitle);
        }else{
            Set<String>borrowedBooks = new HashSet<>();
            borrowedBooks.add(bookTitle);
            userBookings.put(userName, borrowedBooks);
        }

        bookRecord.put(bookTitle, new Book(bookRecord.get(bookTitle).getBookAuthor(), bookRecord.get(bookTitle).getCopies()-1));

        return true;
    }


    public boolean returnBook(String bookTitle, String userName) {
        if(!userBookings.containsKey(userName)){
            System.out.println("No Such user Present");
            return false;
        }

        Set<String> borrowedBooks = userBookings.getOrDefault(userName, null);
        if(borrowedBooks == null || !borrowedBooks.contains(bookTitle) ) {
            return false;
        }

        borrowedBooks.remove(bookTitle);
        bookRecord.put(bookTitle, new Book(bookRecord.get(bookTitle).getBookAuthor(), bookRecord.get(bookTitle).getCopies() +1));

        return true;
    }

    public int getAvailableCopies(String bookTitle) {
        if(!bookRecord.containsKey(bookTitle)) {
            return  0;
        }

        return bookRecord.get(bookTitle).getCopies();
    }

    public List<String> getUserBorrowedBooks(String userName) {
        Set<String> borrowedBooks = userBookings.getOrDefault(userName, new HashSet<>());
        return borrowedBooks.stream().toList();
    }

    public static void main(String[] args) {
        LibraryManagementSystem system = new LibraryManagementSystem();

        // Test Case 1: Add books
        system.addBook("The Great Gatsby", "F. Scott Fitzgerald", 3);
        system.addBook("1984", "George Orwell", 2);

        System.out.println("Available copies of 'The Great Gatsby': " + system.getAvailableCopies(
            "The Great Gatsby")); // Expected: 3

        // Test Case 2: Borrow books
        boolean borrow1 = system.borrowBook("The Great Gatsby", "Alice");
        System.out.println("Borrow status: " + borrow1); // Expected: true
        System.out.println("Available copies of 'The Great Gatsby': " + system.getAvailableCopies(
            "The Great Gatsby")); // Expected: 2

        // Test Case 3: Get user borrowed books
        System.out.println("Alice's borrowed books: " + system.getUserBorrowedBooks(
            "Alice")); // Expected: ["The Great Gatsby"]

        // Test Case 4: Return book
        boolean return1 = system.returnBook("The Great Gatsby", "Alice");
        System.out.println("Return status: " + return1); // Expected: true
        System.out.println("Available copies of 'The Great Gatsby': " + system.getAvailableCopies(
            "The Great Gatsby")); // Expected: 3
    }
}
