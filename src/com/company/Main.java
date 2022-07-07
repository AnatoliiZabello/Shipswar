package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static int firstPlayerShootShips = 0;
    public static int secondPlayerShootShips = 0;
    public static final int amountOfShips = 3;

    public static void main(String[] args) {

        char[][] firstPlayerShips = new char[8][8];
        char[][] secondPlayerShips = new char[8][8];
        char[][] firstPlayerShoot = new char[8][8];
        char[][] secondPlayerShoot = new char[8][8];


        for (int i = 1; i < amountOfShips + 1; i++) {
            PutAndCheckTheShip(firstPlayerShips);
            //placeTheShipByRandom(firstPlayerShips);
            System.out.println("Progress " + i + "/" + amountOfShips);
        }

        System.out.println("The Second player:");
        for (int i = 1; i < amountOfShips + 1; i++) {
            PutAndCheckTheShip(secondPlayerShips);
            //placeTheShipByRandom(secondPlayerShips);
            System.out.println("Progress " +i + "/" + amountOfShips);
        }

        System.out.println("\n All courses are made!\n \n \n \n \n \n ");

        while (true) {



            ShootingInShips(secondPlayerShips, firstPlayerShoot, true);
            if (firstPlayerShootShips == amountOfShips) {
                break;
            }



            ShootingInShips(firstPlayerShips,secondPlayerShoot, false);
            if (secondPlayerShootShips == amountOfShips) {
                break;
            }





        }
//можливо засунути це в PutAndCheckTheShip



    }

    public static int convertSymbol(String symbol) {
        if (symbol.equals("A")) {
            return 0;
        }
        if (symbol.equals("B")) {
            return 1;
        }
        if (symbol.equals("C")) {
            return 2;
        }
        if (symbol.equals("D")) {
            return 3;
        }
        if (symbol.equals("E")) {
            return 4;
        }
        if (symbol.equals("F")) {
            return 5;
        }
        if (symbol.equals("G")) {
            return 6;
        }
        if (symbol.equals("H")) {
            return 7;
        }
        return 0;



    }

    public static Boolean isTheColumnRight(String column) {

        if (column.length() != 1) {
            return false;
        }

        return column.equals("A") ||
                column.equals("B") ||
                column.equals("C") ||
                column.equals("D") ||
                column.equals("E") ||
                column.equals("F") ||
                column.equals("G") ||
                column.equals("H");
    }

    public static Boolean isTheRowRight(int row) {
        return row <= 8 && row >= 1;

    }

    public static void placeTheShipByRandom(char[][] ships) {
        Random rand = new Random();
        int row = rand.nextInt(0, 8);
        int column = rand.nextInt(0, 8);
        if (ships[row][column] == '@') {
            placeTheShipByRandom(ships);
        }
        else
            ships[row][column] = '@';

    }


    public static void printBattleground(char[][] secondPlayerShips) {
        System.out.println("  A B C D E F G H");
        for (int i = 0; i < secondPlayerShips.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < secondPlayerShips[i].length; j++) {
                System.out.print(secondPlayerShips[i][j] + " ");
            }
            System.out.print("\n");
        }

        System.out.print("\n");
    }


    public static void PutAndCheckTheShip(char[][] ships){
        Scanner scanner = new Scanner(System.in);
        int row;

        while (true) {
            System.out.println("Input row (example: 1)");
            if (scanner.hasNextInt()) {
                row = scanner.nextInt();

                if(isTheRowRight(row)){
                    break;
                }
                else{
                    System.out.println("Error input for row, row must be between 1 and 8");
                    //row = scanner.nextInt();
                }

            }
            else {
                scanner.next();
                System.out.println("Error input for row!");
            }
        }
        System.out.println("row = " + row);


        System.out.println("Input column: (example: A)");

        String column = scanner.next();

        while (!isTheColumnRight(column)) {
            System.out.println("Error! not right input! input column: (example: A)");
            column = scanner.next();
        }


        if (ships[row-1][convertSymbol(column)] == '@'){
            System.out.println("Error! This place is already busy\n");
            PutAndCheckTheShip(ships);
        }
        else{
            ships[row-1][convertSymbol(column)] = '@';

            printBattleground(ships);
        }
    }






    public static void ShootingInShips(char[][] ships, char[][] shoots, boolean firstPlayerOrSecondPlayerTurn){
        if (firstPlayerOrSecondPlayerTurn) {
            System.out.println("Now First Player is shooting\n");
        }
        else
            System.out.println("Now Second Player is shooting\n");

    Scanner scanner = new Scanner(System.in);
    int row;

    while (true) {
        System.out.println("Input row (example: 1)");
        if (scanner.hasNextInt()) {
            row = scanner.nextInt();

            if(isTheRowRight(row)){
                break;
            }
            else{
                System.out.println("Error input for row, row must be between 1 and 8");
                //row = scanner.nextInt();
            }

        }
        else {
            scanner.next();
            System.out.println("Error input for row!");
        }
    }
    System.out.println("row = " + row);


    System.out.println("Input column: (example: A)");

    String column = scanner.next();

    while (!isTheColumnRight(column)) {
        System.out.println("Error! not right input! input column: (example: A)");
        column = scanner.next();
    }
    if (ships[row-1][convertSymbol(column)] == '@'){
            shoots [row-1][convertSymbol(column)] = 'X';
            printBattleground(shoots);
            if (firstPlayerOrSecondPlayerTurn) {
                firstPlayerShootShips++;
                if (firstPlayerShootShips == amountOfShips) {
                    System.out.println("First Player Won!");
                    return;
                }
            }
            else {
                secondPlayerShootShips++;
                if (secondPlayerShootShips == amountOfShips) {
                    System.out.println("Second Player Won!");
                    return;
                }
            }


            ShootingInShips(ships, shoots, firstPlayerOrSecondPlayerTurn);

        }
        else{
        shoots [row-1][convertSymbol(column)] = 'M';
        printBattleground(shoots);
    }





}

}



