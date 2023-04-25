import java.util.*;

public class Order {

    public static class byArtist implements Comparator<Track> {

        @Override
        public int compare(Track track1, Track track2) {
           return track1.artist().compareTo(track2.artist());
        }
    }

    // TODO: finish this one
    public static class byTitle implements Comparator<Track> {

        @Override
        public int compare(Track track1, Track track2) {
            return track1.title().compareTo(track2.title());
        }
    }

    public static class byDanceability implements Comparator<Track> {

        @Override
        public int compare(Track track1, Track track2) {
            if(track1.danceability() !=track2.danceability()){
                return track1.danceability().compareTo(track2.danceability());
            }else{
                return track1.title().compareTo(track2.title()); 
            }
        }
    }
}