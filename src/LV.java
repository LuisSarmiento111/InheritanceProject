public class LV extends PhysicalDrive {

    private VG associatedVG;

    public LV (String n, int s, VG vg) {
        super(n, s);
        associatedVG = vg;
        vg.setExtraSpace(s);
        vg.addLV(this);
    }
}
