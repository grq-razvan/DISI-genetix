package model

/**
 *  Uncreated by sergiufalcusan on 10/05/16.
 */
class VRPData {
    String name
    Double bestKnown
    Double comment
    Double dimension
    Double capacity
    Double distance
    String edgeWeightFormat
    String edgeWeightType
    List<City> cities = new ArrayList<>()


    @Override
    public String toString() {
        """NAME: ${name}
           BEST KNOWN: ${bestKnown}
           COMMENT: ${comment}
           DIMENSION: ${dimension}
           CAPACITY: ${capacity}
           DISTANCE: ${distance}
           EDGE WEIGHT FORMAT: ${edgeWeightFormat}
           EDGE WEIGHT TYPE: ${edgeWeightType}
           CITIES: ${cities.collect { it.toString() }}"""
    }
}

