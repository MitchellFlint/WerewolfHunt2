//  File: MonsterClass.java
//  By: Ilya Vasey, Mitchell Flint
//  Date: 4/26/2022
//
//  Description: This is a small game where you move a werewolf to close in on a human
//  to eat its tasty, yummy flesh. There are now multiple werewolfs

import java.util.Arrays;
import java.util.Scanner;

public class MonsterClass {
    public static void main(String [] args) {
        final int SIDE_WIDTH = 12;
        char[][] points = new char[SIDE_WIDTH][SIDE_WIDTH]; // 12x12 gamescreen
        int[] werewolfLocation = new int[2];   // i j location for werewolf
        int[] humanLocation = new int[2];      // i j location for human

        // initialize gamescreen
        for (int i = 0; i < points.length; i++)
            for (int j = 0; j < points[i].length; j++)
                points[i][j] = '.';

        Scanner input = new Scanner(System.in);

        // prompt user to enter location of werewolf
        System.out.print("Enter (i,j) location of werewolf: ");
        werewolfLocation[0] = input.nextInt();
        werewolfLocation[1] = input.nextInt();

        points[werewolfLocation[0]][werewolfLocation[1]] = 'W';

        // prompt user to enter location of human
        System.out.print("Enter (i, j) location of human: ");
        humanLocation[0] = input.nextInt();
        humanLocation[1] = input.nextInt();

        points[humanLocation[0]][humanLocation[1]] = 'H';

        for(int numMoves = 0; true; numMoves++) {
            int userIn;

            displayPoints(points);

            // winscreen
            if (Arrays.equals(werewolfLocation, humanLocation)) {
                System.out.println("The werewolf found the human in " + numMoves + " moves!");
                break;
            }

            // prompts user for move
            System.out.print("Enter move (7: j--, 8: i++, 9: i--, 0: j++): ");
            userIn = input.nextInt();

            // filters out correct inputs
            if (userIn == 7 || userIn == 8 || userIn == 9 || userIn == 0) {
                moveWolf(userIn, werewolfLocation, points);
            }
            else break;
        }
    }

    // Method Name: displayPoints
    // Parameter: Char[][] p
    // Purpose: displays the points of a 2D array (p)
    // of characters in the command line with
    // coordinates at the top
    public static void displayPoints(char[][] p) {
        System.out.println("Current gamescreen:");

        // print j coordinates points
        for (int j = 0; j < p[0].length; j++) {
            System.out.print(j % 10);
        }

        System.out.println(); // new line

        // prints gamescreen
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length; j++) {
                System.out.print(p[i][j]); // prints the character in a specific point
            }
            System.out.println(); // new line
        }
    }

    // Method Name: moveWolf
    // Parameter: int command, int[] wLoc, char[][] p
    // Purpose: changes the coordinates "wLoc" of something on
    // a 2D array using a command, which is either 7, 8, 9, or
    // 0, then changes the coordinate point "wLoc" on the given
    // array "p"
    //
    // Possible commands:
    // 7 moves the point "left"
    // 8 moves the point "down"
    // 9 moves the point "up"
    // 0 moves the point "right"
    public static void moveWolf(int command, int[] wLoc, char[][] p) {

        // changes the coordinates of the wolf
        switch (command) {
            case 7 -> wLoc[1]--;
            case 8 -> wLoc[0]++;
            case 9 -> wLoc[0]--;
            case 0 -> wLoc[1]++;
        }

        // updates the gamescreen
        p[wLoc[0]][wLoc[1]] = 'W';
    }
}