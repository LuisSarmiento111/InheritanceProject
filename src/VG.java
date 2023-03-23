import java.util.ArrayList;

public class VG extends PhysicalDrive {

    private ArrayList<PV> PVs;
    private int extraSpace;
    private ArrayList<LV> LVs;

    public VG (String n, PV PV) {
        super(n,PV.getSize());
        PVs.add(PV);
    }

    public boolean addStorage(PV newPV) {
        if (!PVs.contains(newPV)) {
            setSize(newPV.getSize() + getSize());
            newPV.setVG(this);
            PVs.add(newPV);
            return true;
        } else {
            return false;
        }
    }

    public boolean setExtraSpace (int spaceUsed) {
        if (extraSpace - spaceUsed <= 0) {
            return false;
        } else {
            extraSpace -= spaceUsed;
            return true;
        }
    }

    public void addLV(LV LV) {
        LVs.add(LV);
    }
}
