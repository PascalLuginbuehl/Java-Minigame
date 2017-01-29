package com.company.Game

/**
 * Created by piran on 29.1.17.
 */
class RectangleTest extends GroovyTestCase {
    void testCheckCollision() {
        Rectangle r1 = new Rectangle(new Vector(1.0, 4.0), new Vector(1.0, 1.0))
        Rectangle r2 = new Rectangle(new Vector(1.0, 5.0), new Vector(1.0, 1.0))
        assertEquals(r1.checkCollision(r2), true)
    }

    void testCheckNoCollision() {
        Rectangle r1 = new Rectangle(new Vector(1.0, 4.0), new Vector(1.0, 1.0))
        Rectangle r2 = new Rectangle(new Vector(2.0, 5.0), new Vector(1.0, 1.0))
        assertEquals(r1.checkCollision(r2), false)
    }
}
