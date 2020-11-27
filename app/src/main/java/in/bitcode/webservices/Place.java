package in.bitcode.webservices;

public class Place {
    public String name, url, vicinity;
    public double rating, lat, lng;

    public Place(String name, String url, String vicinity, double rating, double lat, double lng) {
        this.name = name;
        this.url = url;
        this.vicinity = vicinity;
        this.rating = rating;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Place { " +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", vicinity='" + vicinity + '\'' +
                ", rating=" + rating +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
