package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NeighborhoodLibrary {
    public static class Library {

        private static final Book[] books = new Book[20];
        static List<Book> checkOut = new ArrayList<>();

        private static int numBooks = 5;

        public static void main(String[] args) {

            books[0] = new Book(1, "2154332161001", "My Little Pony", false, "");
            books[1] = new Book(2, "6972874898875", "Harry Pother", false, "");
            books[2] = new Book(3, "2976986081977", "Dark Seals", false, "");
            books[3] = new Book(4, "0274167526498", "Elden Ring", false, "");
            books[4] = new Book(5, "7226777899719", "Star Wars", false, "");


            Scanner myObj = new Scanner(System.in);

            boolean isDone = false;


            while (!isDone) {

                System.out.println("Store Home Screen");
                System.out.println("-----------------");
                System.out.println("1. Show Available Books");
                System.out.println("2. Show Checked Out Books");
                System.out.println("3. Exit");
                System.out.print("Enter your choice (1-3) ");

                int command = myObj.nextInt();
                myObj.nextLine();

                switch (command) {
                    case 1 -> showBooks(myObj);
                    case 2 -> checkedOutBooks(myObj);
                    case 3-> isDone = true;
                }
            }
        }

        public static void showBooks (Scanner command)  {

            System.out.println("Available Books\n-----------------");

            for (int i = 0; i < books.length; i++) {
                if (books[i] != null) {
                    System.out.println(books[i]);
                }
            }

            System.out.println("-----------------");
            System.out.print("Enter the Id of the book to check out (0 to cancel) ");
            int id = command.nextInt();
            command.nextLine();

            boolean bookFound = false;

            for (int i = 0; i < books.length; i++) {
                if (books[i] != null &&books[i].getId() == id) {
                    System.out.print("Your name: ");
                    String name = command.nextLine();

                    System.out.println("Thank you " + name + " For checking out " + books[i].getTitle());
                    books[i].checkOut(name);
                    checkOut.add(books[i]);

                    books[i] = null;
                    numBooks--;
                    bookFound = true;
                }
            }
            if (!bookFound) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public  static void checkedOutBooks (Scanner command) {
            if (checkOut.isEmpty()) {
                System.out.println("No Books are currently checked out.");
                return;
            }

            for (int i = 0; i < checkOut.size(); i++) {
                System.out.println(checkOut.get(i));
            }

            System.out.print("Press C to check in a book, or X to return ");
            String userOutput = command.nextLine();

            if (userOutput.equalsIgnoreCase("c")) {
                System.out.print("Enter the Id of the book to check in (0 to cancel) ");
                int id = command.nextInt();
                command.nextLine();

                for (int i = 0; i < checkOut.size(); i++) {
                    if (checkOut.get(i).getId() == id) {
                        Book returned = checkOut.get(i);
                        returned.checkIn();

                        for (int j = 0; j < books.length; j++) {
                            if (books[j] == null) {
                                books[j] = returned;
                                numBooks++;
                                break;
                            }
                        }
                        checkOut.remove(i);
                    }
                }
            }
        }
    }
}

