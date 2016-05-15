package generator

import model.City
import model.Route
import model.Solution
import model.VRPData
import org.apache.commons.math3.random.RandomDataGenerator

/**
 * Uncreated by stefangrecu on 15/05/16.
 */
class RandomSolutionGenerator {

    public static Solution nextSolution(VRPData data) {
        Solution solution = new Solution()
        List<City> cities = data.collect()
        Collections.shuffle(cities)
        RandomDataGenerator random = new RandomDataGenerator()
        while (!cities.empty) {
            Route route = new Route()
            while (route.isValid(data.capacity)) {
                int randomCityIndex = cities.size() - 1 != 0 ? random.nextInt(0, cities.size() - 1) : 0
                City currentCity = cities.get(randomCityIndex)
                if (route.cost + route.lastCity.distanceTo(currentCity) <= data.distance) {
                    route.addCity(currentCity)
                }
            }
            solution.addRoute(route)
        }

        return solution
    }

    public static List<Solution> nextBatchSolutions(VRPData data, int resultLength) {
        resultLength.collect { nextSolution(data) }
    }

}
