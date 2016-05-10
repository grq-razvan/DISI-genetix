package model

import javafx.geometry.Point2D

/**
 * Uncreated by stefangrecu on 10/05/16.
 */
class City {
    Point2D coord
    Boolean depot
    Integer demand

    public Double distanceTo(City otherCity) {
        return this.coord.distance(otherCity.coord)
    }
}