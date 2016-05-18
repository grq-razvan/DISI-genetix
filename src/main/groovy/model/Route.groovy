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

    Route(Route route) {
        this.depot = route.depot
        this.cities = route.cities
    }

    Route(City depot) {
        this.depot = depot
        cities += [depot, depot]
    }

    boolean isValid(Double maxDistance, Double maxCapacity) {
        !cities.empty && getCost() <= maxDistance && getUsedCapacity() <= maxCapacity
    }

    Integer getUsedCapacity() {
        (Integer) cities.sum { City city -> city.demand }
    }

    void addCity(City city) {
        cities = cities.subList(0, cities.size() - 1) + [city] + cities.last()
    }

    public Double getCost() {
        getTotalCostInternal(cities)
    }

    public City getLastCity() {
        if (cities.size() == 2) { return cities.last() }
        return cities.get(cities.size() - 2)
    }

    public City getFirstCity() {
        return cities.get(1)
    }

    private Double getTotalCostInternal(List<City> cities) {
        if (cities.tail().empty || !cities.tail().head()) {
            return 0
        }
        return cities.head().distanceTo(cities.tail().head()) + getTotalCostInternal(cities.tail())
    }

    public getLength() {
        return cities?.size()
    }

    @Override
    String toString() {
        new StringBuilder().with {
            append "\tCities: [${cities.id.join(", ")}]" append '\n'
            append "\tCost: ${cost}" append '\n'
            append "\tCapacity: ${usedCapacity}" append '\n'
            it
        }.toString()
    }

    String getLabel() {
        new StringBuilder().with {
            append "Route" append '\n'
            append "Depot:\n ${depot.label}" append '\n'
            append "Cost: ${cost}" append '\n'
            append "Capacity ${usedCapacity}" append '\n'
            append "Cities:\n ${cities.label.join("\n")}"
            return it
        }.toString()
    }

    private void initializeCities() {
        if (depot && cities.empty) {
            cities = [depot, depot]
        }
    }
}


