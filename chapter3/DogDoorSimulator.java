package chapter3;

public class DogDoorSimulator {

    public static void main(String[] args) {

        DogDoor door = new DogDoor();
        door.addAllowedBark(new Bark("rowlf"));
        door.addAllowedBark(new Bark("rooowlf"));
        door.addAllowedBark(new Bark("rawlf"));
        door.addAllowedBark(new Bark("woof"));
        BarkRecognizer recognizer = new BarkRecognizer(door);
        // Remote remote = new Remote(door);

        System.out.println("Bruce starts barking.");
        recognizer.recognize(new Bark("rowlf"));

        System.out.println("\nBruce has gone outside...");
      
        try {
            Thread.currentThread();
            Thread.sleep(10000);
        } catch(InterruptedException e) {}
      
        System.out.println("\nBruce's all done...");
        System.out.println("...but he is stuck outside!");
        
        Bark smallDogBark = new Bark("yip");
        System.out.println("A small dog starts barking."); 
        recognizer.recognize(smallDogBark);

        try {
            Thread.currentThread();
            Thread.sleep(10000);
        } catch(InterruptedException e) {}
        
        System.out.println("\nBruce starts barking.");
        recognizer.recognize(new Bark("rooowlf"));
        
        System.out.println("\nBruce's back inside...");
        
    }
    
}
