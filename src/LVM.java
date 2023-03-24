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

    public String logic(String command) { // temp name change later
        if (command.contains("install-drive")) {
            String[] info = command.split(" ");
            if (findDrive(info[1]) != null) {
                return "Drive with the same name already is installed";
            } else {
                physicalDrives.add(new PhysicalDrive(info[1], Integer.parseInt(info[2].substring(0, info[2].indexOf("G")))));
                return "Drive " + info[1] + " installed";
            }
        }
        if (command.equals("list-drives")) {
            return "" + listPhysicalDrives();
        }
        if (command.contains("pvcreate")) {
            String[] info = command.split(" ");
            if (findDrive(info[2]) == null) {
                return "Physical drive \"" + info[2] + " \"does not exist in the system.";
            } else if (findPV(info[1]) != null) {
                return "Physical volume with the same name already is installed";
            } else if (VGs.size() != 0) {
            } else {
                PVs.add(new PV(info[1], findDrive(info[2])));
                return info[1] + " created";
            }
        }
        if (command.equals("pvlist")) {
            return "" + listPVsByVG();
        }
        if (command.contains("vgcreate")) {
            String[] info = command.split(" ");
            if (findDrive(info[2]) == null) {
                return "Volume group \"" + info[2] + " \"does not exist in the system.";
            } else if (VGs.contains(findPV(info[1]))) {

                VGs.add(new VG(info[1], findPV(info[2])));
                return info[1] + " created";
            }
        }
        return "Not valid command";
    }

    public String listPVsByVG() {
        if (VGs.size() == 0) {
            String PVsInfo = "";
            for (PV PV : PVs) {
                PVsInfo += PV.toString() + "\n";
            }
            return PVsInfo;
        } else {
            String PVsInfo = "";
            for (VG VG : VGs) {
                for (PV PV : VG.getPVs()) {
                    PVsInfo += PV.toString() + "\n";
                }
            }
            return PVsInfo;
        }
    }

    public boolean VGcontainsPV(PV PV) {
        for (VG VG : VGs) {
            if (VG.getPVs().contains(PV)) {
                return true;
            }
        }
        return false;
    }

    public PhysicalDrive findDrive(String name) {
        for (PhysicalDrive drive : physicalDrives) {
            if (drive.getName().equals(name)) {
                return drive;
            }
        }
        return null;
    }

    public VG findVG(String name) {
        for (VG drive : VGs) {
            if (drive.getName().equals(name)) {
                return drive;
            }
        }
        return null;
    }

    public PV findPV(String name) {
        for (PV drive : PVs) {
            if (drive.getName().equals(name)) {
                return drive;
            }
        }
        return null;
    }

    public LV findLV(String name) {
        for (LV drive : LVs) {
            if (drive.getName().equals(name)) {
                return drive;
            }
        }
        return null;
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
