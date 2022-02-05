package chapter2;

public class DogDoorSimulator {

    public static void main(String[] args) {
        
        DogDoor door = new DogDoor();
        Remote remote = new Remote(door);

        System.out.println("Fido Barks to go outside...");
        remote.pressButton();
        
        System.out.println("\nFido has gone outside...");
        System.out.println("\nFido's all done...");

        try {
            Thread.currentThread();
            Thread.sleep(10000);
        } catch(InterruptedException e) {}
        
        System.out.println("...but he is stuck outside!");
        
        System.out.println("\nFido starts barking...");
        
        System.out.println("\nso gina grabs the remote control.");
        remote.pressButton();

        System.out.println("\nFido's back inside...");
        
    }
    
}
