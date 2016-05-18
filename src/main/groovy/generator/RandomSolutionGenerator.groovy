package generator

import model.City
import model.Route
import model.Solution
import model.VRPData

/**
 * Uncreated by stefangrecu on 15/05/16.
 */
class RandomSolutionGenerator {

    public static Solution nextSolution(VRPData data) {
        List<City> cities = data.cities.collect()
        City depot = cities.remove(0)
        Collections.shuffle(cities)

        Solution solution = new Solution().with { sol ->
            sol.maxDistance = data.distance
            sol.maxCapacity = data.capacity
            sol.depot = depot
            sol
        }

        while (!cities.empty) {
            Route route = new Route(depot)
            while (route.isValid(solution.maxDistance, solution.maxCapacity) && !cities.empty) {
                City currentCity = cities.first()

                Route msodfaa = new Route(route)
                msodfaa.addCity(currentCity)

                if (msodfaa.isValid(solution.maxDistance, solution.maxCapacity)) {
                    route.addCity(currentCity)
                    cities.remove(0)
                } else {
                    break
                }
            }
            solution.addRoute(route)
        }

        return solution
    }

    public static List<Solution> nextBatchSolutions(VRPData data, int resultLength) {
        (0..<resultLength).collect { nextSolution(data) }
    }

}
