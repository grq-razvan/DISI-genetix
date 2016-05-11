package model

/**
 * Uncreated by stefangrecu on 10/05/16.
 */
class Route {
    City depot
    List<City> cities = []

    Route() {
        initializeCities()
    }

    Route(City depot) {
        this.depot = depot
        cities += [depot, depot]
    }

    boolean isValid(Integer maxDistance) {
        !cities.empty && cost <= maxDistance
    }

    void addCity(City city) {
        cities = cities.subList(0, cities.size()-1) + [city] + cities.last()
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

    public getLength() {
        return cities.size()
    }
    @Override
    String toString() {
        if (!cities.empty) {
            String result = "["
            cities.each {
                result << it.id + ", "
            }
            result.substring(0, result.length() - 2) << "]"
        }

        "This route has no cities."
    }

    private void initializeCities() {
        if (depot && cities.empty) {
            cities = [depot, depot]
        }
    }
}


