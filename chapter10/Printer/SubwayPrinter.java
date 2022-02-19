package chapter10.Printer;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import chapter10.Subway.Connection;

public class SubwayPrinter {
    private PrintStream out;
    
    // Rather than printing directly to System.out, our class takes in an OutputStream at construction.
    // That allows directions to be output to any output source, not just a console window on the user’s screen.
    public SubwayPrinter(OutputStream out) {
        this.out = new PrintStream(out);
    }

    public void printDirections(List<Connection> route) {
        Connection connection = (Connection)route.get(0);
        String currentLine = connection.getLineName();
        String previousLine = currentLine;
        // We begin by printing the starting station...
        out.println("Start out at " + connection.getStation1().getName() + ".");
        // ...and the first line to get on, as well as the next station to travel towards.
        out.println("Get on the " + currentLine + " heading towards " 
        + connection.getStation2().getName() + ".");
        
        for(int i=1; i < route.size(); i++) {
            connection = (Connection)route.get(i);
            currentLine = connection.getLineName();
            // This looks at the current connection, and figures out if a line change is required.
            if( currentLine.equals(previousLine)) {
                // if it’s the same line, just print the station name.
                out.println(" Continue past " + connection.getStation1().getName() + "...");
            } else {
                // If the line changes, print out how to change lines
                out.println(" When you get to " + connection.getStation1().getName() 
                + ", get off the" + previousLine + ".");
                out.println("Switch over to the " + currentLine
                + ", heading towards" + connection.getStation2().getName() + ".");
                previousLine = currentLine;
            }
        }
        // Finally, we’re through all the connections... get off the subway
        out.println("Get off at " + connection.getStation2().getName() + " and enjoy yourself!");
    }

}
