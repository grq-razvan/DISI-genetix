package model

import org.junit.Before
import org.junit.Test
import utils.GeneticUtils
import utils.SolutionFactory

class GeneticUtilsTest {
    SolutionFactory solutionFactory = new SolutionFactory();

    Solution solutionOne;
    Solution solutionTwo;

    @Before
    void before() {
        this.solutionOne = solutionFactory.createSolutionOne()
        this.solutionTwo = solutionFactory.createSolutionTwo()
    }

    @Test
    void crossTest() {
        List<Solution> solutions = []
        solutions.add(solutionOne)
        solutions.add(solutionTwo)

        print "SOLUTION ONE: "
        solutionOne.cities.each {
            print it.id + " "
        }
        println()

        print "SOLUTION TWO: "
        solutionTwo.cities.each {
            print it.id + " "
        }
        println()

        def children = GeneticUtils.cross(solutions.collect(), 1, 3)

        print children[0].cities
        println()
        print children[1].cities
        println()

        def otherChildren = GeneticUtils.cross(solutions.collect(), 1, 4)
        print otherChildren[0].cities
        println()
        print otherChildren[1].cities
        println()

        def lastChildren = GeneticUtils.cross(solutions.collect(), 3, 4)
        print lastChildren[0].cities
        println()
        print lastChildren[1].cities
        println()
    }
}
