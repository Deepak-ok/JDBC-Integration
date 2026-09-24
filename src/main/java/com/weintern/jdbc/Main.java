package com.weintern.jdbc;

import java.util.List;
import java.util.Scanner;

// Console menu to test all CRUD operations against MySQL via StudentDAO.
public class Main {
    static Scanner sc = new Scanner(System.in);
    static StudentDAO dao = new StudentDAO();

    public static void main(String[] args) {
        dao.createTable();
        System.out.println("=== JDBC Student CRUD Demo ===");
        int choice;
        do {
            printMenu();
            choice = readInt();
            switch (choice) {
                case 1: insertRecord(); break;
                case 2: viewAll(); break;
                case 3: updateRecord(); break;
                case 4: deleteRecord(); break;
                case 5: System.out.println("Goodbye!"); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    static void printMenu() {
        System.out.println("\n1. Insert Student\n2. View All Students\n3. Update Grade\n4. Delete Student\n5. Exit");
        System.out.print("Enter choice: ");
    }

    static int readInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            sc.next();
        }
        int val = sc.nextInt();
        sc.nextLine();
        return val;
    }

    static double readDouble() {
        while (!sc.hasNextDouble()) {
            System.out.print("Enter a valid number: ");
            sc.next();
        }
        double val = sc.nextDouble();
        sc.nextLine();
        return val;
    }

    static void insertRecord() {
        System.out.print("ID: ");
        int id = readInt();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Age: ");
        int age = readInt();
        System.out.print("Grade: ");
        double grade = readDouble();
        if (dao.insertRecord(new Student(id, name, age, grade))) {
            System.out.println("Student inserted.");
        }
    }

    static void viewAll() {
        List<Student> list = dao.getAllRecords();
        if (list.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        System.out.printf("%-5s %-15s %-5s %-6s%n", "ID", "Name", "Age", "Grade");
        for (Student s : list) {
            System.out.printf("%-5d %-15s %-5d %-6.2f%n", s.getId(), s.getName(), s.getAge(), s.getGrade());
        }
    }

    static void updateRecord() {
        System.out.print("Enter ID to update: ");
        int id = readInt();
        System.out.print("New grade: ");
        double grade = readDouble();
        if (dao.updateRecord(id, grade)) {
            System.out.println("Updated.");
        } else {
            System.out.println("No student found with that ID.");
        }
    }

    static void deleteRecord() {
        System.out.print("Enter ID to delete: ");
        int id = readInt();
        if (dao.deleteRecord(id)) {
            System.out.println("Deleted.");
        } else {
            System.out.println("No student found with that ID.");
        }
    }
}
