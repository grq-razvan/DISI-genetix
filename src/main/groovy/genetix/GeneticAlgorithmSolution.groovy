package genetix

import configuration.Config
import model.Solution
import solution.VRPSolver
import utils.GeneticUtils

/**
 * Uncreated by stefangrecu on 11/05/16.
 */
class GeneticAlgorithmSolution {

    static List<Solution> initializePopulation(int i) {
        return VRPSolver.generateSolutions().get(i)
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

    static Map generationMap(int i) {
        def solutionMap = [:]
        Config.RESTARTS.each { restart ->
            restart.times {
                List<Solution> population = initializePopulation(i)
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
