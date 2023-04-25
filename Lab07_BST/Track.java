/**
 *
 * @author larson amy c
 *
 * https://www.kaggle.com/datasets/leonardopena/top50spotify2019
 */


/*creating a comparable interface class */
public class Track implements Comparable<Track> {

  
  /** Title of the track */
  String title;

  /** Band/person performing song */
  String artist;

  /** Genre of music */
  String genre;

  /** Danceability - the higher the number the better */
  Integer danceability;

  /** How loud the song is */
  Integer decibels;

  /** Popularity on Spotify - the higher, the more popular */
  Integer popularity;

  /** Count instantiations to generate unique id **/
  private static Integer trackCount = 0;

  /** Unique ID per track. Set by instantiation **/
  private Integer id;


  /**
  * Provide id based on instantiations.
  *
  * @return current trackCount (then increment count)
  */
  public static Integer getID() {
    return trackCount++;
  }

  /**
  * Constructor, based on string read from csv file.
  *
  * @param line String of comma-separated values (single string).
  */
  public Track(String trackLine) {
    id = getID();

    String[] values = trackLine.split(",");
    title = values[0];
    artist = values[1];
    genre = values[2];

    danceability =Integer.valueOf(values[3]); /*converting String to int */
    decibels = Integer.valueOf(values[4]);  /*converting String to int */
    popularity = Integer.valueOf(values[5]); /*converting String to int */
  }

  @Override
  public String toString() {
    String toPrint = id+": ";
    toPrint += title +" by "+artist+" ("+genre+"). ";
    toPrint += decibels+" dB " + ", danceable ("+danceability+"), ";
    toPrint += "ranked "+popularity;
    return toPrint;
  }

  @Override 
  public boolean equals(Object other) {
    return false;
  }

  @Override
  public int compareTo(Track other) {
    return 0;
  }

  // all setters and getters from here ------------------------------------

  public String title() {
    return title;
  }
  public void track(String t) {
    title = t;
  }

  public String genre() {
    return genre;
  }
  public void genre(String g) {
    genre = g;
  }

  public String artist() {
    return artist;
  }
  public void artist(String a) {
    artist = a;
  }

  public Integer danceability() {
    return danceability;
  }
  public void danceability(Integer d) {
    danceability = d;
  }

  public Integer decibels() {
    return decibels;
  }
  public void decibels(Integer d) {
    decibels = d;
  }

  public Integer popularity() {
    return popularity;
  }
  public void popularity(Integer d) {
    popularity = d;
  }

  public Integer id() {
    return id;
  }

}
