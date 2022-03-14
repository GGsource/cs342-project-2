import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class KenoTest {
    //These 4 methods are the only testable methods
    //in my Keno Game
    private static int ndx;

    @BeforeEach
    void init() {
        ndx = 0;
    }

    //Test seedRandomList
    @Test
    void seedRandomListFirstTest() {
        ArrayList<Integer> firstList = Project2Keno.seedRandomList();
        //Assert list was filled with 20 numbers
        assertEquals(20, firstList.size());
    }
    @Test
    void seedRandomListSecondTest() {
        ArrayList<Integer> secondList = Project2Keno.seedRandomList();
        ArrayList<Integer> thirdList = Project2Keno.seedRandomList();
        //Assert 2 instances of the random list are not identical
        assertFalse(Arrays.equals(secondList.toArray(), thirdList.toArray()));
    }
    @Test
    void seedRandomListThirdTest() {
        ArrayList<Integer> fourthList = Project2Keno.seedRandomList();
        //Assert there is only 1 instance of each number in list
        for (int num : fourthList) {
            int identicalNumCount = 0;
            for (int num2 : fourthList) {
                if (num == num2)
                    identicalNumCount += 1;
            }
            //Each pass through only 1 count should be found
            assertTrue(identicalNumCount == 1);
        }
    }
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80})
    void seedRandomListFourthTest(int possibility) {
        ArrayList<Integer> fifthList = Project2Keno.seedRandomList();
        int numCounter = 0;
        while (fifthList.contains(possibility)) {
            fifthList.remove((Object)possibility);
            numCounter += 1;
        }
        assertTrue(numCounter <= 1); //No repeats
    }
    @RepeatedTest(20)
    void seedRandomListFifthTest() {
        ArrayList<Integer> sixthList = Project2Keno.seedRandomList();
        assertTrue(sixthList.get(ndx) > 0); //Assert all numbers are 1 or more
    }
    @RepeatedTest(20)
    void seedRandomListSixthTest() {
        ArrayList<Integer> seventhList = Project2Keno.seedRandomList();
        assertTrue(seventhList.get(ndx) <= 80); //Assert all numbers are 80 or less
    }

    //Test calculateRoundWinnings
    @ParameterizedTest
    @ValueSource(ints = {1, 4, 8, 10}) //Possible spot counts
    void calculateRoundWinningsTest(int spotCount) {
        int matchCount = spotCount;
        while (matchCount >= 1) {
            int cash = Project2Keno.calculateRoundWinnings(matchCount, spotCount);
            if (spotCount == 10) {
                if (matchCount == 10)
                    assertEquals(cash, 100000);
                else if (matchCount == 9)
                    assertEquals(cash, 4250);
                else if (matchCount == 8)
                    assertEquals(cash, 450);
                else if (matchCount == 7)
                    assertEquals(cash, 40);
                else if (matchCount == 6)
                    assertEquals(cash, 15);
                else if (matchCount == 5)
                    assertEquals(cash, 2);
                else if (matchCount == 0)
                    assertEquals(cash, 5);
                else
                    assertEquals(cash, 0);
            }
            else if (spotCount == 8) {
                if (matchCount == 8)
                    assertEquals(cash, 10000);
                if (matchCount == 7)
                    assertEquals(cash, 750);
                if (matchCount == 6)
                    assertEquals(cash, 50);
                if (matchCount == 5)
                    assertEquals(cash, 12);
                if (matchCount == 4)
                    assertEquals(cash, 2);
            }
            else if (spotCount == 4) {
                if (matchCount == 4)
                    assertEquals(cash, 75);
                if (matchCount == 3)
                    assertEquals(cash, 5);
                if (matchCount == 2)
                    assertEquals(cash, 1);
            }
            else if (spotCount == 1) {
                if (matchCount == 1)
                    assertEquals(cash, 2);
            }
            matchCount -= 1;
        }

    }
    //Test checkIfReadyToBegin
}
