package model

/**
 * Created by sergiufalcusan on 10/05/16.
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
}
