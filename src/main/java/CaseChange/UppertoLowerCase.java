package CaseChange;

import org.testng.annotations.Test;

import java.util.Scanner; // import the Scanner class

public class UppertoLowerCase {
    @Test
    public void Out() {
        String help = "Technology is rapidly changing the way we work, communicate, and interact with the world around us.";
        Scanner scanner = new Scanner(help);
        String x= scanner.nextLine().toLowerCase();
        System.out.println("Result is: "+ x);
    }
}



