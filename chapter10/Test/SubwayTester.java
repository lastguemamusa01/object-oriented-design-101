package chapter10.Test;

import java.io.File;
import java.util.*;

import chapter10.Loader.SubwayLoader;
import chapter10.Printer.SubwayPrinter;
import chapter10.Subway.Connection;
import chapter10.Subway.Subway;

public class SubwayTester {
    public static void main(String[] args) {
        String startStation = "Ajax Rapids";
        String endStation = "JavaBeans Boulevard";
        
        // if(args.length != 2) {
        //     // We want two stations passed in on the command line for this test.
        //     System.err.println("Usage: SubwayTester [startStation][endStation]");
        //     System.exit(-1);
        // }

        try {
            SubwayLoader loader = new SubwayLoader();
            Subway objectville = loader.loadFromFile(new File("ObjectvilleSubway.txt"));

            // We also validate that the two stations supplied exist on the subway.
            if (!objectville.hasStation(startStation)) {
                System.err.println(startStation + " is not a station in Objectville.");
                System.exit(-1);
            } else if(!objectville.hasStation(endStation)) {
                System.err.println(endStation + " is not a station in Objectville.");
                System.exit(-1);
            }

            // With two valid stations, we can get a route between them...
            List<Connection> route = objectville.getDirections(startStation, endStation);
            SubwayPrinter printer = new SubwayPrinter(System.out);
            // ...and use our new SubwayPrinter class to print out the route.
            printer.printDirections(route);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
