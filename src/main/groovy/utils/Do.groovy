package utils

import model.City
import model.Route
import model.Solution

/**
 * Created by sergiufalcusan on 18/05/16.
 */
class Do {
    public static Solution Solution(Solution oldSol, List<City> cities) {
        def solution = new Solution()

        while (!cities.empty) {
            Route route = new Route(oldSol.depot)
            while (route.isValid(oldSol.maxDistance, oldSol.maxCapacity) && !cities.empty) {
                City currentCity = cities.get(0)

                Route msodfaa = new Route(route)
                msodfaa.addCity(currentCity)

                if (msodfaa.isValid(oldSol.maxDistance, oldSol.maxCapacity)) {
                    route.addCity(currentCity)
                    cities.remove(0)
                } else {
                    break
                }
            }
            solution.addRoute(route)
        }
        solution
    }
}
