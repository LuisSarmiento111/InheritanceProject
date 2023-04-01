import java.util.ArrayList;

public class VG extends PhysicalDrive {

    private ArrayList<PV> PVs;
    private int extraSpace;
    private ArrayList<LV> LVs;

    public VG(String n, PV PV) {
        super(n, PV.getSize());
        PVs = new ArrayList<PV>();
        LVs = new ArrayList<LV>();
        PVs.add(PV);
    }


    public boolean setExtraSpace(int spaceUsed) {
        if (extraSpace - spaceUsed <= 0) {
            return false;
        } else {
            extraSpace -= spaceUsed;
            return true;
        }
    }

    public void addLV(LV LV) {
        LVs.add(LV);
        setExtraSpace(super.getSize() - getLVStorage());
    }

    public int getLVStorage() {
        int storage = 0;
        for (LV LV : LVs) {
            storage += LV.getSize();
        }
        return storage;
    }

    public void addPV(PV PV) {
        PVs.add(PV);
        setSize(PV.getSize() + getSize());
        PV.setVG(this);
        setExtraSpace(super.getSize() - getLVStorage());
    }

    public ArrayList<PV> getPVs() {
        return PVs;
    }

    public int getExtraSpace() {
        return extraSpace;
    }

    public ArrayList<LV> getLVs() {
        return LVs;
    }
}
