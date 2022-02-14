package chapter9.unit;

public class UnitTester {

    public void testType(Unit unit, String type, String expectedOutputType) {
        System.out.println("\nTesting setting/getting the type property.");
        unit.setType(type);
        String outputType = unit.getType();
        if( expectedOutputType.equals(outputType)) {
            System.out.println("Test Passed");
        } else {
            System.out.println("Test failed: " + outputType + " didn't match " + expectedOutputType);
        }
    }

    public void testUnitSpecificProperty(Unit unit, String propertyName, Object inputValue, Object expectedOutputValue)  {
        System.out.println("\nTesting setting/getting unit-specific property.");
        unit.setProperty(propertyName, inputValue);
        Object outputValue = unit.getProperty(propertyName);
        if( expectedOutputValue.equals(outputValue)) {
            System.out.println("Test Passed");
        } else {
            System.out.println("Test failed: " + outputValue + " didn't match " + expectedOutputValue);
        }
    }

    public void testChangeProperty(Unit unit, String propertyName, Object inputValue, Object expectedOutputValue)  {
        System.out.println("\nTesting changing an existing property's value.");
        unit.setProperty(propertyName, inputValue);
        Object outputValue = unit.getProperty(propertyName);
        if( expectedOutputValue.equals(outputValue)) {
            System.out.println("Test Passed");
        } else {
            System.out.println("Test failed: " + outputValue + " didn't match " + expectedOutputValue);
        }
    }

    public void testNonExistentProperty(Unit unit, String propertyName) {
        System.out.println("\nTesting getting a non-existent property's value.");
        
        try {
            Object outputValue = unit.getProperty(propertyName);
        } catch(RuntimeException e) {
            System.out.println("Test Passed");
            return;
        }
        System.out.println("Test failed.");
        
    }

    public static void main(String args[]) {
        UnitTester tester = new UnitTester();
        Unit unit = new Unit(1000);

        tester.testType(unit, "infantry", "infantry");
        tester.testUnitSpecificProperty(unit, "hitPoints", new Integer(25), new Integer(25));
        tester.testChangeProperty(unit, "hitPoints", new Integer(15), new Integer(15));
        tester.testNonExistentProperty(unit, "strength");

        // String name = unit.getName();
        // if((name != null) && (name.length() > 0)) System.out.println("Unit name: " + name);

        // Object value = unit.getProperty("hitPoints");
        // if (value != null) {
        //     try {
        //         Integer hitPoints = (Integer)value;
        //         System.out.println("HitPoints: " + hitPoints);
        //     } catch(ClassCastException e) {
        //         //Handle the potential error
        //     }
        // }
    }
}
