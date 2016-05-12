package solution

import configuration.Config
import model.City
import model.Route
import model.Solution
import utils.VRPFileReader

/**
 * Uncreated by stefangrecu on 12/05/16.
 */
class VRPSolver {

    def static generateSolutions() {
        def listListSolutions = []
        VRPFileReader.readInputFiles().each { data ->
            City depot = data.cities.find { city -> city.depot }
            data.cities.remove(depot)
            List<Solution> population = []
            Config.POPULATION_SIZE.times {
                Solution solution = new Solution()
                Collections.shuffle(data.cities)
                List<City> tempCities = data.cities.collect()
                Iterator<City> currentCity = tempCities.iterator()
                while (!tempCities.empty) {
                    Route route = new Route(depot)
                    while (route.isValid(data.distance.intValue()))
                        if (currentCity.hasNext()) {
                            route.addCity(currentCity.next())
                        }
                    solution.addRoute(route)
                }
                population.add(solution)
            }
            listListSolutions += population
        }
    }
}
