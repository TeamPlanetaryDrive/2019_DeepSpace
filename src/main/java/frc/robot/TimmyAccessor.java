package frc.robot;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class TimmyAccessor {
  public static void main(String[] args) {
    new TimmyAccessor().run();
  }

  public void run() {
    NetworkTableInstance inst = NetworkTableInstance.getDefault(); //get network table
    NetworkTable table = inst.getTable("Timmy");
    //NetworkTableEntry xEntry = table.getEntry("x");
    //NetworkTableEntry yEntry = table.getEntry("y");
    NetworkTableEntry gripState2 = table.getEntry("timmyGrip");
    inst.startClientTeam(2856);  
    inst.startDSClient();  
    while (true) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException ex) {
        System.out.println("interrupted");
        return;
      }
      //double x = xEntry.getDouble(0.0);
      //double y = yEntry.getDouble(0.0);
      //System.out.println("X: " + x + " Y: " + y);
      String harambe = gripState2.getString("your mom");//gets the string value from the network table
      System.out.println(harambe);
    }
  }
}