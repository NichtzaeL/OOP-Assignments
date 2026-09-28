import java.util.Scanner;

public class Main {
    private static Shape[] shapes = new Shape[20];
    private static int numberOfShapes = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            printMenu();
            choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> addSquare();
                case 2 -> addCircle();
                case 3 -> addCylinder();
                case 4 -> listShapes();
                case 0 -> System.out.println("\nGoodbye!");
                default -> System.out.println("\nInvalid option, try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n==============================");
        System.out.println("        SHAPE MENU");
        System.out.println("==============================");
        System.out.println("1. Add square");
        System.out.println("2. Add circle");
        System.out.println("3. Add cylinder");
        System.out.println("4. List shapes & info");
        System.out.println("0. Exit");
        System.out.println("------------------------------");
    }

    private static void addSquare() {
        System.out.println("\n--- Add Square ---");
        if (isFull()) return;

        String color = readString("Color: ");
        double side = readDouble("Side  : ");

        shapes[numberOfShapes++] = new Square(side, color);
        System.out.println("-> Square added.");
    }

    private static void addCircle() {
        System.out.println("\n--- Add Circle ---");
        if (isFull()) return;

        String color = readString("Color : ");
        double radius = readDouble("Radius: ");

        shapes[numberOfShapes++] = new Circle(radius, color);
        System.out.println("-> Circle added.");
    }

    private static void addCylinder() {
        System.out.println("\n--- Add Cylinder ---");
        if (isFull()) return;

        String color = readString("Color : ");
        double radius = readDouble("Radius: ");
        double height = readDouble("Height: ");

        shapes[numberOfShapes++] = new Cylinder(height, radius, color);
        System.out.println("-> Cylinder added.");
    }

    private static void listShapes() {
        System.out.println("\n--- Shapes ---");
        if (numberOfShapes == 0) {
            System.out.println("(no shapes yet)");
            return;
        }

        for (int i = 0; i < numberOfShapes; i++) {
            System.out.print((i + 1) + ". ");
            shapes[i].printInfo();
        }
    }

    // ---------- helpers ----------

    private static boolean isFull() {
        if (numberOfShapes >= shapes.length) {
            System.out.println("-> Storage full, cannot add more shapes.");
            return true;
        }
        return false;
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a whole number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static double readDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a number: ");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }
}