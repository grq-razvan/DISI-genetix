package genetix

import configuration.Config
import model.Solution
import solution.VRPSolver
import utils.GeneticUtils

/**
 * Uncreated by stefangrecu on 11/05/16.
 */
class GeneticAlgorithmSolution {

    static Map<Integer, List<Solution>> initializePopulation() {
        return VRPSolver.generateSolutions()
    }

    static List<Solution> parentSelection(List<Solution> population) {
        GeneticUtils.survivalOfTheFitest(population)
    }

    static List<Solution> variation(List<Solution> parents) {
        GeneticUtils.cross(parents)
        parents.each {
            GeneticUtils.mutate(it)
        }
        return parents.collect()
    }

    static List<Solution> selectOffspring(List<Solution> newGeneration) {
        GeneticUtils.survivalOfTheFitest(newGeneration)
    }

    static Map generationMap() {
        def solutionMap = [:]
        Config.RESTARTS.each { restart ->
            restart.times {
                initializePopulation().eachWithIndex { population, fileIndex ->
                    Config.ITERATIONS.each { iteration ->
                        iteration.times {
                            List<Solution> parents = parentSelection(population.value)
                            List<Solution> offspring = variation(parents)
                            population = selectOffspring(offspring)
                        }
                    }
                    solutionMap.put([restart, fileIndex], population)
                }
            }
        }
        return solutionMap
    }

}
