import javafx.geometry.Point2D
import model.City
import model.Route
import model.Solution
import utils.VRPFileReader
import utils.VRPFileWriter

VRPFileReader.readInputFiles().each {
    Solution solution = new Solution()

    City city = new City()
    city.with {
        coords = new Point2D(0,0)
        depot = false
        id = 1
        demand = 10
    }

    Route route = new Route(depot: city, cities: [city, city])

    route.addCity city
    solution.routes.add route

    VRPFileWriter.writeSolutionToFile(solution, "testfile.txt")
    //print it
}