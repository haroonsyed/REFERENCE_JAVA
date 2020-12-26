package me.Sshawarma.DesignPatterns;

//Decouples abstraction from implementation so the two can vary independently.
//Add functionality while separating major differences using interfaces/classes
//EX) Different web views (mobile vs desktop vs light vs dark etc) paired with different medias (song, movie, book)
//    You want to show the an image with description for each. While varying view and media independently.
//This also avoids doing duplicate things structurally with the same objects

//Abstraction
abstract class View {

    IMedia media;

    public View(IMedia media) {
        this.media = media;
    }

    public abstract String display();

}

//Refined Abstractions
class LongFormView extends View {

    public LongFormView(IMedia media) {
        super(media);
    }

    @Override
    public String display() {
        return "\nThis is a long view of media: \n" + media.about() + "\n" + media.image();
    }
    //Other custom behavior can be put here or whatever. The key point is the main interface is implemented.
    //This allows the interfaces and implementations to vary independently.

}
class ShortFormView extends View {

    public ShortFormView(IMedia media) {
        super(media);
    }

    @Override
    public String display() {
        return "\nThis is a short view of media: \n" + media.image();
    }

}

//Implementor
interface IMedia {

    public String about();
    public String image();

}

//Concrete Implementations
class Song implements IMedia {

    String artist;

    public Song(String artist) {
        this.artist = artist;
    }

    @Override
    public String about() {
        return "Song by: " + artist;
    }

    @Override
    public String image() {
        return "Image of " + artist;
    }

    //Custom behavior specific to Song
    public void playSong() {

    }
    public void increaseVolume() {

    }
    public void decreaseVolume() {

    }
    public void stopSong() {

    }

}
class Book implements IMedia {

    String author;

    public Book(String author) {
        this.author = author;
    }

    @Override
    public String about() {
        return "Book called [title] by: " + author;
    }

    @Override
    public String image() {
        return "Image of " + author;
    }

    //Custom behavior specific to Song
    public void playAudioBook() {

    }
    public void tearUpBook() {

    }
    public void contactAuthor() {

    }
    public void placeBookmark() {

    }

    //You could even have custom dependencies or whatever in here, the resource just needs the interface methods

}



public class BridgePattern {

    public BridgePattern() {

        //Now you can combine the views and the resources in whatever way you want.
        //No need for cartesian product number of classes.
        //Long view with Song
        View longWithSong = new LongFormView(new Song("Bob Marley"));
        System.out.println(longWithSong.display());
        //Short with Book
        View shortWithBook = new ShortFormView(new Book("Christopher Paolini"));
        System.out.println(shortWithBook.display());
        //Long view with Book
        View longWithBook = new LongFormView(new Book("Tolkein"));
        System.out.println(longWithBook.display());

    }

}
