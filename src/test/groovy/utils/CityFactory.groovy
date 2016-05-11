package utils

import javafx.geometry.Point2D
import model.City

/**
 * Uncreated by stefangrecu on 11/05/16.
 */
class CityFactory {

    public
    static City create(Integer id = 1, Double x = 0.0, Double y = 0.0, Integer demand = 0, Boolean isDepot = false) {
        return new City().with {
            it.id = id
            it.coords = new Point2D(x, y)
            it.demand = demand
            it.depot = isDepot
            it
        }
    }

}
