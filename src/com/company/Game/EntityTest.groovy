package com.company.Game

/**
 * Created by Pascal on 20.1.17.
 */
class EntityTest extends GroovyTestCase {
    void testGetDirection() {
        Rectangle r1 = new Rectangle(new Vector(1.0, 4.0), new Vector(1.0, 1.0))
        Rectangle r2 = new Rectangle(new Vector(2.0, 5.0), new Vector(1.0, 1.0))
        assertEquals(r1.checkCollision(r2), false)
    }
}
