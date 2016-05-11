package genetix

import configuration.Config
import model.Solution

/**
 * Uncreated by stefangrecu on 11/05/16.
 */
class GeneticAlgorithmSolution {

    List<Solution> initializePopulation() {
        return []
    }

    List<Solution> parentSelection(List<Solution> population) {
        return []
    }

    List<Solution> variation(List<Solution> parents) {
        return []
    }

    List<Solution> selectOffspring(List<Solution> newGeneration) {
        return []
    }

    Map solve() {
        def solutionMap = [:]
        Config.restarts.each { restart ->
            restart.times {
                List<Solution> population = initializePopulation()
                Config.iterations.each { iteration ->
                    iteration.times {
                        List<Solution> parents = parentSelection(population)
                        List<Solution> offspring = variation(parents)
                        population = selectOffspring(offspring)
                    }
                    solutionMap << [[restart, iteration]: population]
                }
            }
        }
        return solutionMap
    }

}
