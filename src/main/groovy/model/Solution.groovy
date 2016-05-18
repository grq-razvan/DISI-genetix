package model

import genetix.Chromosome

/**
 * Uncreated by stefangrecu on 10/05/16.
 */
class Solution implements Chromosome {
    List<Route> routes = []
    Integer maxDistance
    Integer maxCapacity
    City depot

    public void addRoute(Route route) {
        routes.add(route)
    }

    public Double getTotalCost() {
        routes.cost.sum() as Double
    }

    public List<City> getCities() {
        return routes.cities.flatten().unique() as List<City>
    }

    public String getLabel() {
        new StringBuilder().with {
            append "SOLUTION" append '\n'
            append "TOTAL COST: ${totalCost}" append '\n'
            append "MAX CAPACITY: ${maxCapacity}" append '\n'
            append "MAX DISTANCE: ${maxDistance}" append '\n'
            routes.eachWithIndex { route, index ->
                append "ROUTE ${index}" append '\n'
                append route.label
            }
            it
        }.toString()
    }

    @Override
    public String toString() {
        new StringBuilder().with {
            append "Cost: ${totalCost}"
            append """Cities: [${cities.id.join(", ")}]"""
            it
        }.toString()
    }
}
