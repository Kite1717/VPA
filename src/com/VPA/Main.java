package com.VPA;

public class Main {
    public static void main(String[] args) {
      VPA myVPA = new VPA(6);
      myVPA.setNeighborhoodMatrix();
      myVPA.getNeighborhoodMatrix();
      myVPA.applyVPA();
    }
}
