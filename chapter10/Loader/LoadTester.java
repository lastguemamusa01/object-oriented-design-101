package chapter10.Loader;

import java.io.*;
import chapter10.Subway.Subway;

public class LoadTester {

    public static void main(String[] args) {
        try {
            SubwayLoader loader = new SubwayLoader();
            Subway objectville = loader.loadFromFile(new File("ObjectvilleSubway.txt"));
            System.out.println("Testing stations...");

            if(objectville.hasStation("Ajax Rapids") && objectville.hasStation("HTML Heights") && objectville.hasStation("JavaBeans Boulevard")) {
                System.out.println("...station test passed successfully.");
            } else {
                System.out.println("...station test FAILED.");
                System.exit(-1);
            }

            System.out.println("\nTesting connections...");
            if(objectville.hasConnection("Ajax Rapids", "HTML Heights", "Booch Line") 
            && objectville.hasConnection("HTML Heights", "JavaBeans Boulevard", "Booch Line")) {
                System.out.println("...connections test passed successfully.");
            } else {
                System.out.println("...connections test FAILED");
                System.exit(-1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } 
    }
}
