package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {

        //Point
        Point a= new Point(9,0);
        Point b= new Point(-3,15);
        System.out.println("Расстояние между двумя точками: " +a.distance(b));

        /*
        //Square
        Square s= new Square(5);
        System.out.println("Площадь квадрата со стороной "+s.l+" равна " +s.area());

        //Rectangle
        Rectangle r= new Rectangle(3, 7);
        System.out.println("Площадь прямоугольника со сторонами "+r.a+" и "+r.b+" равна " +r.area());*/
    }
}
