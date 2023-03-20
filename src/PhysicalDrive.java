import java.util.UUID;

public class PhysicalDrive {

    private String name;
    private int size;
    private UUID id;

    public PhysicalDrive (String n, int s) {
        name = n;
        size = s;
        id = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public UUID getId() {
        return id;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
