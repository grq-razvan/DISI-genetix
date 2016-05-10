package model

import javafx.geometry.Point2D

/**
 * Uncreated by stefangrecu on 10/05/16.
 */
class City {
    Integer id
    Point2D coord
    Boolean depot = false
    Integer demand

    public Double distanceTo(City otherCity) {
        return this.coord.distance(otherCity.coord)
    }

    public Integer supply(Integer capacity) {
        capacity -= demand
        demand = 0
        return capacity
    }
}