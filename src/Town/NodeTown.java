package Town;

public class NodeTown {
    private int id;
    private int longitude;
    private int latitude;

    public NodeTown() {
        this.id=0;
        this.latitude=0;
        this.longitude=0;
    }

    public int getId() {
        return id;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "longitude=" + longitude + ", latitude=" + latitude;
    }
}
