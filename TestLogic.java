// import Entity.*;
// import File.*;

// public class TestLogic {
//     public static void main(String[] args) {
//         // We create an array (a shelf) that can hold 10 books
//         Book[] myBooks = new Book[10];

//         // changed line here: creating test Book objects to see if saving works
//         myBooks[0] = new Book("B001", "Java Programming", "A great coding book");
//         myBooks[1] = new Book("B002", "Cyber Security", "Offensive security guide");

//         System.out.println("--- Testing Save ---");
//         // changed line here: calling your specific method name 'saveToFile'
//         FileIo.saveToFile(myBooks);
//         System.out.println("Done! Check your books.txt file.");

//         // Now we create a fresh empty array to load data into
//         Book[] loadedBooks = new Book[10];

//         System.out.println("\n--- Testing Load ---");
//         // changed line here: calling your specific method name 'loadFromFile'
//         FileIo.loadFromFile(loadedBooks);

//         // We loop through the array to see what was loaded
//         for (int i = 0; i < loadedBooks.length; i++) {
//             if (loadedBooks[i] != null) {
//                 // changed line here: printing the details of each book found
//                 System.out.println("Book Found: " + loadedBooks[i].getName() + " [" + loadedBooks[i].getId() + "]");
//             }
//         }
//     }
// }