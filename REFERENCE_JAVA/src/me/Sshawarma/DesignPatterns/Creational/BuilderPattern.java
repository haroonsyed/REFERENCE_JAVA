package me.Sshawarma.DesignPatterns.Creational;

//Sequentially build an object from parts. Different from factory in that factory refers to families of objects and
//factory build objects in one step. In this example we build a robot using parts as specified in builder pattern.
//This helps to avoid large constructor parameter lists or have optional parameters or even construct immutable objects.


//Showing the problem we are trying to solve:
/*class Robot {

    private String head;
    private String torso;
    private String arms;
    private String legs;
    //Lets make the following optional..
    private String speakers;
    private String camera;

    public Robot(String head, String torso, String arms..... Very Confusing/Annoying) {

    }

    //Using a bunch of setters is also unwieldy, especially when you want to construct the object in (seemingly) one go.

}*/

//Solution:
class Robot {

    //lets make it immutable for thread safety as well!
    private final String head;
    private final String torso;
    private final String arms;
    private final String legs;
    //Lets make the following optional..
    private final String speakers;
    private final String camera;

    public Robot(RobotBuilder builder) {
        //Set all the attributes form the builder, has access to private variable because inner class
        this.head = builder.head;
        this.torso = builder.torso;;
        this.arms = builder.arms;
        this.legs = builder.legs;
        this.speakers = builder.speakers;
        this.camera = builder.camera;
    }

    public void robotInfo() {
        System.out.println("The robot has ...\nHead: " + head + "\nTorso: " + torso + "\nArms: " + arms + "\nLegs: " + legs
                            + "\nSpeakers: " + speakers + "\nCamera: " + camera);
    }

    //Inner private class. Must be static so it can be accessed without instantiating a robot.
    //This way we force the use of the builder
    static class RobotBuilder {

        private String head;
        private String torso;
        private String arms;
        private String legs;
        //Lets make the following optional..
        private String speakers;
        private String camera;

        public RobotBuilder() {
            //Nothing here in this example, but can be used for checks before building the object
        }

        public Robot build() {
            return new Robot(this);
        }

        //The setter methods return a builder so that you can chain setters
        //Let's make them very easy to read though!
        public RobotBuilder head(String head) {
            this.head = head;
            return this;
        }
        public RobotBuilder torso(String torso) {
            this.torso = torso;
            return this;
        }
        public RobotBuilder arms(String arms) {
            this.arms = arms;
            return this;
        }
        public RobotBuilder legs(String legs) {
            this.legs = legs;
            return this;
        }
        public RobotBuilder speakers(String speakers) {
            this.speakers = speakers;
            return this;
        }
        public RobotBuilder camera(String camera) {
            this.camera = camera;
            return this;
        }

    }


}






public class BuilderPattern {

    public BuilderPattern() {

        //Now building an object is super understandable
        Robot robot = new Robot.RobotBuilder()
                            .head("Steve Head")
                            .arms("Anakin and Luke arms")
                            .torso("Kylo torso")
                            .legs("Jar Jar legs")
                            .build();
        //Note how the camera and speaker became optional
        robot.robotInfo();

    }

}
