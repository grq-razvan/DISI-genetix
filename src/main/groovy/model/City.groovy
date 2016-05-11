package model

import javafx.geometry.Point2D

/**
 * Uncreated by stefangrecu on 10/05/16.
 */
class City {
    Integer id
    Point2D coords
    Boolean depot = false
    Integer demand

    public Double distanceTo(City otherCity) {
        return this.coords.distance(otherCity.coords)
    }

    public void clearDemand() {
        if (demand) {
            demand = 0
        }
    }

    @Override
    public String toString() {
        """ID: ${id}
           Coords: (${coords.x}, ${coords.y})
           Is depot: ${depot}
           Demand: ${demand}
        """
    }
}