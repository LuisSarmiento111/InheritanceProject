import java.util.Scanner;

public class LVMRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LVM system = new LVM();
        System.out.println("Welcome to the LVM System.");
        String cmd = "";
        while (!cmd.toLowerCase().equals("exit")) {
            System.out.print("cmd#: ");
            cmd = scanner.nextLine();
            if (!cmd.toLowerCase().equals("exit")) {
                System.out.println(system.logic(cmd));
            }
        }
    }
}
