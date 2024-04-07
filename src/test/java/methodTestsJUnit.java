import org.example.classwork.day13.ClassWithMethodToTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class methodTestsJUnit {
    @Test
    public void caseOneFirstBranch(){
        System.out.println("Case 1: 1st branch + Cond1 true + Cond2 ture + " +
                "Cond3 true + Cond4 true");
        assertEquals(48,new ClassWithMethodToTest().getNumber(-1, -1));
    }

    @Test
    public void caseTwoSecondtBranch(){
        System.out.println("Case 2: 2st branch + Cond1 false + Cond2 n/a + " +
                "Cond3 fasle + Cond4 false");
        assertEquals(20,new ClassWithMethodToTest().getNumber(-40, 11));
    }

    @Test
    public void caseThreeSecondBranch(){
        System.out.println("Case 3: 2st branch + Cond1 false + Cond2 false + " +
                "Cond3 fasle + Cond4 true");
        assertEquals(27,new ClassWithMethodToTest().getNumber(-15, 11));
    }
}
