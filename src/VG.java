import java.util.ArrayList;

public class VG extends PhysicalDrive {

    private ArrayList<PV> PVs;
    private int extraSpace;
    private ArrayList<LV> LVs;

    public VG (String n, PV PV) {
        super(n,PV.getSize());
        PVs.add(PV);
    }

    public void addStorage(PV newPV) {
        setSize(newPV.getSize() + getSize());
        PVs.add(newPV);
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
