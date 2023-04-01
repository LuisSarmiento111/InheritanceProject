public class PV extends PhysicalDrive{

    private PhysicalDrive connectedDrive;
    private VG volumeGroup;


    public PV (String n, PhysicalDrive drive) {
        super(n, drive.getSize());
        connectedDrive = drive;
        volumeGroup = null;
    }

    public void setVG(VG VG) {
        volumeGroup = VG;
    }

    public PhysicalDrive getConnectedDrive() {
        return connectedDrive;
    }

    public VG getVolumeGroup() {
        return volumeGroup;
    }

    public String toString() {
        if (volumeGroup == null) {
            return super.toString();
        }
        return super.getName() + " [" + super.getSize() + "G]" + "[" + getVolumeGroup() + "]" + "[" + super.getId() + "]";
    }


}
