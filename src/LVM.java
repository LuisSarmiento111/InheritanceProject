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

    public String logic(String command) {
        if (command.contains("install-drive")) {
            String[] info = command.split(" ");
            if (findDrive(info[1]) != null) { // Physical Drive
                return "Drive with the same name already is installed";
            } else {
                physicalDrives.add(new PhysicalDrive(info[1], Integer.parseInt(info[2].substring(0, info[2].indexOf("G")))));
                return "Drive " + info[1] + " installed";
            }
        }
        if (command.equals("list-drives")) {
            return "" + listPhysicalDrives();
        }
        if (command.contains("pvcreate")) { // Physical Volume
            String[] info = command.split(" ");
            if (info.length > 1) {
                if (findDrive(info[2]) == null) {
                    return "Physical drive \"" + info[2] + " \"does not exist in the system.";
                } else if (findPV(info[1]) != null) {
                    return "Physical volume with the same name already is installed";
                } else if (PVscontainsPhysical(findDrive(info[2]))) {
                    return info[2] + " is already in connected to another physical volume";
                } else {
                    PVs.add(new PV(info[1], findDrive(info[2])));
                    return info[1] + " created";
                }
            }
        }
        if (command.equals("pvlist")) {
            return "" + listPVsByVG();
        }
        if (command.contains("vgcreate")) { // Volume Group
            String[] info = command.split(" ");
            if (findPV(info[2]) == null) {
                return "Physical volume \"" + info[2] + "\"does not exist in the system.";
            }  else if (findVG(info[1]) != null) {
                return "Volume Group with the same name already is installed";
            }  else if (VGscontainsPV(findPV(info[2]))) {
                return info[2] + " is already in another volume group";
            } else {
                System.out.println(findPV(info[2]));
                VGs.add(new VG(info[1], findPV(info[2])));
                return info[1] + " created";
            }
        }
        if (command.contains("vgextend")) {
            String[] info = command.split(" ");
            if (findVG(info[1]) == null) {
                return "There are no volume groups in this system";
            } else if (findPV(info[2]) == null) {
                return "Physical volume \"" + info[2] + "\"does not exist in the system.";
            } else if (findVG(info[1]).getPVs().contains(findPV(info[2]))) {
                return info[1] + " already contains " + info[2];
            }  else if (VGscontainsPV(findPV(info[2]))) {
                return info[2] + " is already in another volume group";
            } else {
                if (findVG(info[1]).getPVs().contains(findPV(info[2]))) {
                    return "Volume Group already has physical volume with name " + info[2] + " installed.";
                } else {
                    findVG(info[1]).addPV(findPV(info[2]));
                    return info[2] + " added to " + info[1];
                }
            }
        }
        if (command.equals("vglist")) {
            return "" + listVGS();
        }
        if (command.contains("lvcreate")) { // Logical Volume
            String[] info = command.split(" ");
            if (findVG(info[3]) == null) {
                return "Volume group \"" + info[3] + "\"does not exist in the system.";
            } else if (findLV(info[1]) != null) {
                return "Logical volume with the same name already is installed";
            } else if (findVG(info[3]).getExtraSpace() < findLV(info[1]).getSize()) {
                return "Cannot create " + info[1] + " because there is not enough space in " + info[3];
            } else {
                System.out.println(findPV(info[2]));
                LVs.add(new LV(info[1], Integer.parseInt(info[2]), findVG(info[3])));
                return info[1] + " created";
            }
        }
        if (command.equals("lvlist")) {
            return "" + listLVs();
        }
        return "Not valid command";
    }

    public String listPVsByVG() {
        String PVsInfo = "";
        if (VGs.size() == 0) {
            for (PV PV : PVs) {
                PVsInfo += PV.toString() + "\n";
            }
        } else {
            for (VG VG : VGs) {
                for (PV PV : VG.getPVs()) {
                    PVsInfo += PV.toString() + "\n";
                }
            }
        }
        return PVsInfo;
    }

    public boolean VGscontainsPV(PV PV) {
        for (VG VG : VGs) {
            if (VG.getPVs().contains(PV)) {
                return true;
            }
        }
        return false;
    }

    public boolean PVscontainsPhysical(PhysicalDrive physicalDrive) {
        for (PV PV : PVs) {
            if (PV.getConnectedDrive() == physicalDrive) {
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

    public String listVGS() {
        String drivesInfo = "";
        for (VG VG : VGs) {
            drivesInfo += VG.toString() + "\n";
        }
        return drivesInfo;
    }
    public String listLVs() {
        String drivesInfo = "";
        for (LV LV : LVs) {
            drivesInfo += LV.toString() + "\n";
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
