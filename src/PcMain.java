
/**
 *
 * @author Simon, Vegar, Per-Gunnar
 */
public class PcMain {
    
    protected static DrivingModeFlag drivingModeFlag = DrivingModeFlag.MANUAL;
            
    public static void main(String[] args){
    OdroidData oData = new OdroidData(); 
    oData.start();
    GUI gui = new GUI();
    gui.setVisible(true);
}
}