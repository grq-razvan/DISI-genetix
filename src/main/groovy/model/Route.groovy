package model

/**
 * Uncreated by stefangrecu on 10/05/16.
 */
class Route {
    List<City> cities
    Double cost
    Integer remainingCapacity

    boolean isValid(Integer maxCapacity, Integer maxDistance) {
        remainingCapacity < maxCapacity && remainingCapacity >= 0 && cost <= maxDistance
    }

}
