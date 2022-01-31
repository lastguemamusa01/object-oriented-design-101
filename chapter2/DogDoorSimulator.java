package chapter2;

public class DogDoorSimulator {

    public static void main(String[] args) {
        
        DogDoor door = new DogDoor();
        Remote remote = new Remote(door);
        System.out.println("Fido Barks to go outside...");
        remote.pressButton();
        System.out.println("\nFido has gone outside...");
        remote.pressButton();
        System.out.println("\nFido's all done...");
        remote.pressButton();
        System.out.println("\nFido's back inside...");
        remote.pressButton();
    }
    
}
