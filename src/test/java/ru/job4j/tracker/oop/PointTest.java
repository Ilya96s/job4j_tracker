package ru.job4j.tracker.oop;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.tracker.oop.Point;

public class PointTest {

    @Test
    public void when000to869then13point45() {
        Point a = new Point(0, 0, 0);
        Point b = new Point(8, 6, 9);
        double dist = a.distance3d(b);
        double expected = 13.45;
        Assert.assertEquals(expected, dist, 0.01);
    }
}
