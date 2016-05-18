package utils

import model.City
import model.Solution
import org.apache.commons.math3.random.RandomDataGenerator

/**
 * Uncreated by stefangrecu on 11/05/16.
 */
class GeneticUtils {

    public static Solution mutate(Solution chromosome) {
        if (chromosome.mutate()) {
            def cities = chromosome.cities
            Collections.shuffle(cities)
            return Do.Solution(chromosome, cities)
        } else return chromosome
    }

    public
    static List<Solution> cross(List<Solution> chromosomes, Integer randomIndexOne = null, Integer randomIndexTwo = null) {
        List<Solution> solutionList = []
        RandomDataGenerator random = new RandomDataGenerator()

        while (chromosomes.size() > 1) {
            Solution one = chromosomes.remove(0)
            Solution two = chromosomes.remove(0)

            def oneFlatten = one.routes.cities.flatten().unique().findAll {
                it != one.routes.cities.flatten().first()
            } as List<City>
            def twoFlatten = two.routes.cities.flatten().unique().findAll {
                it != two.routes.cities.flatten().first()
            } as List<City>

            int randomIndex2 = randomIndexTwo ?: random.nextInt(0, oneFlatten.size())
            int randomIndex1 = randomIndexOne ?: random.nextInt(0, randomIndex2)

            def oneMiddle = oneFlatten.subList(randomIndex1, randomIndex2)
            def twoMiddle = twoFlatten.subList(randomIndex1, randomIndex2)

            def capeteOne = (oneFlatten.subList(randomIndex2, oneFlatten.size()) + oneFlatten.subList(0, randomIndex1)).findAll {
                !oneMiddle.contains(it)
            }
            def capeteTwo = (twoFlatten.subList(randomIndex2, twoFlatten.size()) + twoFlatten.subList(0, randomIndex1)).findAll {
                !twoMiddle.contains(it)
            }

            def childOne = capeteOne.subList(capeteOne.size() - randomIndex1, capeteOne.size()) + oneMiddle + capeteOne.subList(0, randomIndex1)
            def childTwo = capeteTwo.subList(capeteTwo.size() - randomIndex1, capeteTwo.size()) + twoMiddle + capeteTwo.subList(0, randomIndex1)

            solutionList.add(Do.Solution(one, childOne.collect()))
            solutionList.add(Do.Solution(two, childTwo.collect()))
        }
        solutionList
    }

    public static List<Solution> survivalOfTheFitest(List<Solution> population) {
        population
                .sort { a, b -> b.totalCost <=> a.totalCost }
                .take(population.size() / 2 as int)
    }
}
