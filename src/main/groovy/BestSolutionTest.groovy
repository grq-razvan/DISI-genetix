import model.Route
import model.Solution
import utils.VRPFileReader

VRPFileReader.readInputFiles().each {
    def cities = it.cities

    Solution solution = new Solution()

    InputStream resource = Thread.currentThread().getContextClassLoader().getResourceAsStream("sol")
    resource.readLines().each { line ->
        def routes = line.split("-")
        routes.each {
            Route route = new Route(cities.get(0))
            it.split(" ").each {
                route.addCity(cities.get(it.toInteger()))
            }
            solution.addRoute(route)
        }
    }

    println solution
}