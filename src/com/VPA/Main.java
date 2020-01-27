package com.VPA;

import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        int matrixSize ;
        while (true)
        {
            System.out.println("Enter the square matrix of size");

            try {
                matrixSize = Integer.parseInt(sc.nextLine());
                if(matrixSize >= 2)
                break;
            }
            catch (NumberFormatException ignored)
            {

            }

        }
      VPA myVPA = new VPA(matrixSize);
      myVPA.setNeighborhoodMatrix();
      myVPA.getNeighborhoodMatrix();
      myVPA.applyVPA();
    }
}
