# object-oriented-design-101

## 1 well-designed apps rock

Does the application actually do what it’s supposed to? And what about things like duplicate code—that can’t be good, can it? It’s usually pretty hard to know what you should work on first, and still make sure you don’t screw everything else up in the process.

Here’s the application that the programming firm built for Rick... they’ve put together a system to completely replace all of Rick’s handwritten notes, and help him match his customers with the perfect guitar. Here’s the UML class diagram they gave Rick to show him what they did:

![image](https://user-images.githubusercontent.com/25869911/151646326-7e32854d-0ddf-4aec-bbc3-d9ed57d5d9c0.png)

* Invetory.java
* Guitar.java

### But then Rick started losing customers...

It seems like no matter who the customer is and what they like, Rick’s new search program almost always comes up empty when it looks for good guitar matches. But Rick knows he has guitars that these customers would like... so what’s going on?

FindGuitarTester.java

It’s obvious that Rick’s app has problems, but it’s not so obvious what we should work on first. And it looks like there’s no shortage of opinion:

![](2022-01-28-22-29-24.png)

 
The customer-friendly programmer says:

* “Great software always does what the customer wants it to. So even if customers think of new ways to use the software, it doesn’t break or give them unexpected results.”

The object-oriented programmer says:

* “Great software is code that is object-oriented. So there’s not a bunch of duplicate code, and each object pretty much controls its own behavior. It’s also easy to extend because your design is really solid and flexible.”


The design-guru programmer says:

* “Great software is when you use tried-and-true design patterns and principles. You’ve kept your objects loosely coupled, and your code open for extension but closed for modification. That also helps make the code more reusable, so you don’t have to rework everything to use parts of your application over and over again.”


First, great software must satisfy the customer. The software must do what the customer wants it to do

Building software that works right is great, but what about when it’s time to add to your code, or reuse it in another application? It’s not enough to just have software that works like the customer wants it to; your software better be able to stand the test of time.

Second, great software is well-designed, well-coded, and easy to maintain, reuse, and extend.

smart - maintain, reuse and extend.


### Great software in 3 easy steps

1. Make sure your software does what the customer wants it to do.
* This is where getting good requirements and doing some analysis comes in.

2. Apply basic OO principles to add flexibility.
* Once your software works, you can look for any duplicate code that might have slipped in, and make sure you’re using good OO programming techniques.

3. Strive for a maintainable, reusable design.
* It’s time to apply patterns and principles to make sure your software is ready to use for years to come.


Let’s put our ideas about how to write great software to the test and see if they hold up in the real world. Rick’s got a search tool that isn’t working, and it’s your job to fix the application, and turn it into something great. Let’s look back at the app and see what’s going on:

Apply 3 easy steps of great software

step 1 -> If we’re starting with functionality, let’s figure out what’s going on with that broken search() method. It looks like in Rick’s inventory, he’s got “Fender” with a capital “F,” and the customer’s specs have “fender” all lowercase. We just need to do a case-insensitive string comparison in the search() method.

Frank: Sure, that would fix the problem Rick’s having now, but I think there’s probably a better way to make this work than just calling toLowerCase() on a bunch of strings all over the place.

Joe: Yeah, I was thinking the same thing. I mean, all that string comparison seems like a bad idea. Couldn’t we use constants or maybe some enumerated types for the builders and woods?

Jill: You guys are thinking way too far ahead. Step 1 was supposed to be fixing the app so it does what the customer wants it to do. I thought we weren’t supposed to worry about design yet.

Frank: Well, yeah, I get that we’re supposed to focus on the customer. But we can at least be smart about how we fix things, right? I mean, why create problems we’ll have to come back and fix later on if we can avoid them from the start?

Jill: Hmmm... I guess that does make sense. We don’t want our solution to this problem creating new design problems for us down the road. But we’re still not going to mess with the other parts of the application, right?

Frank: Right. We can just remove all those strings, and the string comparisons, to avoid this whole case-matching thing.

Joe: Exactly. If we go with enumerated types, we can ensure that only valid values for the builder, woods, and type of guitar are accepted. That’ll make sure that Rick’s clients actually get to look at guitars that match their preferences.

Jill: And we’ve actually done a little bit of design at the same time... very cool! Let’s put this into action.

Don’t create problems to solve problems.

chose the optimal solution

* Original Java Code Before refactoring :

* Guitar.java
```java
public class Guitar {

    private String serialNumber, builder, model, type, backWood, topWood;
    private double price;

    public Guitar(String serialNumber, double price, String builder, String model, String type, String backWood, String topWood) {
        this.serialNumber = serialNumber;
        this.price = price;
        this.builder = builder;
        this.model = model;
        this.type = type;
        this.backWood = backWood;
        this.topWood = topWood;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public double getPrice() { 
        return price;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    public String getBuilder() { 
        return builder;
    }

    public String getModel() {
        return model; 
    }

    public String getType() { 
        return type;
    }

    public String getBackWood() {
        return backWood; 
    }

    public String getTopWood() { 
        return topWood;
    }
}
```


* Inventory.java

```java
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Inventory {
    private List guitars;

    public Inventory() {
        guitars = new LinkedList();
    }

    public void addGuitar(String serialNumber, double price, String builder, String model, String type, String backWood, String topWood) {
        Guitar guitar = new Guitar(serialNumber, price, builder, model, type, backWood, topWood);
        guitars.add(guitar);
    } 

    public Guitar getGuitar(String serialNumber) {
        for(Iterator i = guitars.iterator(); i.hasNext(); ) {
            Guitar guitar = (Guitar)i.next();
            if( guitar.getSerialNumber().equals(serialNumber)) {
                return guitar;
            }
        }
        return null;
    }

    public Guitar search(Guitar searchGuitar) {
        for(Iterator i = guitars.iterator(); i.hasNext();) {
            Guitar guitar = (Guitar)i.next();
            // Ignore serial number since that’s unique
            // Ignore price since that’s unique

            String builder = searchGuitar.getBuilder();
            if ((builder != null) && (!builder.equals("")) && (!builder.equals(guitar.getBuilder()))) continue;
            
            String model = searchGuitar.getModel();
            if ((model != null) && (!model.equals("")) && (!model.equals(guitar.getModel()))) continue;
            
            String type = searchGuitar.getType();
            if ((type != null) && (!searchGuitar.equals("")) && (!type.equals(guitar.getType()))) continue;
            
            String backWood = searchGuitar.getBackWood();
            if ((backWood != null) && (!backWood.equals("")) && (!backWood.equals(guitar.getBackWood()))) continue;
            
            String topWood = searchGuitar.getTopWood();
            if ((topWood != null) && (!topWood.equals("")) && (!topWood.equals(guitar.getTopWood()))) continue;
        }
        return null;
    }
}
```

* FindGuitarTester.java 
```java
public class FindGuitarTester {
    public static void main(String[] args) { 
        // Set up Rick’s guitar inventory 
        Inventory inventory = new Inventory(); 
        initializeInventory(inventory);
        Guitar whatErinLikes = new Guitar("", 0, "fender", "Stratocastor", "electric", "Alder", "Alder");
        Guitar guitar = inventory.search(whatErinLikes); 
        
        if (guitar != null) {
            System.out.println("Erin, you might like this " + guitar.getBuilder() + " " + guitar.getModel() + " " + guitar.getType() + " guitar:\n " + 
            guitar.getBackWood() + " back and sides,\n " + guitar.getTopWood() + " top.\nYou can have it for only $" + guitar.getPrice() + "!");
        } else {
            System.out.println("Sorry, Erin, we have nothing for you.");
        } 
    }
        
    private static void initializeInventory(Inventory inventory) { 
        // Add guitars to the inventory...
        inventory.addGuitar("V95693", 1499.95, "Fender", "Stratocastor", "electric", "Alder", "Alder");
    }

}
```

### Ditching String comparisons

The first improvement we can make to Rick’s guitar search tool is getting rid of all those annoying String comparisons. And even though you could use a function like toLowerCase() to avoid problems with uppercase and lowercase letters, let’s avoid String comparisons altogether:

* Builder.java
* Type.java
* Wood.java

One of the big advantages of using enums is that it limits the possible values you can supply to a method... no more misspellings or case issues.

Enums are enumerated types. They’re available in C, C++, Java version 5.0 and up, and will even be a part of Perl 6.

The cool thing about enums is that methods or classes that use them are protected from any values not defined in the enum.
So you can’t misspell or mistype an enum without getting a compiler error. It’s a great way to get not only type safety, but value safety; you can avoid getting bad data for anything that has a standard range or set of legal values.


```java
Guitar whatErinLikes = new Guitar("", 0, Builder.FENDER, "Stratocastor", Type.ELECTRIC, Wood.ALDER, Wood.ALDER);
```

The only String left is for the model, since there really isn’t a limited set of these like there is with builders and wood.

It looks like nothing has changed, but with enums, we don’t have to worry about these comparisons getting screwed up by misspellings or case issues.

```java
String model = searchGuitar.getModel().toLowerCase(); 
if ((model != null) && (!model.equals("")) && (!model.equals(guitar.getModel().toLowerCase()))) continue;
```

The only property that we need to worry about case on is the model, since that’s still a String.

![](2022-01-28-23-31-28.png)

[completed] - 1. Make sure your software does what the customer wants it to do.
Even better, we’ve made Rick’s application less fragile along the way. It’s not going to break so easily now, because we’ve added both type safety and value safety with these enums. That means less problems for Rick, and less maintenance for us.

Code that is not fragile is generally referred to as robust code

So it’s OK to do a little design when I’m working on Step 1, right?

* Yeah, as long as your focus is still on the customer’s needs. You want the basic features of your application in place before you start making big design changes. But while you’re working on functionality, you can certainly use good OO principles and techniques to make sure your application is well designed from the start.

That diagram over on page 18 is a class diagram right? Or is it class diagrams, since it’s more than one class?

* It is a class diagram, and a single diagram can have multiple classes in it. In fact, class diagrams can show a lot more detail than you’ve seen so far, and we’ll be adding to them in the next several chapters.

So we’re ready to move on to Step 2, and start applying OO principles, right?

* Not quite... there’s one more thing Rick would like us to help him with before we’re ready to start analyzing our code for places we might be able to improve it. Remember, our first job is to please the customer, and then we really focus on improving our OO design.


### Rick’s customers want choices!

Rick’s come up with a new requirement for his app: he wants his search tool to return all the guitars that match his client’s specs, not just the first one in his inventory.

```java
inventory.addGuitar("V95693", 1499.95, Builder.FENDER, "Stratocastor", Type.ELECTRIC, Wood.ALDER, Wood.ALDER);
inventory.addGuitar("V9512", 1549.95, Builder.FENDER, "Stratocastor", Type.ELECTRIC, Wood.ALDER, Wood.ALDER);
```

![](2022-01-28-23-46-40.png)

Inventory.java before
```java
public class Inventory {
    private List guitars;

    public Inventory() {
        guitars = new LinkedList();
    }

    public void addGuitar(String serialNumber, double price, Builder builder, String model, Type type, Wood backWood, Wood topWood) {
        Guitar guitar = new Guitar(serialNumber, price, builder, model, type, backWood, topWood);
        guitars.add(guitar);
    } 

    public Guitar getGuitar(String serialNumber) {
        for(Iterator i = guitars.iterator(); i.hasNext(); ) {
            Guitar guitar = (Guitar)i.next();
            if( guitar.getSerialNumber().equals(serialNumber)) {
                return guitar;
            }
        }
        return null;
    }

    public Guitar search(Guitar searchGuitar) {
        for(Iterator i = guitars.iterator(); i.hasNext();) {
            Guitar guitar = (Guitar)i.next();
            // Ignore serial number since that’s unique
            // Ignore price since that’s unique

            Builder builder = searchGuitar.getBuilder();
            if ((builder != null) && (!builder.equals("")) && (!builder.equals(guitar.getBuilder()))) continue;
            
            String model = searchGuitar.getModel().toLowerCase();
            if ((model != null) && (!model.equals("")) && (!model.equals(guitar.getModel().toLowerCase()))) continue;
            
            Type type = searchGuitar.getType();
            if ((type != null) && (!searchGuitar.equals("")) && (!type.equals(guitar.getType()))) continue;
            
            Wood backWood = searchGuitar.getBackWood();
            if ((backWood != null) && (!backWood.equals("")) && (!backWood.equals(guitar.getBackWood()))) continue;
            
            Wood topWood = searchGuitar.getTopWood();
            if ((topWood != null) && (!topWood.equals("")) && (!topWood.equals(guitar.getTopWood()))) continue;
        }
        return null;
    }
}
```

* Inventory.java will be change to return List Search Method.

So I’m not done with the first step until the application works like my customer wants it to?

* Exactly. You want to make sure that the application works like it should before you dive into applying design patterns or trying to do any real restructuring of how the application is put together.

And why is it so important to finish Step 1 before going on to Step 2?

* You’re going to make lots of changes to your software when you’re getting it to work right. Trying to do too much design before you’ve at least got the basic functionality down can end up being a waste, because a lot of the design will change as you’re adding new pieces of functionality to your classes and methods. (Avoid Premature optimization)


You seem sort of hung up on this “Step 1” and “Step 2” business. What if I don’t code my apps that way?

* There’s nothing that says you have to follow these steps exactly, but they do provide an easy path to follow to make sure your software does what it’s supposed to, and is well-designed and easy to reuse. If you’ve got something similar that accomplishes the same goals, that’s great!

### Test drive

We’ve talked a lot about getting the right requirements from the customer, but now we need to make sure we’ve actually got those requirements handled by our code. Let’s test things out, and see if our app is working like Rick wants it to:

FindGuitarTester.java before
```java
public class FindGuitarTester {
    public static void main(String[] args) { 
        // Set up Rick’s guitar inventory 
        Inventory inventory = new Inventory(); 
        initializeInventory(inventory);
        Guitar whatErinLikes = new Guitar("", 0, Builder.FENDER, "Stratocastor", Type.ELECTRIC, Wood.ALDER, Wood.ALDER);
        Guitar guitar = inventory.search(whatErinLikes); 
        
        if (guitar != null) {
            System.out.println("Erin, you might like this " + guitar.getBuilder() + " " + guitar.getModel() + " " + guitar.getType() + " guitar:\n " + 
            guitar.getBackWood() + " back and sides,\n " + guitar.getTopWood() + " top.\nYou can have it for only $" + guitar.getPrice() + "!");
        } else {
            System.out.println("Sorry, Erin, we have nothing for you.");
        } 
    }
        
    private static void initializeInventory(Inventory inventory) { 
        // Add guitars to the inventory...
        inventory.addGuitar("V95693", 1499.95, Builder.FENDER, "Stratocastor", Type.ELECTRIC, Wood.ALDER, Wood.ALDER);
        inventory.addGuitar("V9512", 1549.95, Builder.FENDER, "Stratocastor", Type.ELECTRIC, Wood.ALDER, Wood.ALDER);
    }

}
```

we are goingo to change the FindGuitarTester.java to work with search method that return list

Now that Rick’s all set with our software, we can begin to use some OO principles and make sure the app is flexible and well-designed.

2. Apply basic OO principles to add flexibility

So this is where we can make sure there’s no duplicate code, and all our objects are well designed, right?
Here’s where you take software that works, and make sure the way it’s put together actually makes sense.

### Looking for problems

Let’s dig a little deeper into our search tool, and see if we can find any problems that some simple OO principles might help improve. Let’s start by taking a closer look at how the search() method in Inventory works:

![](2022-01-29-15-09-56.png)

### Analyze the search() method

Let’s spend a little time analyzing exactly what goes on in the search() method of Inventory.java. Before we look at the code, though, let’s think about what this method should do.

The client provides their guitar preferences.

* Each of Rick’s clients has some properties that they’re interested in finding in their ideal guitar: the woods used, or the type of guitar, or a particular builder or model. They provide these preferences to Rick, who feeds them into his inventory search tool.

The search tool looks through Rick’s inventory.

* Once the search tool knows what Rick’s client wants, it starts to loop through each guitar in Rick’s inventory.

Each guitar is compared to the client’s preferences.

* For each guitar in Rick’s inventory, the search tool sees if that guitar matches the client’s preferences. If there’s a match, the matching guitar is added to the list of choices for the client.

Rick’s client is given a list of matching guitars.

* Finally, the list of matching guitars is returned to Rick and his client. The client can make a choice, and Rick can make a sale.


The client can specify only general properties of an instrument. So they never supply a serial number or a price.

Use a textual description of the problem you’re trying to solve to make sure that your design lines up with the intended functionality of your application

### The Mystery of the Mismatched Object Type

objects are very particular about their jobs. Each object is interested in doing its job, and only its job, to the best of its ability. There’s nothing a well-designed object hates more than being used to do something that really isn’t its true purpose.

Unfortunately, it’s come to our attention that this is exactly what is happening in Rick’s inventory search tool: somewhere, an object is being used to do something that it really shouldn’t be doing. It’s your job to solve this mystery and figure out how we can get Rick’s application back in line.

1. Objects should do what their names indicate.

* If an object is named Jet, it should probably takeOff() and land(), but it shouldn’t takeTicket()—that’s the job of another object, and doesn’t belong in Jet.

2. Each object should represent a single concept.

* You don’t want objects serving double or triple duty. Avoid a Duck object that represents a real quacking duck, a yellow plastic duck, and someone dropping their head down to avoid getting hit by a baseball.

3. Unused properties are a dead giveaway.

* If you’ve got an object that is being used with no-value or null properties often, you’ve probably got an object doing more than one job. If you rarely have values for a certain property, why is that property part of the object? Would there be a better object to use with just a subset of those properties?

You know, Rick’s clients really aren’t providing a Guitar object... I mean, they don’t actually give him a guitar to compare against his inventory

Frank: Hey, that’s right, Joe. I hadn’t thought about that before. 

Jill: So what? Using a Guitar object makes it really easy to do comparisons in the search() method.

Joe: Not any more than some other object would. Look:

```java
if (searchGuitar.getBuilder() != guitar.getBuilder()) { continue; }
```

Joe: It really doesn’t matter what type of object we’re using there, as long as we can figure out what specific things Rick’s clients are looking for.

Frank: Yeah, I think we should have a new object that stores just the specs that clients want to send to the search() method. Then they’re not sending an entire Guitar object, which never seemed to make much sense to me.

Jill: But isn’t that going to create some duplicate code? If there’s an object for all the client’s specs, and then the Guitar has all its properties, we’ve got two getBuilder() methods, two getBackWood() methods... that’s not good.

Frank: So why don’t we just encapsulate those properties away from Guitar into a new object?

Joe: Whoa... I was with you until you said “encapsulate.” I thought that was when you made all your variables private, so nobody could use them incorrectly. What’s that got to do with a guitar’s properties?

Frank: Encapsulation is also about breaking your app into logical parts, and then keeping those parts separate. So just like you keep the data in your classes separate from the rest of your app’s behavior, we can keep the generic properties of a guitar separate from the actual Guitar object itself.

Jill: And then Guitar just has a variable pointing to a new object type that stores all its properties?

Frank: Exactly! So we’ve really encapsulated the guitar properties out of Guitar, and put them in their own separate object. Look, we could do something like this...

Encapsulation allows you to hide the inner workings of your application’s parts, but yet make it clear what each part does.

Create the GuitarSpec object.

![](2022-01-29-15-26-12.png)

### Now update your own code

With this class diagram, you should be able to add the GuitarSpec class to your application, and update the Guitar class as well. Go ahead and make any changes you need to Inventory.java so that the search tool compiles, as well.

I understand why we need an object for the client to send specs to search()... but why are we using that object to hold properties for Guitar, too?

Suppose you just used GuitarSpec to hold client specs for sending to the search() method, and you kept the Guitar class just the same as it was. If Rick started carrying 12-string guitars, and wanted a numStrings property, you’d have to add that property—and code for a getNumStrings() method—to both the GuitarSpec and Guitar classes. Can you see how this would lead to duplicate code?

Instead, we can put all that (potentially) duplicate code into the GuitarSpec class, and then have Guitar objects reference an instance of it to avoid any duplication.

Anytime you see duplicate code, look for a place to encapsulate!

I still am confused about how this is a form of encapsulation. Can you explain that again?

The idea behind encapsulation is to protect information in one part of your application from the other parts of your application. In its simplest form, you can protect the data
in your class from the rest of your app by making that data private. But sometimes the information might be an entire
set of properties—like the details about a guitar—or even behavior—like how a particular type of duck flies.

When you break that behavior out from a class, you can change the behavior without the class having to change as well. So if you changed how properties were stored, you wouldn’t have to change your Guitar class at all, because the properties are encapsulated away from Guitar.
That’s the power of encapsulation: by breaking up the different parts of your app, you can change one part without having to change all the other parts. In general, you should encapsulate the parts of your app that might vary away from the parts that will stay the same.

guitar.java file before change
```java
public class Guitar {

    private String serialNumber, model;
    private double price;
    private Builder builder;
    private Type type;
    private Wood backWood, topWood;


    public Guitar(String serialNumber, double price, Builder builder, String model, Type type, Wood backWood, Wood topWood) {
        this.serialNumber = serialNumber;
        this.price = price;
        this.builder = builder;
        this.model = model;
        this.type = type;
        this.backWood = backWood;
        this.topWood = topWood;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public double getPrice() { 
        return price;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    public Builder getBuilder() { 
        return builder;
    }

    public String getModel() {
        return model; 
    }

    public Type getType() { 
        return type;
    }

    public Wood getBackWood() {
        return backWood; 
    }

    public Wood getTopWood() { 
        return topWood;
    }
}
```

guitar.java modified and encapsulated to GuitarSpec.java(created).

### update the inventory class

Now that we’ve encapsulated away the specifications of a guitar, we’ll need to make a few other changes to our code.

![](2022-01-29-15-37-57.png)


### Getting ready for another test drive

You’ll need to update the FindGuitarTester class to test out all these new changes:



Encapsulation isn’t the only OO principle I can use at this stage, is it?

Other good OO principles that you might want to think about at this stage are inheritance and polymorphism


But I don’t really see how this encapsulation makes my code more flexible. Can you explain that again?

Once you’ve gotten your software to work like it’s supposed to, flexibility becomes a big deal. What if the customer wants to add new properties or features to the app? If you’ve got tons of duplicate code or confusing inheritance structures in your app, making changes is going to be a pain.

By introducing principles like encapsulation and good class design into your code, it’s easier to make these changes, and your application becomes a lot more flexible.

### Design once, design twice


Once you’ve taken a first pass over your software and applied some basic OO principles, you’re ready to take another look, and this time make sure your software is not only flexible, but easily reused and extended.

3. Strive for a maintainable, reusable design.

Once you’ve applied some basic OO principles, you’re ready to apply some patterns and really focus on reuse.

### Let’s make sure Inventory.java is really well-designed

We’ve already used encapsulation to improve the design of Rick’s search tool, but there are still some places in our code where we could get rid of potential problems. This will make our code easier to extend when Rick comes up with that next new feature he wants in his inventory search tool, and easier to reuse if we want to take just a few parts of the app and use them in other contexts.

Take a look at the class diagram for Rick’s application, and think about what you would need to do to add support for
12-string guitars. What properties and methods would you need to add, and to what classes? And what code would you need to change to allow Rick’s clients to search for 12-strings?

How many classes did you have to modify to make this change? Do you think Rick’s application is well designed right now?


We’re adding a property to GuitarSpec, but we have to change code in the Inventory class’s search() method, as well as in the constructor to the Guitar class.

So that’s the problem, right? We shouldn’t have to change code in Guitar and Inventory to add a new property to the GuitarSpec class. Can’t we just use more encapsulation to fix this?

![](2022-01-29-16-38-52.png)


That’s right—we need to encapsulate the guitar specifications and isolate them from the rest of Rick’s guitar search tool

![](2022-01-29-16-45-49.png)


It’s not enough to know what’s wrong with Rick’s app, or even to figure out that we need some more encapsulation. Now we need to actually figure out how to fix his app so it’s easier to reuse and extend

Adding a new property to GuitarSpec.java results in changes to the code in Guitar.java and Inventory.java. The application should be restructured so that adding properties to GuitarSpec doesn’t affect the code in the rest of the application.

Your task:

* 1. Add a numStrings property and getNumStrings() method to GuitarSpec.java.
* 2. Modify Guitar.java so that the properties of GuitarSpec are encapsulated away from the constructor of the class.
* 3. Change the search() method in Inventory.java to delegate comparing the two GuitarSpec objects to the GuitarSpec class, instead of handling the comparison directly.
* 4. Update FindGuitarTester.java to work with your new classes, and make sure everything still works.
* 5. Compare your answers with ours on page 44, and then get ready for another test drive to see if we’ve finally got this application finished.

You said I should “delegate” comparisons to GuitarSpec. What’s delegation?

Delegation is when an object needs to perform a certain task, and instead of doing that task directly, it asks another object to handle the task (or sometimes just a part of the task).

So in the design puzzle, you want the search() method in Inventory to ask GuitarSpec to tell it if two specs are equal, instead of comparing the two GuitarSpec objects directly within the search() method itself. search() delegates the comparison to GuitarSpec.

And what does delegation have to do with code being more reusable?

Delegation lets each object worry about equality (or some other task) on its own. This means your objects are more independent of each other, or more loosely coupled. Loosely coupled objects can be taken from one app and easily reused in another, because they’re not tightly tied to other objects’ code.

What’s the point of that?

Delegation makes your code more reusable. It also lets each object worry about its own functionality, rather than spreading the code that handles a single object’s behavior all throughout your application

One of the most common examples of delegation in Java is the equals() method. Instead of a method trying to figure out if two objects are equal, it calls equals() on one of the objects and passes in the second object. Then it just gets back a true or false response from the equals() method.


And what does loosely coupled mean again?

Loosely coupled is when the objects in your application each have a specific job to do, and they do only that job. So the functionality of your app is spread out over lots of well-defined objects, which each do a single task really well.


And Why is that good ?

Loosely coupled applications are usually more flexible, and easy to change. Since each object is pretty independent of the other objects, you can make a change to one object’s behavior without having to change all the rest of your objects. So adding new features or functionality becomes a lot easier.

delegation.- The act of one object forwarding an operation to another object, to be performed on
behalf of the first object.

The problem:
Adding a new property to GuitarSpec.java results in changes to the code in Guitar.java and Inventory.java. The application should be refactored so that adding properties to GuitarSpec doesn’t affect the code in the rest of the application.

![](2022-01-29-17-24-34.png)


He just wanted to write great software. So what’s the answer? How do you write great software consistently?

* You just need a set of steps to follow that makes sure your software works and is well designed. It can be as simple as the three steps we used in working on Rick’s app; you just need something that works, and that you can use on all of your software projects

Object-Oriented Analysis & Design helps you write great software, every time

* All this time that we’ve been talking about the three steps you can follow to write great software, we’ve really been talking about OOA&D. OOA&D is really just an approach to writing software that focuses on making sure your code does what it’s supposed to, and that it’s well designed. That means your code is flexible, it’s easy to make changes to it, and it’s maintainable and reusable.

### OOA&D is about writing great software, not doing a bunch of paperwork!


Customers are satisfied when their apps WORK.

* We can get requirements from the customer to make sure that we build them what they ask for. Use cases and diagrams are helpful ways to do that, but it’s all about figuring out what the customer wants the app to do.

Customers are satisfied when their apps KEEP WORKING.

* Nobody is happy when an application that worked yesterday is crashing today. If we design our apps well, then they’re going to be robust, and not break every time a customer uses them in unusual ways. Class and sequence diagrams can help show us design problems, but the point is to write well-designed and robust code.

Customers are satisfied when their apps can be UPGRADED.

* There’s nothing worse than a customer asking for a simple new feature, and being told it’s going to take two weeks and $25,000 to make it happen. Using OO techniques like encapsulation, composition, and delegation will make your applications maintainable and extensible.

Programmers are satisfied when their apps can be REUSED.

* Ever built something for one customer, and realized you could use something almost exactly the same for another customer? If you do just a little bit of analysis on your apps, you can make sure they’re easily reused, by avoiding all sorts of nasty dependencies and associations that you don’t really need. Concepts like the Open-Closed Principle (OCP) and the Single Responsibility Principle (SRP) are big time in helping here.

* Programmers are satisfied when their apps are FLEXIBLE.

Sometimes just a little refactoring can take a good app and turn it into a nice framework that can be used for all sorts of different things. This is where you can begin to move from being a head-down coder and start thinking like a real architect (oh yeah, those guys make a lot more money, too). Big-picture thinking is where it’s at.


This is ALL OOA&D! It’s not about doing silly diagrams... it’s about writing killer applications that leave your customer happy, and you feeling like you’ve kicked major ass.


### Bullet Points

* It takes very little for something to go wrong with an ◆ application that is fragile.
* You can use OO principles like encapsulation and delegation to build applications that are flexible. 
*  Encapsulation is breaking your application into logical parts that have a clear boundary that allows an object to hide its data and methods from other objects. 
* Delegation is giving another object the responsibility of handling a particular task.
* Always begin a project by figuring out what the customer wants.
* Once you’ve got the basic functionality of an app in place, work on refining the design so it’s flexible.
* With a functional and flexible design, you can employ design patterns to improve your design further, and make your app easier to reuse.
* Find the parts of your application that change often, and try and separate them from the parts of your application that don't change.
* Building an application that works well but is poorly designed satisfies the customer but will leave you with pain, suffering, and lots of late nights fixing problems.
* Object oriented analysis and design (OOA&D) provides a way to produce well-designed applications that satisfy both the customer and the programmer.

Funcionality -> Without me, you’ll never actually make the customer happy. No matter how well-designed your application is, I’m the thing that puts a smile on the customer’s face.

Design Paterrn - > I’m all about reuse and making sure you’re not trying to solve a problem that someone else has already figured out.

Encapsulation -> You use me to keep the parts of your code that stay the same separate from the parts that change; then it’s really easy to make changes to your code without breaking everything.

Flexibility -> Use me so that your software can change and grow without constant rework. I keep your application from being fragile.

## 2 gathering requirements 

Give them what they want

Everybody loves a satisfied customer. You already know that the first step in writing great software is making sure it does what the customer wants it to. But how do you figure out what a customer really wants? And how do you make sure that the customer even knows what they really want? That’s where good requirements come in, and in this chapter, you’re going to learn how to satisfy your customer by making sure what you deliver is actually what they asked for.

### You’ve got a new programming gig

![](2022-01-31-13-49-12.png)


### But when Gina tried it...

![](2022-01-31-14-07-01.png)

### So what exactly is a requirement, anyway?

![](2022-01-31-14-11-42.png)

### Creating a requirements list

![](2022-01-31-14-19-41.png)

### what does the dog door really need to do ?

![](2022-01-31-14-25-24.png)

### plan for things going wrong

![](2022-01-31-14-28-20.png)

### Alternate paths handle system problems

A use case is what people call the steps that a system takes to make something happen.

![](2022-01-31-14-32-11.png)

### (Re) introducing use cases

A use case describes what your system does to accomplish a particular customer goal.

![](2022-01-31-14-37-26.png)

### One use case, three parts

![](2022-02-02-12-48-00.png)

![](2022-02-02-12-51-23.png)

### Checking your requirements against your use cases

So far, you’ve got an initial set of requirements and a good solid use case. But now you need to go back to your requirements and make sure that they’ll cover everything your system has to do.
And that’s where the use case comes in:

![](2022-02-02-13-05-25.png)

### So now can we write some code?

With use case and requirements in hand, you’re ready to write code that you know will make Todd and Gina satisfied customers. Let’s check out our requirements and see exactly what we’re going to have to write code for:


### Automatically closing the door

The only requirement left to code is taking care of automatically closing the door after it’s been opened. 

Change on Remote.java class (add timer to close the door)

 Why did you make the timer variable final?

 Because we need to call its cancel() method in the TimerTask anonymous class. If you need to access variables in your anonymous class from the enclosing class (that’s Remote in this case), those variables must be final. And, really, just because it makes things work.

 Why are you calling cancel()? Won’t the timer quit automatically after running the TimerTask?

 t will, but it turns out that most JVMs take forever before they garbage collect the Timer. That ends up hanging the program, and your code will run for hours before it actually quits gracefully. That’s no good, but calling cancel() manually takes care of the problem.


### We need a new simulator!

Our old simulator isn’t that useful anymore... it assumes Todd and Gina are closing the door manually, and not letting the timer do its work. Let’s update our simulator to make it work with the updated Remote class:t

DogDoorSimultor -> change to only press once

### Test drive, version 2.0

we need to test alternate paths as well as the main path.

plan and test for when things go wrong.

### Reviewing the alternate path

![](2022-02-02-13-20-23.png)

change the DogDoorSimultor.java -> add try catch for this alternative path

### Delivering the new dog door

Good use cases, requirements, main paths, alternate paths, and a working simulator; we’re definitely on the road to great software. Let’s take the new dog door to Todd and Gina.

![](2022-02-02-13-39-46.png)

- make use cases

![](2022-02-02-13-46-58.png)

- check that use cases have clear value, start and stop, external initiator

- make requierement from use cases

![](2022-02-02-13-47-32.png)

![](2022-02-02-15-57-06.png)

### Tools for your OOA&D Toolbox

![](2022-02-02-16-00-34.png)

## 3 - requirements change

So you’ve talked to your customer, gathered requirements, written out your use cases, and delivered a killer application. It’s time for a nice relaxing cocktail, right? Right... until your customer decides that they really wanted something different than what they told you. They love what you’ve done, really, but it’s not quite good enough anymore. In the real world, requirements are always changing, and it’s up to you to roll with these changes and keep your customer satisfied.

![](2022-02-03-17-05-14.png)

Requirements always change. If you’ve got good use cases, though, you can usually change your software quickly to adjust to those new requirements.

![](2022-02-03-17-12-21.png)

![](2022-02-03-17-13-35.png)

![](2022-02-03-17-15-05.png)


A complete path through a use case, from the first step to the last, is called a scenario.
Most use cases have several different scenarios, but they always share the same user goal.

![](2022-02-03-17-18-44.png)

all scenarios is all permutation of the steps.

I think we should recheck our requirements list against the new use case. If Todd and Gina’s requirements changed, then our requirements list might change too, right?

Any time you change your use case, you need to go back and check your requirements.

Now we can start coding the dog door again

With new requirements comes new code. We need some barking, a bark recognizer to listen for barking, and then a dog door to open up:

![](2022-02-03-17-23-12.png)


BarkRecognizer.java -> recognize() method

![](2022-02-03-17-29-28.png)

### Power up the new dog door

Use cases, requirements, and code have all led up to this. Let’s see if everything works like it should.

update the DogDoorSimulator.java -> add new barkrecognizer class and use the recognize method.

![](2022-02-03-17-41-02.png)

![](2022-02-03-17-41-11.png)


Add the timer to DogDoor.java and remove on remot.javae the timer

Sometimes a change in requirements reveals problems with your system that you didn’t even know were there.

Change is constant, and your system should always improve every time you work on it.

Encapsulation helped us realize that the dog door should handle closing itself. We separated the door’s behavior from the rest of the code in our app.

![](2022-02-03-17-59-39.png)

## 4 Analysis

Taking Your Software into the Real World

Your application has to do more than work on your own personal development machine, finely tuned and perfectly set up; your apps have to work when real people use them. This chapter is all about making sure that your software works in a real-world context. You’ll learn how textual analysis can take that use case you’ve been working on and turn it into classes and methods that you know are what your customers want.




Analysis helps you ensure your system works in a real-world context.
       
![](2022-02-03-18-10-03.png)


dentify the problem

The first step in good analysis is figuring out potential problems. We already know that there’s a problem when there are multiple dogs in the same neighborhood:

Plan a solution

It looks like there’s a change we need to make in what our system does. Do you know what it is? Below is a part of the diagram detailing how the dog door system works:

![](2022-02-03-18-12-39.png)

Analysis and your use cases let you show customers, managers, and other developers how your system works in a real world context.

![](2022-02-03-18-16-13.png)

### Update your use case

Since we’ve changed our dog door diagram, we need to go back to the dog door use case, and update it with the new steps we’ve figured out. Then, over the next few pages, we’ll figure out what changes we need to make to our code.

![](2022-02-03-18-20-13.png)

we need to add user case for storing a dog bark

![](2022-02-03-18-23-45.png)

![](2022-02-03-18-27-11.png)

![](2022-02-03-18-27-20.png)

![](2022-02-03-18-30-32.png)


![](2022-02-03-18-47-40.png)

The power of loosely coupled applications

In Chapter 1, we said that delegation helps our applications stay loosely coupled. That means that your objects are independent of each other; in other words, changes to one object don’t require you to make a bunch of changes to other objects.

Delegation shields your objects from implementation changes to other objects in your software.

![](2022-02-03-18-49-26.png)


![](2022-02-03-18-52-57.png)

![](2022-02-03-18-57-14.png)

![](2022-02-03-18-57-22.png)

![](2022-02-03-19-03-52.png)

![](2022-02-03-19-06-41.png)

![](2022-02-03-19-11-03.png)

![](2022-02-03-19-14-46.png)

![](2022-02-03-19-14-57.png)

![](2022-02-03-22-50-07.png)

![](2022-02-03-22-53-26.png)

![](2022-02-03-22-57-09.png)

Class diagrams aren’t everything

Class diagrams are a great way to get an overview of your system, and show the parts of your system to co-workers and other programmers. But there’s still plenty of things that they don’t show.

![](2022-02-03-23-03-22.png)

### So how does recognize() work now?

Maria’s figured out that her BarkRecognizer class should be able to compare any bark it receives against multiple allowed barks, but her class diagram doesn’t tell us much about how to actually write the recognize() method.

![](2022-02-03-23-04-35.png)

![](2022-02-03-23-08-09.png)

![](2022-02-03-23-12-34.png)


## 5 (part 1) good design = flexible software - Nothing Ever Stays the Same

Change is inevitable. No matter how much you like your software right now, it’s probably going to change tomorrow. And the harder you make it for your software to change, the more difficult
it’s going to be to respond to your customer’s changing needs. In this chapter, we’re going to revisit an old friend, try and improve an existing software project, and see how small changes can turn into big problems

## Rick’s Guitar is expanding (Stringed Instruments)

We’ve talked a lot about good analysis and design being the key to software that you can reuse and extend.

![](2022-02-04-21-31-37.png)

![](2022-02-04-21-36-05.png)

Abstract classes are placeholders for actual implementation classes.

The abstract class defines behavior, and the subclasses implement that behavior.

![](2022-02-04-21-40-29.png)

![](2022-02-04-21-43-00.png)

### UML cheat Sheet


### Let’s code Rick’s new search tool

We can start off by creating a new class, Instrument, and making it abstract. Then we put all the properties common to an instrument in this class

![](2022-02-04-21-48-45.png)


create Instrument abstract class
create guitar and mandolin class that is extends Instrument

With the instruments taken care of, we can move on to the spec classes. We need to create another abstract class, InstrumentSpec, since so many instruments have common specifications:

Let’s code GuitarSpec...

After seeing GuitarSpec, MandolinSpec is pretty simple.

All that’s left is to update the Inventory class to work with multiple instrument types, instead of just the Guitar class:

![](2022-02-04-22-40-08.png)

You’ve made some MAJOR improvements to Rick’s app You’ve done a lot more than just

add support for mandolins to Rick’s application. By abstracting common properties and behavior into the Instrument and InstrumentSpec classes, you’ve made the classes in
Rick’s app more independent. That’s a significant improvement in his design.

Great software isn’t built in a day

Along with some major design improvements, we’ve uncovered a few problems with the search tool. That’s OK... you’re almost always going to find  a few new problems when you make big changes to your design.

So now our job is to take Rick’s better- designed application, and see if we can improve it even further... to take it from good software to GREAT software.


1. Does the new search tool do what it’s supposed to do?

Absolutely. It finds guitars and mandolins, although not at the same time. So maybe it just mostly does what it’s supposed to do. Better ask Rick to be sure...

2. Have you used solid OO principles, like encapsulation, to avoid duplicate code and make your software easy to extend?

We used encapsulation when we came up with the InstrumentSpec classes, and inheritance when we developed an Instrument and InstrumentSpec abstract superclass. But it still takes a lot of work to add new instrument types..

3. How easy is it to reuse Rick’s application? Do changes to one part of the app force you to make lots of changes in other parts of the app? Is his software loosely coupled?

It’s sort of hard to use just parts of Rick’s application. Everything’s pretty tightly connected, and InstrumentSpec is actually part of Instrument (remember when we talked about aggregation?).

One of the best ways to see if software is well-designed is to try and CHANGE it.

If your software is hard to change, there’s probably something you can improve about the design. Let’s see how hard it is to add a couple of new instruments to Rick’s app:

![](2022-02-04-22-48-23.png)


for each new instrument type you need to add that class(Banjo), spec class(BnajoSpec) and modify inventory.class(update)

![](2022-02-04-22-52-52.png)


### So what are we supposed to do now?

It looks like we’ve definitely still got some work to do to turn Rick’s application into great software that’s truly easy to change and extend. But that doesn’t mean the work you’ve done isn’t important... lots of times, you’ve got to improve your designto find some problems that weren’t so apparent earlier on. Now that we’ve applied some of our OO principles to Rick’s search tool, we’ve been able to locate some issues that we’re going to have to resolve if we don’t want to spend the next few years writing new Banjo and Fiddle classes (and who really wants to do that?).


INTERFACE - This code construct has the dual role of defining behavior that applies to multiple types, and also being the preferred focus of classes that use those types.

![](2022-02-04-23-02-26.png)

ENCAPSULATION - It’s been responsible for preventing more maintenance problems than any other OO principle in history, by localizing the changes required for the behavior of an object to vary.

![](2022-02-04-23-05-55.png)


Change - Every class should attempt to make sure that it has only one reason to do this, the death of many a badly designed piece of software.

![](2022-02-04-23-12-39.png)

![](2022-02-04-23-18-48.png)


### OO Principles

* Encapsulate what varies.
* Code to an interface rather than to an implementation.
* Each class in your application should have only one reason to change.

## 5 (part2) good desing = flexible software (Give Your Software a 30-minute Workout)

Ever wished you were just a bit more flexible?


When you run into problems making changes to your application, it probably means that your software needs to be more flexible and resilient. To help stretch your application out, you’re going to do some analysis, a whole lot of design, and learn how OO principles can really loosen up your application. And for the grand finale, you’ll see how higher cohesion can really help your coupling. Sound interesting? Turn the page, and let’s get back to fixing that inflexible application.

![](2022-02-04-23-25-20.png)

![](2022-02-04-23-37-13.png)

Let’s take what we’ve figured out about turning InstrumentSpec into a concrete class, and see if it makes the design of Inventory any better.

changing the InstrumentSpec from abstract class to normal class, in the inventory.class now we can use only one search method that we can use as parameter InstrumentSpec because it can be created or instanciated(because it is not abstract class)

The search() method isn’t the only thing that makes adding new instruments to Rick’s application difficult. You also have to add a new subclass of Instrument for each new instrument type. But why? Let’s do a little more analysis.

Why is there a need for an Instrument class in Rick’s application?

Most instruments have at least a few common properties, like serial number and price. Instrument stores the common properties, and then each specific instrument type can extend from Instrument.

What things are common to all instruments?

The serial number, the price, and some set of specifications (even though the details of those specs may be different for different instrument types).

What things are different between instruments?

The specifications: each type of instrument has a different set of properties that it can contain. And since each instrument has a different InstrumentSpec, each has a different constructor.

### A closer look at the instrument classes

![](2022-02-04-23-43-03.png)

![](2022-02-04-23-48-51.png)

![](2022-02-04-23-58-02.png)

Design is iterative... and you have to be willing to change your own designs, as well as those that you inherit from other programmers.

delete Guitar.java and Mnadolin.java classes, and don't create new classes of Instrument

Guitar.java

```java
package chapter5;

public class Guitar extends Instrument {
    
    public Guitar(String serialNumber, double price, GuitarSpec spec) {
        super(serialNumber, price, spec);
    }
}
```

instead of Instrument class, create enum of InstrumentType.java 
where the GUITAR, BANJO,MANDOLIN can be enums. we are eliminating classes because there is no different behavior in this system like type of playing etc. only have different attributes each instrument.

![](2022-02-05-00-13-21.png)

![](2022-02-05-00-13-32.png)


### Getting dynamic with instrument properties


using properties Map ds , now properties can have (instrumentTYpe, builder, model, tpe backwood, topwood, numstrings, style, ...) 
we can eliminate GuitarSpec or MnadolinSpec.java

```java
package chapter5;

public class GuitarSpec extends InstrumentSpec {
    
    private int numStrings;

    public GuitarSpec(Builder builder, String model, Type type, Wood backWood, Wood topWood, int numStrings) {
        super(builder, model, type, backWood, topWood);
        this.numStrings = numStrings;
    }

    public int getNumStrings() {
        return numStrings;
    }

    // Override the superclass matches()
    public boolean matches(InstrumentSpec otherSpec) {
       
        if(!super.matches(otherSpec)) return false;

        if(!(otherSpec instanceof GuitarSpec)) return false;

        GuitarSpec spec = (GuitarSpec)otherSpec;

        if (numStrings != spec.numStrings) return false;
    
        return true;

    }

}
```

![](2022-02-05-00-26-19.png)

### Using the new Instrument and InstrumentSpec classes

![](2022-02-05-00-27-01.png)

![](2022-02-05-00-40-55.png)


create enum of InstrumentType.java 

change the Inventory.java -> addInstrument method is more simple now

### Behold: Rick’s flexible application


![](2022-02-05-00-49-05.png)


create FindInstrument.java to test the search

![](2022-02-05-01-13-37.png)

![](2022-02-05-02-19-45.png)

![](2022-02-05-02-19-57.png)

![](2022-02-05-02-20-10.png)

![](2022-02-05-02-20-24.png)

![](2022-02-05-02-20-33.png)

## 6 Solving really big problems

It’s time to build something REALLY BIG. Are you ready?

You’ve got a ton of tools in your OOA&D toolbox, but how do you use those tools when you have to build something really big? Well, you may not realize it, but you’ve got everything you need to handle big problems. We’ll learn about some new tools, like domain analysis and use case diagrams, but even these new tools are based on things you already know about—like listening to the customer and understanding what you’re going to build before you start writing code. Get ready... it’s time to start playing the architect.

![](2022-02-05-13-15-23.png)

### The things you already know...

By encapsulating what varies, you make your application more flexible, and easier to change.

Coding to an interface, rather than to an implementation, makes your software easier to extend.

The best way to get good requirements is to understand what a system is supposed to do

Great software is easy to change and extend, and does what the customer wants it to do.

Analysis helps you ensure your system works in a real-world context.

![](2022-02-05-13-19-24.png)

![](2022-02-05-13-23-20.png)

### Customer Conversation

![](2022-02-05-13-31-53.png)

### Figure out the features

You’ve learned a lot about what Gary and his team want the game system framework to do, so let’s take that information and figure out the features of the system.

Bethany: And we need all sorts of different time periods. 

Bethany said the game system should support different time periods. That’s a feature of the game syste

But what is a feature, anyway?
A feature is just a high-level description of something a system needs to do. You usually get features from talking to your customers (or listening in on their conversations, like we just did on the last few pages).

A lot of times, you can take one feature, and come up with several different requirements that you can use to satisfy that feature. So figuring out a system’s features is a great way to start to get a handle on your requirements.

Starting with the features of a system is really helpful in big projects-like Gary’s game system-when you don’t have tons of details, and just need to get a handle on where to start.

![](2022-02-05-13-35-35.png)

![](2022-02-05-13-40-35.png)

Use cases don’t always help you see the big picture.
When you start to write use cases, you’re really getting into a lot of detail about what the system should do. The problem
is that can cause you to lose sight of the big picture. In Gary’s game system, we’re really not ready for a lot of detail... we’re just trying to figure out what the framework  actually is at this point.

So even though you could start writing use cases, that probably won’t help you figure  out exactly what you’re trying to build, from the big-picture point of view. When you’re working on a system, it’s a good idea to defer details as long as you can... you won’t get caught up in the little things when you should be working on the big things.

You still need to know what your system is supposed to do... but you need a BIG-PICTURE view.

Even though use cases might be a little too focused on the details for where we are in designing the system right now, you still need to have a good understanding of what your system needs to do. So you need a way to focus on the big picture, and figure  out what your system should do, while still avoiding getting into too much detail.


### Use case diagrams


Use case diagrams are the blueprints for your system.

![](2022-02-05-13-50-46.png)

![](2022-02-05-14-02-09.png)

![](2022-02-05-14-08-41.png)

### Actors are people, too (well, not always)

![](2022-02-05-14-13-57.png)

![](2022-02-05-14-14-23.png)

Domain analysis lets you check your designs, and still speak the customer’s language

Jim: Sure... but when do we get to talk about what classes we need to write, and the packages we put those classes into?

Frank:  We’re getting to that, definitely. But the customer really  doesn’t understand what most of that stuff means... we’d never be sure we were building the right thing if we started talking about classes and variables.

Jim: What about class diagrams? We could use those to show what we’re going to code, couldn’t we?

Frank: Well, we could... but do you think the customer would understand that much better? That’s really what domain analysis is all about. We can talk to the customer about their system, in terms that they understand. For Gary, that means talking about units, and terrain, and tiles, instead of classes, objects, and methods.


### Let’s do a little domain analysis!

domain analysis. The process of identifying, collecting, organizing, and representing the relevant information of a domain, based upon the study of existing systems and their development histories, knowledge captured from domain experts, underlying theory, and emerging technology within a domain.

![](2022-02-05-14-19-37.png)

### What most people give the customer...
 
![](2022-02-05-14-23-32.png)

![](2022-02-05-14-29-39.png)

![](2022-02-05-14-30-12.png)

![](2022-02-05-14-37-35.png)


### big apps process

big apps -> gather features(general) and their requierements(more specific) (make feature list) -> defer details as long as you can. -> make use case diagrams (make use case diagram) -> domain analysis -> breaking system into modules -> apply design pattern(last step)

![](2022-02-05-14-42-11.png)


![](2022-02-05-14-47-32.png)


## 7 - architecture 

Bringing Order to Chaos

You have to start somewhere, but you better pick the right somewhere! You know how to break your application up into lots of small problems, but all that means is that you have LOTS of small problems. In this chapter, we’re going to help you figure out where to start, and make sure that you don’t waste any time working on the wrong things. It’s time to take all those little pieces laying around your workspace, and figure out how to turn them into a well-ordered, well-designed application. Along the way, you’ll learn about the all-important 3 Qs of architecture, and how Risk is a lot more than just a cool war game from the ‘80s.

### Feeling a little overwhelmed?

![](2022-02-12-19-54-20.png)

![](2022-02-12-20-00-06.png)

![](2022-02-12-20-34-01.png)

![](2022-02-12-20-34-14.png)

![](2022-02-12-20-34-26.png)

![](2022-02-12-20-34-35.png)

![](2022-02-12-20-35-05.png)

![](2022-02-12-20-36-35.png)

![](2022-02-12-20-36-46.png)

![](2022-02-12-20-36-56.png)

implement Board.class

Doesn't using an array of arrays limit you to a square board ?

* No, although it does limit you to a board that uses (x, y) coordinates. For example, you can use (x, y) coordinates in a board made up of hexagon-shaped tiles, if you structure the hexagon tiles correctly. But for the most part, an array of arrays is more ideally suited to a square-tiled, rectangular board.

So isn’t that limiting? Why not use a graph, or even a Coordinate class, so you’re not tied to (x, y) coordinates and a rectangular board ?

* If you wanted maximum flexibility, that might be a good idea. For this situation, though, our requirements (back on page 340) actually specified (x, y) coordinates. So we chose a solution that wasn’t quite as flexible, but certainly was simpler. Remember, at this stage, we’re trying to reduce risk, not increase it by going with a solution that is a lot more complex than we really need.

The Tile and Unit classes
To actually make Board compile and work, we need to create a Tile and Unit class. 

Keep the right focus

You don’t need to worry about everything that Tile and Unit will eventually need to do. Your focus is on making Board and its key features work, not on completing Tile or Unit. That’s why we left Unit so bare, and added only a few methods to Tile.

![](2022-02-12-21-57-02.png)

![](2022-02-12-21-57-14.png)

![](2022-02-12-21-57-24.png)

![](2022-02-12-21-57-47.png)

![](2022-02-12-21-57-57.png)

![](2022-02-12-21-58-07.png)

![](2022-02-12-21-58-16.png)

![](2022-02-12-21-58-24.png)

![](2022-02-12-21-58-33.png)

![](2022-02-12-21-58-42.png)

![](2022-02-12-21-58-51.png)

### Bullet points

* Architecture helps you turn all your diagrams, plans, and feature lists into a well-ordered application.
* The features in your system that are most important to the project are architecturally significant.
* Focus on features that are the essence of your system, that you’re unsure about the meaning of, or unclear about how to implement first.
* Everything you do in the architectural stages of a project should reduce the risks of your project failing.
* If you don't need all the detail of a use case, writing a scenario detailing how your software could be used can help you gather requirements quickly.
* When you're not sure what a feature is, you should ask the customer, and then try and generalize the answers you get into a good understanding of the feature.
* Use commonality analysis to build software solutions that are flexible.
* Customers are a lot more interested in software that does what they want, and comes in on time, than they are in code that you think is really cool.

## 8 design principles

Imitation is the sincerest form of not being stupid. There’s nothing as satisfying as coming up with a completely new and original solution to a problem that’s been troubling you for days—until you find out someone else solved the same problem, long before you did, and did an even better job than you did! In this chapter, we’re going to look at some design principles that people have come up with over the years, and how they can make you a better programmer. Lay aside your thoughts of “doing it your way”; this chapter is about doing it the smarter, faster way.

### Design principle roundup

### (O of SOLID) The Open Close Principle

![](2022-02-12-23-09-49.png)

![](2022-02-12-23-10-03.png)

![](2022-02-12-23-10-11.png)

What’s the big deal about modifying code in a base class, or a class that you’ve already written?

* Once you have a class that works, and is being used, you really don’t want to make changes to it unless you have to. But remember, CHANGE is the great constant in software development. With the OCP, we allow for change through extension, rather than having to go back and modify your existing code. Subclasses can add and extend the base class’s behavior, without messing around with code that you already know is working and making the customer happy.

Isn’t the OCP just another form of encapsulation?

* It’s really a combination of encapsulation and abstraction. You’re finding the behavior that stays the same, and abstracting that behavior away into a base class, and then locking that code up from modification. But then when you need new or different behavior, your subclasses handle the changes by extending the base class. That’s where encapsulation comes in: you’re encapsulating what varies (behavior in the subclasses) away from what stays the same (the common behavior in the base class).

So the only way to use the OCP is by extending another class?

* No, anytime your code is closed for modification but open for extension, you’re using the OCP. So for example, if you had several private methods in a class, those are closed for modification—no other code can mess with them. But then you could add several public methods that invoked those private methods in different ways. You’re extending the behavior of the private methods, without changing them. That’s another example of the OCP in action.


### The Don’t Repeat Yourself Principle (DRY)

![](2022-02-12-23-27-48.png)

![](2022-02-12-23-27-57.png)

![](2022-02-12-23-28-06.png)


### (S of SOLID) The Single Responsibility Principle

![](2022-02-12-23-47-04.png)

![](2022-02-12-23-47-19.png)

![](2022-02-12-23-47-32.png)

![](2022-02-12-23-47-49.png)

### (L of SOLID) The Liskov Substitution Principle (LSP)

* Inheritance
* Delegation
* Composition
* Aggregation

![](2022-02-13-00-24-48.png)

![](2022-02-13-00-25-00.png)

![](2022-02-13-00-25-11.png)

![](2022-02-13-00-25-22.png)

![](2022-02-13-00-25-36.png)

![](2022-02-13-00-25-54.png)

![](2022-02-13-00-26-08.png)


### Aggregation versus composition

It’s easy to get confused about when you should use composition, and when you should use aggregation. The easiest way to figure this out is to ask yourself, Does the object whose behavior I want to use exist outside of the object that uses its behavior?

If the object does make sense existing on its own, then you should use aggregation; if not, then go with composition. But be careful! Sometimes the slightest change in the usage of your objects can make all the difference.


![](2022-02-13-00-37-19.png)

![](2022-02-13-00-42-27.png)

![](2022-02-13-00-42-39.png)

### BULLET POINTS

* The Open-Closed Principle keeps your software reusable, but still flexible, by keeping classes open for extension, but closed for modification.
* With classes doing one single thing through the Single Responsibility Principle, it’s even easier to apply the OCP to your code.
* When you’re trying to determine if a method is
the responsibility of a class, ask yourself, Is it this class’s job to do this particular thing? If not, move the method to another class.
* Once you have your OO code nearly complete, be sure that you Don’t Repeat Yourself. You’ll avoid duplicate code, and ensure that each behavior in your code is in a single place.
* DRY applies to requirements as well as your code: you should have each feature and requirement in your software implemented in a single place.
* The Liskov Substitution Principle ensures that you use inheritance correctly, by requiring that subtypes be substitutable for their base types.
* When you find code that violates the LSP, consider using delegation, composition, or aggregation to use behavior from other classes without resorting to inheritance.
* If you need behavior from another class but don’t need to change or modify that behavior, you can simply delegate to that class to use the desired behavior.
* Composition lets you choose a behavior from a family of behaviors, often via several implementations of an interface.
* When you use composition, the composing object owns the behaviors it uses, and they stop existing as soon as the composing object does.
* Aggregation allows you to use behaviors from another class without limiting the lifetime to those behaviors.
* Aggregated behaviors continue to exist even after the aggregating object is destroyed.

## 9 iterating and testing

It’s time to show the customer how much you really care. Nagging bosses? Worried clients? Stakeholders that keep asking, “Will it be done on time?” No amount of well-designed code will please your customers; you’ve got to show them something working. And now that you’ve got a solid OO programming toolkit, it’s time to learn how you can prove to the customer that your software works. In this chapter, we learn about two ways to dive deeper into your software’s functionality, and give the customer that warm feeling in their chest that makes them say, Yes, you’re definitely the right developer for this job!

### Your toolbox is filling up

![](2022-02-13-16-10-45.png)

![](2022-02-13-16-10-53.png)

![](2022-02-13-16-11-03.png)

![](2022-02-13-16-11-12.png)

![](2022-02-13-16-11-21.png)

![](2022-02-13-16-11-33.png)

![](2022-02-13-16-11-46.png)

![](2022-02-13-16-11-56.png)

![](2022-02-13-16-12-08.png)

![](2022-02-13-16-12-16.png)

![](2022-02-13-16-12-27.png)

![](2022-02-13-16-12-36.png)

![](2022-02-13-16-12-46.png)

![](2022-02-13-16-12-56.png)


### Let's write the Unit Class

Create Unit.java and Weapon.java

### Test Cases Dissected...

![](2022-02-13-17-24-34.png)

![](2022-02-13-17-24-45.png)

create UnitTester.java

![](2022-02-13-17-37-35.png)

![](2022-02-13-18-06-41.png)

![](2022-02-13-18-06-50.png)

![](2022-02-13-18-06-59.png)

![](2022-02-13-18-07-08.png)


Change the Unit.java and UnitTester.java to throw exception instead return null

![](2022-02-13-18-21-51.png)

![](2022-02-13-18-26-20.png)


The problem:
Gary’s framework needs to support groups of units.

Your task:
1. Create a new class that can group units together, and both add and remove units to the group.

2. Fill out the table below with test case scenarios that will test your software, and prove to Gary that the grouping of units works.

3. Add methods to UnitTester to implement the test scenarios in the table, and make sure all your tests pass.

### Bullet points

* The first step in writing good software is to make sure your application works like the customer expects and wants it to.
* Customers don’t usually care about diagrams and lists; they want to see your software actually do something.
* Use case driven development focuses on one scenario in a use case in your application at a time.
* In use case driven development, you focus on a single scenario at a time, but you also usually code all the scenarios in a single use case before moving on to any other scenarios, in other use cases.
* Feature driven development allows you to code a complete feature before moving on to anything else.
* You can choose to work on either big or small features in feature-driven development, as long as you take each feature one at a time.
* Software development is always iterative. You look at the big picture, and then iterate down to smaller pieces of functionality.
* You have to do analysis and design at each step of your development cycle, including when you start working on a new feature or use case.
* Tests allow you to make sure your software doesn’t have any bugs, and let you prove to your customer that your software works.
* A good test case only tests one specific piece of functionality.
* Test cases may involve only one, or several, methods in a single class, or may involve multiple classes.
* Test driven development is based on the idea that you write your tests first, and then develop software that passes those tests. The result is fully functional, working software.
* Programming by contract assumes both sides in a transaction understand what actions generate what behavior, and will abide by that contract.
* Methods usually return null or unchecked exceptions when errors occur in programming by contract environments.
* Defensive programming looks for things to go wrong, and tests extensively to avoid problem situations.
* Methods usually return “empty” objects or throw checked exceptions in defensive programming environments.


Create UnitGroup.java

![](2022-02-13-18-36-31.png)

![](2022-02-13-18-36-40.png)


## 10 the ooa&d lifecycle

Are we there yet? We’ve been working on lots of individual ways to improve your software, but now it’s time to put it all together. This is it, what you’ve been waiting for: we’re going to take everything you’ve been learning, and show you how it’s all really part of a single process that you can use over and over again to write great software.

### Developing software, OOA&D style

![](2022-02-16-21-56-12.png)

![](2022-02-16-21-56-37.png)

![](2022-02-16-21-56-44.png)

![](2022-02-16-21-56-54.png)

![](2022-02-16-21-57-12.png)

![](2022-02-16-21-57-21.png)

![](2022-02-16-21-57-31.png)

![](2022-02-16-21-57-38.png)

![](2022-02-16-21-57-45.png)

![](2022-02-16-21-58-00.png)

![](2022-02-16-21-58-08.png)

![](2022-02-16-21-58-16.png)

![](2022-02-16-21-58-27.png)

![](2022-02-16-21-58-38.png)


### Code the station class

We’ve got requirements in the form of a use case, a class diagram, and we know the Station class will fit into our Subway model. Now we’re ready to start writing code:

create Station.java

The Java specification recommends that if two objects are equal, they should have the same hash code. So if you’re deciding on equality based on a property, it’s a good idea to also override hashCode() and return a hash code based on that same property. This is particularly important if you’re using your object in a Hashtable or HashMap, which both make heavy use of the hashCode() method.

create Connection.java

create Subway.java

![](2022-02-16-22-46-48.png)

![](2022-02-16-22-47-00.png)

The SubwayLoader class

We’re almost done with our first iteration, and our first use case. All that’s left is to code the class that loads a subway based on the test file we got from Objectville Travel, Inc.

create SubwayLoader.java

![](2022-02-16-23-29-45.png)

![](2022-02-16-23-29-58.png)

![](2022-02-16-23-31-32.png)


### What’s left to do?

![](2022-02-18-12-57-45.png)

![](2022-02-18-12-58-00.png)

![](2022-02-19-00-42-54.png)

![](2022-02-19-00-43-10.png)

![](2022-02-19-00-47-20.png)

Update subway.java code

![](2022-02-19-01-25-50.png)

![](2022-02-19-01-29-42.png)

Implement SubwayPrinter.java and SubwayTester.java


### OOAD TimeLine

Feature List

* Talk to the customer
* Key Feature List

Use case Diagrams

* EXTERNAL INITIATOR

Break Up The problem

* Architecture
* Analysis
* Commonality
* Delegation
* Variability

Requirements

* External Initiator
* Scenario
* Alternate Path
* Iteration
* Rquirements Lists

Domain Analysis

* Key Feture List
* Talk to the customer
* Textual Analysis
* Architecture

Preliminary Design

* Encapsulation
* Design Pattern
* Class Diagram
* Design Principles
* Cohesion

Implementation

* OO Principles
* Encapsulation
* Design Pattern
* Feature Driven Development
* Test Driven Development
* Test Scenario
* Design Principles

![](2022-02-19-02-30-40.png)

### The journey’s not over...

Now take OOA&D for a spin on your own projects!

We’ve loved having you here in Objectville, and we’re sad to see you go. But there’s nothing like taking what you’ve learned and putting it to use on your own development projects. So don’t stop enjoying OOA&D just yet... you’ve still got a few more gems in the back of the book, an index to read through, and then it’s time to take all these new ideas and put them into practice. 

## appendix i: leftovers


The Top Ten Topics (we didn’t cover)

#1. IS-A and HAS-A

![](2022-02-19-03-13-38.png)

![](2022-02-19-03-13-48.png)

![](2022-02-19-03-13-56.png)

![](2022-02-19-03-14-15.png)

![](2022-02-19-03-14-25.png)

![](2022-02-19-03-14-36.png)

![](2022-02-19-03-14-44.png)

![](2022-02-19-03-14-53.png)

![](2022-02-19-03-15-03.png)

## Appendix ii: welcome to objectville

Get ready to take a trip to a foreign country. It’s time to visit Objectville, a land where objects do just what they’re supposed
to, applications are all well-encapsulated (you’ll find out exactly what that means shortly), and designs are easy to reuse and extend. But before we can get going, there are a couple of things you need to know first, and a few language skills you’re going to have to learn. Don’t worry, though, it won’t take long, and before you know it, you’ll be speaking the language of OO like you’ve been living in the well-designed areas of Objectville for years.

![](2022-02-19-03-37-43.png)

![](2022-02-19-03-37-50.png)

![](2022-02-19-03-37-59.png)

![](2022-02-19-03-38-07.png)

![](2022-02-19-03-38-17.png)

### Bullet Points

* UML stands for the Unified Modeling Language.
* UML helps you communicate the structure of your application to other developers, customers, and managers.
* A class diagram gives you an overview of your class, including its methods and variables.
* Inheritance is when one class extends another class to reuse or build upon the inherited class’s behavior.
* In inheritance, the class being inherited from is called the superclass; the class that is doing the inheritance is called the subclass.
* A subclass gets all the behavior of its superclass automatically.
* A subclass can override its superclass’s behavior to change how a method works.
* Polymorphism is when a subclass “stands in" for its superclass.
* Polymorphism allows your applications to be more flexible, and less resistant to change.
* Encapsulation is when you separate or hide one part of your code from the rest of your code.
* The simplest form of encapsulation is when you make the variables of your classes private, and only expose that data through methods on the class.
* You can also encapsulate groups of data, or even behavior, to control how they are accessed.