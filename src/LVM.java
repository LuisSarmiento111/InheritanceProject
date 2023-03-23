import java.util.ArrayList;

public class LVM {

    private ArrayList<PhysicalDrive> physicalDrives;
    private ArrayList<PV> PVs;
    private ArrayList<VG> VGs;
    private ArrayList<LV> LVs;

    public LVM() {
        physicalDrives = new ArrayList<PhysicalDrive>();
        PVs = new ArrayList<PV>();
        VGs = new ArrayList<VG>();
        LVs = new ArrayList<LV>();

    }

    public String logic (String command) { // temp name change later
        if (command.contains("install-drive")) {
            String[] test = command.split(" ");
            physicalDrives.add(new PhysicalDrive(test[1], Integer.parseInt(test[2].substring(0, test[2].indexOf("G")))));
            return "Drive " + physicalDrives.get(physicalDrives.size() - 1).getName() + " installed";
        } else if (command.equals("list-drives")
        ) {
            return "" + listPhysicalDrives();
        } else {
            return "Not valid command";
        }
    }

    public void sortBySize() {

    }

    public String listPhysicalDrives() {
        String drivesInfo = "";
        for (PhysicalDrive physicalDrive : physicalDrives) {
            drivesInfo += physicalDrive.toString() + "\n";
        }
        return drivesInfo;
    }

    public ArrayList<PV> getPVs() {
        return PVs;
    }

    public ArrayList<VG> getVGs() {
        return VGs;
    }

    public ArrayList<LV> getLVs() {
        return LVs;
    }
}
