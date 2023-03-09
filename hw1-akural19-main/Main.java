
public class Main {

    public static void main(String[] args) {

        Playlist p1 = new Playlist();
        Playlist p2 = new Playlist();

        Song s1 = new Song("One" , "U2");
        Song s2 = new Song("Two Of Us" , "The Beatles");
        Song s3 = new Song("Three Little Birds" , "Bob Marley");
        Song s4 = new Song("The Four Horsemen" , "Metallica");
        Song s5 = new Song("505" , "Arctic Monkeys");
        Song s6 = new Song("Six Feet Under" , "The Weeknd");
        Song s7 = new Song("Song to Say Goodbye","Placebo");

        p1.displayList();
        System.out.println("------------------------");
        p1.insertToEnd(s1);
        p1.insertToEnd(s2);
        p1.insertToEnd(s3);
        p1.insertToEnd(s4);
        p1.displayList();
        System.out.println("------------------------");
        p1.insertToIndex(s5,1);
        p1.insertToIndex(s6,5);
        p1.displayList();
        System.out.println("------------------------");
        p1.removeSong("Two Of Us");
        p1.removeSong("Not a song in our playlist...");
        p1.removeSong("The Four Horsemen");
        p1.removeSong("Six Feet Under");
        p1.displayList();
        System.out.println("------------------------");
        p1.insertToEnd(s2);
        p1.insertToEnd(s6);
        p1.displayList();
        System.out.println("------------------------");
        p1.move("Two Of Us",2);
        p1.move("One",2);
        p1.move("Three Little Birds",8);
        p1.move("Three Little Birds",1);
        p1.displayList();
        System.out.println("------------------------");
        p1.reverseSequence(1,4);
        p1.displayList();
        System.out.println("------------------------");
        p1.reverseSequence(4,5);
        p1.displayList();
        System.out.println("------------------------");


        // A different playlist
        p2.insertToEnd(s7);
        p2.displayList();
        System.out.println("------------------------");
        p2.removeSong("Song to Say Goodbye");
        p2.displayList();
        System.out.println("------------------------");
        p2.insertToIndex(s7,1);
        p2.displayList();

    }
}