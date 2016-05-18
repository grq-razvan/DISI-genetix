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
        GeneticUtils.survivalOfTheFitest(population.collect())
    }

    static List<Solution> variation(List<Solution> parents) {
        def newParents = GeneticUtils.cross(parents.collect())
        newParents.collect {
            GeneticUtils.mutate(it)
        }
    }

    static Map generationMap() {
        def solutionMap = [:]
        Config.RESTARTS.each { restart ->
            restart.times {
                initializePopulation().eachWithIndex { population, fileIndex ->
                    def newPopulation = population.value
                    Config.ITERATIONS.each { iteration ->
                        iteration.times { genCount ->
                            List<Solution> parents = parentSelection(newPopulation)
                            parents.each { it.generationCount = genCount }
                            List<Solution> offspring = variation(parents)
                            newPopulation = offspring + parents
                        }
                    }
                    solutionMap.put([restart, fileIndex], newPopulation.sort { a, b -> a.totalCost <=> b.totalCost })
                }
            }
        }
        return solutionMap
    }

}
