package org.example.classwork.day13;

public class Runner {
    public static void main(String[] args) {
        customAssert(-1, -1, 48, "Case 1: 1st branch + Cond1 true + Cond2 ture + " +
                "Cond3 true + Cond4 true");
        customAssert(-40, 11, 20, "Case 2: 2st branch + Cond1 false + Cond2 n/a + " +
                "Cond3 fasle + Cond4 false");
        customAssert(-15, 11, 27, "Case 3: 2st branch + Cond1 false + Cond2 false + " +
                "Cond3 fasle + Cond4 true");
    }

    public static void customAssert(int inputX, int inputY, int expectedResult, String description){
        int result = new ClassWithMethodToTest().getNumber(inputX, inputY);
        if(expectedResult == result){
            System.out.println(
                    description +
                    "\nStatus: Passed " +
                    "\nExpected result was " + expectedResult +
                    "\nInputX was " + inputX +
                    "\nInputY was " + inputY);
        } else {
            System.out.println(
                    description +
                            "\nStatus: Failed " +
                            "\nExpected result was " + expectedResult +
                            "\nBut was " + result +
                            "\nInputX was " + inputX +
                            "\nInputY was " + inputY);
        }
        System.out.println("-------------------------------------------------");
    }
}
