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

        def childs = GeneticUtils.cross(solutions, 2, 3)

        print "CHILD ONE: "
        childs[0].cities.each {
            print it.id + " "
        }
        println()

        print "CHILD TWO: "
        childs[1].cities.each {
            print it.id + " "
        }
        println()
    }
}
