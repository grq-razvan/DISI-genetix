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

    static List<Solution> parentSelection(List<Solution> population) {
        GeneticUtils.survivalOfTheFitest(population.collect())
    }

    static List<Solution> variation(List<Solution> parents) {
        def newParents = GeneticUtils.cross(parents.collect())
        newParents.collect {
            GeneticUtils.mutate(it)
        }
    }

    static void generationMap() {
        GParsPool.withPool {
            Config.RESTARTS.eachParallel { restart ->
                restart.times {
                    initializePopulation().each { population ->
                        def newPopulation = population.value
                        Config.ITERATIONS.each { iteration ->
                            iteration.times { genCount ->
                                List<Solution> parents = parentSelection(newPopulation)
                                parents.each { it.generationCount = genCount }
                                List<Solution> offspring = variation(parents)
                                newPopulation = offspring + parents
                            }
                            println """${population.key}-${restart}-${iteration}"""
                            VRPFileWriter.writeSolutionToFile("""${population.key}-${restart}-${
                                iteration
                            }""", newPopulation.sort { a, b -> a.totalCost <=> b.totalCost })
                        }
                    }
                }
            }
        }
    }

}
