package generator

import model.City
import model.Solution
import model.VRPData

/**
 * Uncreated by stefangrecu on 18/05/16.
 */
class GreedySolutionGenerator {

    public static Solution nextSolution(VRPData data, int startingPosition) {
        List<City> cities = data.cities.collect()
        City depot = cities.remove(0)
        Solution solution = new Solution().with { sol ->
            sol.maxDistance = data.distance
            sol.maxCapacity = data.capacity
            sol.depot = depot
            sol
        }


    }

}
