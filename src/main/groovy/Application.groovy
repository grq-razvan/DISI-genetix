import configuration.*
import javafx.geometry.Point2D
import model.City
import model.VRPData
import org.apache.commons.io.FileUtils

Config.inputPaths.parallelStream().forEach { filename ->
    boolean COORD_SECTION = false;
    boolean DEMAND_SECTION = false;
    boolean DEPOT_SECTION = false;

    def resource = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename)
    VRPData vrpData = new VRPData()

    resource.readLines().each { String entry ->
        def entryData = entry.split(" ")

        if (entry.contains("NAME")) {
            vrpData.name = entryData[1]
        } else if (entry.contains("BEST")) {
            vrpData.bestKnown = entryData[1].toDouble()
        } else if (entry.contains("COMM")) {
            vrpData.comment = entryData[1].toDouble()
        } else if (entry.contains("DIME")) {
            vrpData.dimension = entryData[1].toDouble()
        } else if (entry.contains("CAPA")) {
            vrpData.capacity = entryData[1].toDouble()
        } else if (entry.contains("DIST")) {
            vrpData.distance = entryData[1].toDouble()
        } else if (entry.contains("FORM")) {
            vrpData.edgeWeightFormat = entryData[1]
        } else if (entry.contains("TYPE")) {
            vrpData.edgeWeightType = entryData[1]
        } else if (entry.contains("COOR")) {
            DEMAND_SECTION = false;
            COORD_SECTION = true;
            DEPOT_SECTION = false;
        } else if (entry.contains("DEMA")) {
            DEMAND_SECTION = true;
            COORD_SECTION = false;
            DEPOT_SECTION = false;
        } else if (entry.equals("EOF") || entry.equals("-1")) {
            DEMAND_SECTION = false;
            COORD_SECTION = false;
            DEPOT_SECTION = false;
        } else if (entry.contains("DEPO")) {
            DEMAND_SECTION = false;
            COORD_SECTION = false;
            DEPOT_SECTION = true;
        }

        if (!(entry.contains("COOR")) && COORD_SECTION) {
            City city = new City().with{
                id = entryData[0].toInteger()
                coord = new Point2D(entryData[1].toDouble(), entryData[2].toDouble())
                it
            }

            vrpData.cities.add(city)
        }

        if (!(entry.contains("DEMA")) && DEMAND_SECTION) {
            vrpData.cities.find { it.id == entryData[0].toInteger() } .demand = entryData[1].toInteger()
        }

        if (!(entry.contains("DEPO")) && DEPOT_SECTION) {
            vrpData.cities.find { it.id == entryData[0].toInteger() } .depot = true
        }
    }

    println VRPData
}
