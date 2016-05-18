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

    boolean isBetterThan(Solution otherSolution) {
        return totalCost < otherSolution.totalCost
    }

    public List<City> getCities() {
        return routes.cities.flatten().unique() as List<City>
    }


    @Override
    public String toString() {
        new StringBuilder().with {
            append """SOLUTION \n"""
            append """TOTAL COST ${totalCost} \n"""
            routes.eachWithIndex { route, index ->
                append """\n Route ${index} \n"""
                append route.toString()
            }
            return it
        }.toString()
    }
}
