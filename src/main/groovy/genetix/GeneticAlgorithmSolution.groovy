package genetix

import configuration.Config
import model.Solution
import utils.GeneticUtils

/**
 * Uncreated by stefangrecu on 11/05/16.
 */
class GeneticAlgorithmSolution {

    static List<Solution> initializePopulation() {
        return []
    }

    static List<Solution> parentSelection(List<Solution> population) {
        GeneticUtils.survivalOfTheFitest([])
        return []
    }

    static List<Solution> variation(List<Solution> parents) {
        GeneticUtils.cross([])
        GeneticUtils.mutate(null)
        return []
    }

    static List<Solution> selectOffspring(List<Solution> newGeneration) {
        GeneticUtils.survivalOfTheFitest([])
        return []
    }

    static Map generationMap() {
        def solutionMap = [:]
        Config.RESTARTS.each { restart ->
            restart.times {
                List<Solution> population = initializePopulation()
                Config.ITERATIONS.each { iteration ->
                    iteration.times {
                        List<Solution> parents = parentSelection(population)
                        List<Solution> offspring = variation(parents)
                        population = selectOffspring(offspring)
                    }
                    solutionMap.put([restart, iteration], population)
                }
            }
        }
        return solutionMap
    }

}
