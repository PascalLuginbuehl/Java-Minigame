package com.company.Game

/**
 * Created by Pascal on 20.1.17.
 */
class VectorTest extends GroovyTestCase {
    void testAdd() {
        Vector v = new Vector(0.0, 4.0)
        Vector v2 = new Vector(3.0, 0.0)
        Vector result = v2.add(v)
        assertEquals(result.x, 3.0)
    }

    void testSubtract() {
        Vector v = new Vector(4.0, 0.0)
        Vector v2 = new Vector(3.0, 0.0)
        Vector result = v2.subtract(v)
        assertEquals(result.x, -1.0)
    }

    void testScale() {
        Vector v = new Vector(4.0, 5.0)
        Vector result = v.scale(2.0)
        assertEquals(result.x, 8.0)
    }

    void testSmalest() {
        Vector v = new Vector(4.0, 1.0)
        Vector v2 = new Vector(1.0, 3.0)
        Vector result = v2.smalest(v)
        assertEquals(result.x, 1.0)
    }

    void testBiggest() {
        Vector v = new Vector(4.0, 1.0)
        Vector v2 = new Vector(1.0, 3.0)
        Vector result = v2.biggest(v)
        assertEquals(result.x, 4.0)
    }

    void testEqual() {
        Vector v = new Vector(4.0, 1.0)
        Vector v2 = new Vector(4.0, 1.0)
        Vector result = v2.biggest(v)
        assertEquals(v2.equal(v), true)
    }
}
