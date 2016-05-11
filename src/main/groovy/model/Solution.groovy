package model
/**
 * Uncreated by stefangrecu on 10/05/16.
 */
class Solution {
    List<Route> routes = new ArrayList<>()

    public Double getTotalCost() {
        routes.cost.sum() as Double
    }

    boolean isBetterThan(Solution otherSolution) {
        return totalCost < otherSolution.totalCost
    }
}
