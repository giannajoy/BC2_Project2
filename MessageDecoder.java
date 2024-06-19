/*
 * GEE
 * This is free and unencumbered software released into the public domain.
 */
package gee_p2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * The MessageDecoder class is responsible for converting a scrambled
 * message file into plain text.
 * It utilizes a linked list data structure to accomplish the decoding work.
 */
public class MessageDecoder {
    private LinkedList<Character> messageList;

    /** The head node of the linked list storing the characters. */
    private Node head;

    /**
     * A node class to store a character and its position in the message.
     */
    private class Node {
        char character;     //The character stored in this node.
        int position;       //The position of this node in the linked list
        Node next;          //A reference to the next node in the linked list.

        /**
         *
         * This constructor initializes a new `Node` object with a
         * specified character.
         *
         * @param position The position of the character in the message
         * @param character The character to be stored in the node.
         *
         */
        public Node(char character, int position) {
            this.character = character;
            this.position = position;

        }
    }

    /**
     * Constructs a MessageDecoder object and reads the encoded file
     * to initialize the messageList.
     *
     * @param fileName The name of the file containing the encoded message.
     * @throws FileNotFoundException If the specified file is not found.
     */
    public MessageDecoder(String fileName) throws FileNotFoundException {

        if (isValidFile(fileName)) {
            readAndDecodeFile(fileName);
        } else {
            throw new FileNotFoundException("Invalid file name. Please " +
                    "provide a valid file.");
        }
    }

    /**
     * Reads the scrambled message from the given file and stores it in a
     * linked list.
     *
     * @param fileName the name of the file containing the scrambled message
     * @throws FileNotFoundException if the file is not found
     */
    private void readAndDecodeFile(String fileName)
            throws FileNotFoundException {

        //Creates a File object representing the specified file.
        File file = new File(fileName);

        //Creates a Scanner object to read the contents of the file.
        Scanner scanner = new Scanner(file);

        //Iterates through each line in the file as long as there are more
        // lines to read.
        while (scanner.hasNextLine()) {

            //Reads the current line from the file.
            String line = scanner.nextLine();


            //Extracts the first character from the line
            //assuming it's the to be decoded character.
            char character = line.charAt(0);

            //Extracts the position information from the line
            //assuming it to be a number starting from the third character.
            int position = Integer.parseInt(line.substring(2));

            //Adds a new node to the linked list with the extracted character
            // and position.
            addNode(character, position);
        }

        //Closes the Scanner object to release resources.
        scanner.close();
    }

    /**
     * Adds a new node containing the character and its position to the
     * linked list.
     * @param character the character to be added
     * @param position the position of the character in the message
     */
    private void addNode(char character, int position) {

        // Create a new Node
        Node newNode = new Node(character, position);

        // Insert the node at the correct position based on its position value
        if (head == null || position < head.position) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null && position > current.next.position) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }


    /**
     * Constructs the plain text message by appending characters from the
     * linked list.
     * Method to retrieve the plain text message
     * @return The decoded plain text message.
     */
    public String getPlainTextMessage() {

        // Use a StringBuilder to efficiently construct the message
        StringBuilder result = new StringBuilder();
        Node current = head;
        while (current != null) {
            result.append(current.character);
            current = current.next;
        }
        return result.toString();
    }

    /**
     * Checks if the provided file name corresponds to a valid file in computer.
     *
     * @param fname The file name to check.
     * @return true if the file exists on computer and is not a directory.
     */
    public static boolean isValidFile(String fname) {
        // Create a File object with the specified file name
        File path = new File(fname);
        // Check if the file exists on disk and is not a directory
        boolean isValid = path.exists() && !path.isDirectory();
        // If the file is not valid, display an error message
        if (!isValid) {
            System.out.println("Invalid file name. Please provide a valid " +
                    "file.");
        }
        // Return the result of the validity check
        return isValid;

    }



}
