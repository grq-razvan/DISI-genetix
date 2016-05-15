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

    boolean isValid(Double maxDistance) {
        !cities.empty && getCost() <= maxDistance
    }

    void addCity(City city) {
        cities = cities.subList(0, cities.size() - 1) + [city] + cities.last()
    }

    public Double getCost() {
        getTotalCostInternal(cities)
    }

    public City getLastCity() {
        return cities.get(cities.size() - 2)
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
            if (cities.empty) {
                append """This route has no cities.\n"""
            } else {
                append """Route cost: ${this.cost}\n"""
                append """Route cities: ${this.cities.collect { city -> city.id }}"""
            }
            return it
        }.toString()

    }

    private void initializeCities() {
        if (depot && cities.empty) {
            cities = [depot, depot]
        }
    }
}


