package genetix

import configuration.Config
import groovyx.gpars.GParsPool
import model.Solution
import solution.VRPSolver
import utils.GeneticUtils
import utils.VRPFileWriter

/**
 * Uncreated by stefangrecu on 11/05/16.
 */
class GeneticAlgorithmSolution {

    static Map<String, List<Solution>> initializePopulation() {
        return VRPSolver.generateSolutions()
    }

    static Map<String, List<Solution>> initializePopulationGreedy() {
        return VRPSolver.generateGreedySolutions()
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

    static void generationMap(Map<String, List<Solution>> pop = initializePopulation()) {
        GParsPool.withPool {
            Config.RESTARTS.eachParallel { restart ->
                List<Solution> previousPopulation = []
                restart.times {
                    List<Solution> bestAtThisRestart = []
                    GParsPool.withPool {
                        pop.eachParallel { Map.Entry<String, List<Solution>> population ->
                            List<Solution> newPopulation = previousPopulation + population.value + bestAtThisRestart
                            Config.ITERATIONS.each { iteration ->
                                iteration.times { genCount ->
                                    List<Solution> parents = parentSelection(newPopulation)
                                    parents.each { it.generationCount = genCount }
                                    List<Solution> offspring = variation(parents)
                                    newPopulation = offspring + parents
                                }
                                def name = """${population.key}-${restart}-${iteration}"""
                                println name
                                bestAtThisRestart = newPopulation.sort(true) { a, b -> a.totalCost <=> b.totalCost }
                                VRPFileWriter.writeSolutionToFile(name, newPopulation.sort { a, b -> a.totalCost <=> b.totalCost }.take(Config.POPULATION_SIZE))
                            }
                            previousPopulation += bestAtThisRestart
                            previousPopulation.sort(true) { a, b -> a.totalCost <=> b.totalCost }
                        }
                    }
                }
            }
        }
    }
}
