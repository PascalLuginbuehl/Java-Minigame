package com.company.Game;

/**
 * Created by Pascal on 12.12.2016.
 */
public class Vector {
    public double x;
    public double y;

    Vector(double x, double y) {
        this.x = Math.round(x * 10) / 10;
        this.y = Math.round(y * 10) / 10;
    }

    /**
     * Adds vector to vector
     * @param vector vector to add to
     * @return new vector
     */
    public Vector add(Vector vector) {
        return new Vector(Math.round((vector.x + this.x) * 10) / 10, Math.round((vector.y + this.y) * 10) / 10);
    }

    /**
     * Subtracts vector from vector
     * @param vector to subtract from
     * @return new vector
     */
    public Vector subtract(Vector vector) {
        return new Vector(Math.round((this.x - vector.x) * 10) / 10, Math.round((this.y - vector.y) * 10) / 10);
    }

    /**
     * Scales x and y
     * @param s amount scaled
     * @return scaled value
     */
    public Vector scale (double s) {
        return new Vector(Math.round((this.x * s) * 10) / 10 , Math.round((this.y * s) * 10) / 10);
    }

    public double dot(Vector vector) {
        return (this.x * vector.x + this.y * vector.y);
    }

    /**
     * Returns the smallest values of both vectors
     * @param vector other vector
     * @return new vector
     */
    public Vector smalest(Vector vector) {
        double x = this.x < vector.x ? this.x : vector.x
                , y = this.y < vector.y ? this.y : vector.y;
        return new Vector(x, y);
    }

    /**
     * Returns the biggest values of both vectors
     * @param vector other vector
     * @return new vector
     */
    public Vector biggest(Vector vector) {
        double x = this.x > vector.x ? this.x : vector.x
                , y = this.y > vector.y ? this.y : vector.y;
        return new Vector(x, y);
    }

    /**
     * Rounds the vectors
     * @return
     */
    public Vector round() {
        return new Vector(this.x > 0 ? Math.floor(this.x) : Math.ceil(this.x), this.y > 0 ? Math.floor(this.y) : Math.ceil(this.y));
    }

    /**
     * Checks if vectors are equal
     * @param v
     * @return
     */
    public boolean equal(Vector v) {
        return (this.x == v.x && this.y == v.y);
    }
}
