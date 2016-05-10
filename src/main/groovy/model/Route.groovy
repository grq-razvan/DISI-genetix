package model

/**
 * Uncreated by stefangrecu on 10/05/16.
 */
class Route {
    City depot
    List<City> cities = [depot, depot]

    boolean isValid(Integer maxDistance) {
        !cities.empty && cost <= maxDistance
    }

    void addCity(City city) {
        cities.plus(cities.indexOf(cities.last()) - 1, [city])
    }

    public Double getCost() {
        getTotalCostInternal(cities)
    }

    private Double getTotalCostInternal(List<City> cities) {
        if (cities.tail().empty || !cities.tail().head()) {
            return 0
        }
        return cities.head().distanceTo(cities.tail().head()) + getTotalCostInternal(cities.tail())
    }
}


