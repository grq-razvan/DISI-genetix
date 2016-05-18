package utils

import model.City
import model.Route
import model.Solution

/**
 * Created by sergiufalcusan on 18/05/16.
 */
class SolutionFactory {
    CityFactory cityFactory = new CityFactory()
    Random random = new Random();

    Integer maxDistance = 650
    Integer maxCapacity = 550

    Solution createSolutionOne() {
        City cityOne = CityFactory.create(1, 0.00, 0.00, 0, false)
        City cityTwo = CityFactory.create(2, 30.00, 0.00, 0, false)
        City cityThree = CityFactory.create(3, 29.63060, 4.69300, 0, false)
        City cityFour = CityFactory.create(4, 28.53170, 9.27050, 0, false)
        City cityFive = CityFactory.create(5, 26.73020, 13.61970, 0, false)

        Route routeOne = new Route(cityOne)
        routeOne.addCity(cityTwo)
        routeOne.addCity(cityThree)

        Route routeTwo = new Route(cityOne)
        routeTwo.addCity(cityFour)

        Route routeThree = new Route(cityOne)
        routeThree.addCity(cityFive)

        Solution solution = new Solution()

        solution.maxCapacity = maxCapacity
        solution.maxDistance = maxDistance
        solution.depot = cityOne

        solution.addRoute(routeOne)
        solution.addRoute(routeTwo)
        solution.addRoute(routeThree)

        solution
    }

    Solution createSolutionTwo() {
        City cityOne = CityFactory.create(1, 0.00, 0.00, 0, false)
        City cityTwo = CityFactory.create(2, 30.00, 0.00, 0, false)
        City cityThree = CityFactory.create(3, 29.63060, 4.69300, 0, false)
        City cityFour = CityFactory.create(4, 28.53170, 9.27050, 0, false)
        City cityFive = CityFactory.create(5, 26.73020, 13.61970, 0, false)

        Route routeOne = new Route(cityOne)
        routeOne.addCity(cityFive)
        routeOne.addCity(cityFour)

        Route routeTwo = new Route(cityOne)
        routeTwo.addCity(cityTwo)
        routeTwo.addCity(cityThree)

        Solution solution = new Solution()

        solution.maxCapacity = maxCapacity
        solution.maxDistance = maxDistance
        solution.depot = cityOne

        solution.addRoute(routeOne)
        solution.addRoute(routeTwo)

        solution
    }
}
