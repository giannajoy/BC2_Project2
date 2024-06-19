/*
 * GEE
 *
 * This is free and unencumbered software released into the public domain.
 */
package gee_p2;


import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The SecretMessage class contains the main method and user interface for
 * the message decoding program.
 * It uses the MessageDecoder class to accomplish the decoding.
 */
public class SecretMessage {

    /**
     * The main method initiates the message decoding program, interacts with
     * the user, and handles exceptions.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) throws FileNotFoundException {
        // Create a Scanner for user input
        Scanner scanner = new Scanner(System.in);

        //Welcome message
        System.out.println("Welcome to Secret Messages!!!");
        System.out.println("This program reads an encoded message from a " +
                "file supplied by the user and");
        System.out.println("displays the contents of the message before it" +
                " was encoded.");

        // Flag to control whether to try again
        boolean tryAgain = true;

        while (tryAgain) {
            // Prompt the user to enter the file name
            System.out.print("\nEnter secret file name: ");
            // Read the file name from the user
            String fileName = scanner.nextLine();

            // Check if the file name is valid
            if (MessageDecoder.isValidFile(fileName)) {
                // Create a MessageDecoder object with the specified
                // file name
                MessageDecoder messageDecoder =
                        new MessageDecoder(fileName);
                // Display the decoded message to the user
                System.out.println("Decoded: "
                        + messageDecoder.getPlainTextMessage());
                //System.out.println(messageDecoder.getPlainTextMessage());

                // Exit the loop after successful decoding
                tryAgain = false; // Update flag to stop the loop
            }
            else {
                System.out.println("Invalid file name. Please provide a " +
                                    "valid path to a file.");
            }


            // Ask the user if they want to try again
            System.out.print("\nWould you like to try again? Yes/No in Full" +
                    "(no to exit): ");
            // Read the user's response and convert to lowercase
            String input = scanner.nextLine().toLowerCase();
            // Update the tryAgain flag based on user input
            tryAgain = input.equals("yes");
        }
        // Display a thank-you message when the user exits the program
        System.out.println("\nThank you for using the message decoder.");
        System.out.println("GoodBye! Looking forward to seeing you again!");

    }
}

