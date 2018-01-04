package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {

        Point a= new Point(1,1);
        Point b= new Point(5,4);
        System.out.println("Расстояние между двумя точками: " +a.distance(b));
    }
}
