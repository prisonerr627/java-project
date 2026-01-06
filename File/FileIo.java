package File;

import Entity.*;
import java.awt.print.Book;
import java.io.*;
import java.util.*;

public class FileIo {
    public static void loadFormFile(Book[] books) {
        // public → accessible from anywhere
        // static → can be called without creating an object
        // Books[] books → array where loaded books will be stored

        try {
            Scanner sc = new Scanner(new File("books.txt"));

            int idx = 0;

            while (sc.hasNextLine() && idx < books.length) {
                String data[] = sc.nextLine().split(";");
                // Reads one line from file
                // Splits it using semicolon (;) into an array
                // Example line: 221-15-1234;Rahim;3.75

                books[idx] = new Book(data[0] // Book ID
                        , data[1] // Book Name
                        , data[2] // Book Description
                );
                idx++;
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

    }
}

// This takes your list of students and writes them into the text file
    public static void saveToFile(Book[] books) {
        try {
            FileWriter writer = new FileWriter("books.txt");
            for (int i = 0; i < books.length; i++) {
                if (books[i] != null) {
                    writer.write(books[i].getId() + ";" + books[i].getName() + ";" + books[i].getDescription() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
