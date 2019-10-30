package com.VPA;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


/**
 * Welsh-Powell Algorithm
 */
 class VPA {
    private  static final Scanner sc = new Scanner(System.in);
    private int[][]neighborhoodMatrix;
    private  int matrixSize = 2;

    /**
     *
     * @param matrixSize Applicable size for neighborhood and vertex matrix
     */
     VPA(int matrixSize)
    {
        if(matrixSize > 1) this.matrixSize = matrixSize;
    }

    /**
     * takes the neighborhood matrix from the user
     */
     void  setNeighborhoodMatrix()
    {
        neighborhoodMatrix = new int[matrixSize][matrixSize];

        for (int i = 0; i < neighborhoodMatrix.length ; i++)
            for (int j = 0 ; j < neighborhoodMatrix.length ; j++)
                if(i !=j)
                {
                    boolean exit = false;
                    while (!exit)
                    {
                        System.out.println("[" + i + "][" + j + "]");
                        String temp = sc.nextLine();
                        try {
                            Integer.valueOf(temp);
                             exit = true;
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("Invalid value");
                        }
                        if(exit){
                            if(Integer.valueOf(temp) == 1 || Integer.valueOf(temp) == 0)
                            neighborhoodMatrix[i][j] = Integer.valueOf(temp);
                            else
                            {
                                System.out.println("Invalid value");
                                exit = false;
                            }

                        }
                    }

                }
                else neighborhoodMatrix[i][j] = 0;
    }

    /**
     * shows the neighborhood matrix on the screen
     */
     void getNeighborhoodMatrix()
    {
        System.out.print("     ");
        for(int i = 0; i < neighborhoodMatrix.length ; i++)
            System.out.print("  V" +(i+1));
        System.out.println();
        for (int i = 0; i< neighborhoodMatrix.length ;i ++)
        {
            System.out.print("   V" +(i+1));
            for(int j = 0; j < neighborhoodMatrix.length ; j++)
                System.out.print("   " + neighborhoodMatrix[i][j]);

            System.out.println("\n");
        }
    }

    /**
     * apply the vertex painting algorithm
     */
      void  applyVPA()
    {
        char[] vertexes = new char[matrixSize];
        Arrays.fill(vertexes,'Q');
        char color = 'R';


        //the positions of the vertex(es) to be painted in the next step
        ArrayList<Integer> pvpns= new ArrayList<>();

        //random init paint vertex
        int init = new Random().nextInt(matrixSize); // 0 to matrixSize -1
        vertexes[init] =  color;
        pvpns.add(init);
        System.out.println("Started  from the " + (init + 1) +  ". vertex");

                       //corner case
        boolean exit = checkIsolatedVertex();
        if(exit) return; //compulsory finishing cause it has isolated vertex

        while (!exit)
        {
           color = (color == 'R') ? 'B' : 'R';

         pvpns =  painting(color,pvpns,vertexes);

         exit = checkPainted(vertexes);

        }

        result(vertexes);

    }

    /**
     * paints the neighbors of the sent vertexes
     * @param color color to be painted at that moment
     * @param pvpns vertexes to be painted at that moment neighbors
     * @param vertexes  array containing the last state of vertexes
     * @return vertexes to be painted neighbors for the next round
     */
    private ArrayList<Integer> painting(char color, ArrayList<Integer> pvpns, char[] vertexes)
    {
        ArrayList<Integer> temp = new ArrayList<>();
        for(int i : pvpns)
          for (int j = 0; j < neighborhoodMatrix.length;j++)
          {
            if(neighborhoodMatrix[i][j] == 1 && vertexes[j] == 'Q')
            {
                vertexes[j] = color;
                temp.add(j);
            }
          }

        return temp;
    }

    /**
     * check whether it is painted or not
     * @param vertexes array containing the last state of vertexes
     * @return all painted status
     */
    private  boolean checkPainted(char[] vertexes)
    {
        for(char e : vertexes)
            if(e ==  'Q') return  false;
            return true;
    }

    /**
     * indicates whether a two-part graph can be drawn
     * @param vertexes array containing the last state of vertexes
     */
    private void  result(char[] vertexes)
    {

        for(int i = 0; i < neighborhoodMatrix.length ; i++)
            System.out.print("V" +(i+1) + " ");
        System.out.println("\n" + Arrays.toString(vertexes));


        for(int i = 0; i < neighborhoodMatrix.length ; i++)
        {
            for(int j = 0 ; j<neighborhoodMatrix.length ; j++)
            {
                if( neighborhoodMatrix[i][j] == 1 && vertexes[i] == vertexes[j])
                {
                    System.out.println("Two-part graph  can NOT be drawn");
                    return;
                }
            }
        }
        System.out.println("Two-part graph  can  be drawn");

    }

    /**
     *checks the presence of isolated vertex
     * @return indicates whether the algorithm is applied
     */
    private boolean checkIsolatedVertex()
    {
        boolean flag;
        for(int i = 0; i < neighborhoodMatrix.length;i++) {
            flag = true;
            for (int j = 0; j < neighborhoodMatrix.length; j++)
                if (neighborhoodMatrix[j][i] == 1)
                {
                    flag = false;
                    break;
                }

                if(flag){
                    System.out.println("Two-part graph  can be drawn");
                    return  flag;
                }
        }
        return false;
    }
}
