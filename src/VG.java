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
        PV.setVG(this);
        extraSpace = PV.getSize();
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
        super.setSize(PV.getSize() + super.getSize());
        setExtraSpace(extraSpace + PV.getSize());
        PV.setVG(this);
        setExtraSpace(super.getSize() - getLVStorage());
    }

    public ArrayList<PV> getPVs() {
        return PVs;
    }

    public int getExtraSpace() {
        return extraSpace;
    }

    public String toString() {
        String listPV = PVs.get(0).getName();
        for (int i = 1; i < PVs.size(); i++) {
            listPV += ", " + PVs.get(i).getName();
        }
        return super.getName() + ": total: [" + super.getSize() + "G] available: [" + extraSpace + "G] [" + listPV + "] ["
                + super.getId() + "]";
    }
}
