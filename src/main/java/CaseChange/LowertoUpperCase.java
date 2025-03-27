package CaseChange;

import org.testng.annotations.Test;

import java.util.Scanner;

public class LowertoUpperCase {
    @Test
    public void Out(){
        String IP = "Finding a balance between innovation and responsibility is crucial for a sustainable future.";
        Scanner scanner = new Scanner(IP);
        String Out = scanner.nextLine().toUpperCase();
        System.out.println("Result: " + Out);
    }
}
