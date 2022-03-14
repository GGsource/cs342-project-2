import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class KenoTest {
    //These 4 methods are the only testable methods
    //in my Keno Game

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
    void seedRandomListParamTest() {
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
    //Test clearDrawingBoard
    //Test calculateRoundWinnings
    //Test checkIfReadyToBegin
}
