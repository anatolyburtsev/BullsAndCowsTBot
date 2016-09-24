package BullsAndCows;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by onotole on 9/22/16.
 */
@RunWith(Parameterized.class)
public class BullsCowsTest {

    @Parameterized.Parameter(0)
    public int pattern;

    @Parameterized.Parameter(1)
    public int sample;

    @Parameterized.Parameter(2)
    public int expectedBulls;

    @Parameterized.Parameter(3)
    public int expectedCows;

    @Parameterized.Parameters(name = "{0}:{1} = {2},{3}")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                {0, 1456, 0, 0},
                {1098, 1234, 1, 0},
                {1234, 4281, 1, 2},
                {1234, 2341, 0, 4},
                {9876, 9876, 4, 0}
        });
    }

    @org.junit.Test
    public void countBullsAndCows() throws Exception {
        assertThat(BullsCows.countBullsAndCows(pattern, sample), equalTo(new int[] {expectedBulls, expectedCows}));
    }

}