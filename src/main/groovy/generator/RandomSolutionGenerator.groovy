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
        List<City> cities = data.cities.collect()
        City depot = cities.remove(0)
        Collections.shuffle(cities)
        RandomDataGenerator random = new RandomDataGenerator()

        while (!cities.empty) {
            Route route = new Route(depot)
            while (route.isValid(data.distance, data.capacity) && !cities.empty) {
                City currentCity = cities.get(0)

                Route msodfaa = new Route(route)
                msodfaa.addCity(currentCity)

                if (msodfaa.isValid(data.distance, data.capacity)) {
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
        resultLength.collect { nextSolution(data) }
    }

}
