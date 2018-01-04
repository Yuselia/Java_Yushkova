package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance()
  {
    Point a1= new Point(5,4);
    Point b1= new Point(1,1);
    Assert.assertEquals(a1.distance(b1), 5.0);

    Point a2= new Point(7,7);
    Point b2= new Point(7,7);
    Assert.assertEquals(a2.distance(b2), 0.0);

    Point a3= new Point(0,0);
    Point b3= new Point(-4,-3);
    Assert.assertEquals(a3.distance(b3), 5.0);
  }
}
