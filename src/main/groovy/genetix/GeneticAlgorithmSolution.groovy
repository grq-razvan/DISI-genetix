package genetix

import configuration.Config
import model.Solution
import solution.VRPSolver
import utils.GeneticUtils

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
                def previousPopulation = []
                restart.times {
                    GParsPool.withPool {
                        initializePopulation().eachParallel { population ->
                            def newPopulation = previousPopulation + population.value
                            Config.ITERATIONS.each { iteration ->
                                iteration.times { genCount ->
                                    List<Solution> parents = parentSelection(newPopulation)
                                    parents.each { it.generationCount = genCount }
                                    List<Solution> offspring = variation(parents)
                                    newPopulation = offspring + parents
                                }
                                def name = """${population.key}-${restart}-${iteration}"""
                                println name
                                previousPopulation = newPopulation.sort(true) { a, b -> a.totalCost <=> b.totalCost }
                                VRPFileWriter.writeSolutionToFile(name, newPopulation.take(Config.POPULATION_SIZE))
                            }
                        }
                    }
                }
            }
        }
    }
}
