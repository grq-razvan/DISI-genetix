package model

import genetix.Chromosome

/**
 * Uncreated by stefangrecu on 10/05/16.
 */
class Solution implements Chromosome {
    List<Route> routes = []

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
        return [routes.first().cities.first()] + routes.cities.flatten().unique()
    }


    @Override
    public String toString() {
        new StringBuilder().with {
            routes.eachWithIndex { route, index ->
                append """Route ${index} \n"""
                append route.toString()
            }
            return it
        }.toString()
    }
}
