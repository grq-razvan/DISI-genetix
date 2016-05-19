package generator

import model.City
import model.Route
import model.Solution
import model.VRPData
import org.apache.commons.math3.random.RandomDataGenerator

/**
 * Uncreated by stefangrecu on 18/05/16.
 */
class GreedySolutionGenerator {

    static RandomDataGenerator randomDataGenerator = new RandomDataGenerator()

    public static Solution nextSolution(VRPData data, int startingPosition) {
        List<City> cities = data.cities.collect()
        City depot = cities.remove(0)
        Solution solution = new Solution().with { sol ->
            sol.maxDistance = data.distance
            sol.maxCapacity = data.capacity
            sol.depot = depot
            sol
        }

        while (!cities.empty) {
            Route route = new Route(depot)
            if (!solution.routes.any { it.firstCity == cities.get(startingPosition) }) {
                route.addCity(cities.get(startingPosition))
            }
            while (route.isValid(solution.maxDistance, solution.maxCapacity) && !cities.empty) {
                List<City> candidates = cities.sort { a, b ->
                    b.demand <=> a.demand
                }
                City currentCity = candidates.min { it.distanceTo(route.lastCity) }
                candidates.remove(currentCity)
                Route temp = new Route(route)
                temp.addCity(currentCity)

                if (temp.isValid(solution.maxDistance, solution.maxCapacity)) {
                    route.addCity(currentCity)
                    cities.remove(currentCity)
                } else {
                    if (!candidates.empty) {
                        City tempCity = candidates.first()
                        Route newTemp = new Route(route)
                        newTemp.addCity(tempCity)
                        if (newTemp.isValid(solution.maxDistance, solution.maxCapacity)) {
                            route.addCity(tempCity)
                            candidates.remove(tempCity)
                        }
                    }
                }
                solution.addRoute(route)
            }
        }
        return solution
    }

    public static List<Solution> nextBatchSolutions(VRPData data, int resultLength) {
        (0..<resultLength).collect {nextSolution(data, randomDataGenerator.nextInt(1, data.dimension.intValue()-2))}
    }

}
