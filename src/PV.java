public class PV extends PhysicalDrive{

    private PhysicalDrive connectedDrive;


    public PV (String n, PhysicalDrive drive) {
        super(n, drive.getSize());
        connectedDrive = drive;
    }
}
