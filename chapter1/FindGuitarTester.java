package chapter1;

import java.util.List;
import java.util.Iterator;

public class FindGuitarTester {
    public static void main(String[] args) { 
        // Set up Rick’s guitar inventory 
        Inventory inventory = new Inventory(); 
        initializeInventory(inventory);
        GuitarSpec whatErinLikes = new GuitarSpec(Builder.FENDER, "Stratocastor", Type.ELECTRIC, Wood.ALDER, Wood.ALDER,6);
        
        List<Guitar> matchingGuitars = inventory.search(whatErinLikes);

        if (!matchingGuitars.isEmpty()) {
            System.out.println("Erin, you might like these guitars:"); 
       
            for (Iterator<Guitar> i = matchingGuitars.iterator(); i.hasNext(); ) {
                Guitar guitar = (Guitar)i.next();
                GuitarSpec spec = guitar.getSpec();
                System.out.println(" We have a " + spec.getBuilder() + " " + spec.getModel() + " " + spec.getType() + " guitar:\n " +
                spec.getBackWood() + " back and sides,\n " + spec.getTopWood() + " with " + spec.getNumStrings() + " string " + ", top.\n You can have it for only $" + guitar.getPrice() + "!\n ----");
            }
        } else {
            System.out.println("Sorry, Erin, we have nothing for you.");
        } 
    }
        
    private static void initializeInventory(Inventory inventory) { 
        // Add guitars to the inventory...
        GuitarSpec StroratocasteGuitarSpec = new GuitarSpec(Builder.FENDER, "Stratocastor", Type.ELECTRIC, Wood.ALDER, Wood.ALDER, 6);
        inventory.addGuitar("V95693", 1499.95, StroratocasteGuitarSpec);
        inventory.addGuitar("V9512", 1549.95, StroratocasteGuitarSpec);
    }

}

