package utils

import configuration.Config
import javafx.geometry.Point2D
import model.City
import model.VRPData

/**
 *  Uncreated by sergiufalcusan on 10/05/16.
 */
class VRPFileReader {

    private static final String NAME_HEADER = "NAME"
    private static final String BEST_KNOWN_HEADER = "BEST"
    private static final String COMMENT_HEADER = "COMM"
    private static final String DIMENSION_HEADER = "DIME"
    private static final String CAPACITY_HEADER = "CAPA"
    private static final String DISTANCE_HEADER = "DIST"
    private static final String EDGE_WEIGHT_HEADER = "FORM"
    private static final String EDGE_TYPE_HEADER = "TYPE"
    private static final String COORD_SECTION_HEADER = "COOR"
    private static final String DEMAND_SECTION_HEADER = "DEMA"
    private static final String DEPOT_SECTION_HEADER = "DEPO"
    private static final String EOF_HEADER = "EOF"
    private static final Integer ID_INDEX = 0
    private static final Integer VALUE_INDEX = 1
    private static final Integer X_COORD_INDEX = 1
    private static final Integer Y_COORD_INDEX = 2

    public static List<VRPData> readInputFiles() {
        List<VRPData> data = []
        Config.FLK_INPUT.parallelStream().forEachOrdered { filename ->
            boolean inCoordinatesSection = false;
            boolean inDemandSection = false;
            boolean inDepotSection = false;
            InputStream resource = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename)
            VRPData vrpData = new VRPData()
            resource.readLines().each { String entry ->
                def entryData = entry.split(" ")

                if (entry.contains(NAME_HEADER)) {
                    vrpData.name = entryData[VALUE_INDEX]
                } else if (entry.contains(BEST_KNOWN_HEADER)) {
                    vrpData.bestKnown = entryData[VALUE_INDEX].toDouble()
                } else if (entry.contains(COMMENT_HEADER)) {
                    vrpData.comment = entryData[VALUE_INDEX].toDouble()
                } else if (entry.contains(DIMENSION_HEADER)) {
                    vrpData.dimension = entryData[VALUE_INDEX].toDouble()
                } else if (entry.contains(CAPACITY_HEADER)) {
                    vrpData.capacity = entryData[VALUE_INDEX].toDouble()
                } else if (entry.contains(DISTANCE_HEADER)) {
                    vrpData.distance = entryData[VALUE_INDEX].toDouble()
                } else if (entry.contains(EDGE_WEIGHT_HEADER)) {
                    vrpData.edgeWeightFormat = entryData[VALUE_INDEX]
                } else if (entry.contains(EDGE_TYPE_HEADER)) {
                    vrpData.edgeWeightType = entryData[VALUE_INDEX]
                } else if (entry.contains(COORD_SECTION_HEADER)) {
                    inDemandSection = false;
                    inCoordinatesSection = true;
                    inDepotSection = false;
                } else if (entry.contains(DEMAND_SECTION_HEADER)) {
                    inDemandSection = true;
                    inCoordinatesSection = false;
                    inDepotSection = false;
                } else if (entry.equals(EOF_HEADER) || entry.equals("-1")) {
                    inDemandSection = false;
                    inCoordinatesSection = false;
                    inDepotSection = false;
                } else if (entry.contains(DEPOT_SECTION_HEADER)) {
                    inDemandSection = false;
                    inCoordinatesSection = false;
                    inDepotSection = true;
                }

                if (!(entry.contains(COORD_SECTION_HEADER)) && inCoordinatesSection) {
                    vrpData.cities.add(new City(
                            id: entryData[ID_INDEX].toInteger(),
                            coords: new Point2D(entryData[X_COORD_INDEX].toDouble(), entryData[Y_COORD_INDEX].toDouble())))
                }

                if (!(entry.contains(DEMAND_SECTION_HEADER)) && inDemandSection) {
                    vrpData.cities.find {
                        it.id == entryData[ID_INDEX].toInteger()
                    }.demand = entryData[VALUE_INDEX].toInteger()
                }

                if (!(entry.contains(DEPOT_SECTION_HEADER)) && inDepotSection) {
                    vrpData.cities.find { it.id == entryData[ID_INDEX].toInteger() }.depot = true
                }
            }
            data.add(vrpData)
        }
        return data
    }
}
