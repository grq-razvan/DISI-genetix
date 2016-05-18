import genetix.GeneticAlgorithmSolution
import utils.VRPFileWriter

/* VRPFileReader.readInputFiles().each {
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
} */
//
//Config.RESTARTS.each { i ->
//    Map<Integer, List<Solution>> solutionsMap = GeneticAlgorithmSolution.initializePopulation()
//
//    solutionsMap.each { k, v ->
//        println i
//        println k
//        println v
//    }
//}
def x = GeneticAlgorithmSolution.generationMap()
VRPFileWriter.writeSolutionToFile(x)