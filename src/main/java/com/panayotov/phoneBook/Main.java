package com.panayotov.phoneBook;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        PhoneBook phoneBook = new PhoneBook();
        File file = new File(Main.class.getResource("/numbers.csv").getFile());
        phoneBook.load(file);

        Scanner input = new Scanner(System.in);

        while (true) {
            printUserOptions();
            int userOption;
            try {
                userOption = input.nextInt();
            } catch (InputMismatchException e){
                System.err.println("Invalid option entered. Program terminates.");
                return;
            }
            dealWithUserRequest(phoneBook, userOption, input);
        }
    }

    private static void dealWithUserRequest(PhoneBook phoneBook, int userOption, Scanner input) {
        switch (userOption) {
            case 1:
                printRecords(phoneBook);
                break;
            case 2:
                addNewRecord(phoneBook, input);
                break;
            case 3:
                deleteRecord(phoneBook, input);
                break;
            case 4:
                findNumber(phoneBook, input);
                break;
            case 5:
                phoneBook.printFiveMostSearchedPhoneNumbers();
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("Please choose a valid option!");
        }
    }

    private static void findNumber(PhoneBook phoneBook, Scanner input) {
        System.out.println("Enter name");
        String name = input.next();

        System.out.println(phoneBook.findNumber(name));
    }

    private static void deleteRecord(PhoneBook phoneBook, Scanner input) {
        System.out.println("Enter name to delete: ");
        String name = input.next();

        phoneBook.delete(name);
    }

    private static void addNewRecord(PhoneBook phoneBook, Scanner input) {
        System.out.println("Enter name: ");
        String name = input.next();

        System.out.println("Enter number: ");
        String number = input.next();

        Record record = new Record(name, number);
        phoneBook.add(record);
    }

    private static void printRecords(PhoneBook phoneBook) {
        phoneBook.printRecords();
    }

    private static void printUserOptions() {
        System.out.println("\nPlease choose one of the following options:");
        System.out.println("Enter 1 to print all phone book records sorted by name");
        System.out.println("Enter 2 to add new phone book record");
        System.out.println("Enter 3 to delete phone book record by name");
        System.out.println("Enter 4 to search for phone number by name");
        System.out.println("Enter 5 to print the five most searched phone numbers ");

        System.out.println("Enter 0 to exit");
    }
}
