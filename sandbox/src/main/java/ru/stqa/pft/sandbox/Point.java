package ru.stqa.pft.sandbox;

public class Point {
  public double x;
  public double y;

  public Point(int x, int y)
  {
    this.x=x;
    this.y=y;
  }

  public double distance(Point p)
  {
    return Math.sqrt(Math.pow((this.x-p.x), 2.0)+Math.pow((this.y-p.y), 2.0));
  }
}
