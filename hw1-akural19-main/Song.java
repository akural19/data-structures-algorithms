
public class Song {

    private String name;
    private String artist;
    /**
     * Contructor for the Song class.
     * @param name name of the song
     * @param artist of the song
     */
    public Song(String name, String artist){
        this.name = name;
        this.artist = artist;
    }

    /**
     * Displaying info for current song
     */
    public void displaySong (){
        System.out.println(String.format ( "%25s %20s",name, artist));
    }
    /**
     * @return name of the song
     */
    public String getName(){
        return name;
    }

    /**
     * @return artist of the song
     */
    public String getArtist(){
        return artist;
    }

    /**
     * Setting a new name for this song
     * @param newName updated name of the song
     */
    public void setName(String newName){
        this.name = newName;
    }

    /**
     * Setting a new artist for this song
     * @param newArtist updated artist of the song
     */
    public void setArtist(String newArtist){
        this.artist = newArtist;
    }


}
