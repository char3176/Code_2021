package frc.robot;
import javax.swing.JFileChooser;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Controller {
    
    private static Controller instance = new Controller();
    private XboxController m_Controller = new XboxController(2);
    private final JoystickButton aButton = new JoystickButton(m_Controller, Button.kA.value);
    private final JoystickButton bButton = new JoystickButton(m_Controller, Button.kB.value);
    private final JoystickButton yButton = new JoystickButton(m_Controller, Button.kY.value);
    private final JoystickButton xButton = new JoystickButton(m_Controller, Button.kX.value);
    private final JoystickButton rBumper = new JoystickButton(m_Controller, Button.kBumperRight.value);
    private final JoystickButton lBumper = new JoystickButton(m_Controller, Button.kBumperLeft.value);
    
    /**
     * <b> Low Speed Button </b>
     * <p>
     * returns LowSpeedButton to be used for RobotContainer.
     * @return aButton
     * @author Jared Brown, Caleb Walters, Amelia Bingamin
     */
    public JoystickButton getAButton() {
        return aButton;
    }

    /**
     * <b> Medium Speed Button </b>
     * <p>
     * returns MediumSpeedButton to be used for RobotContainer.
     * @return bButton
     * @author Jared Brown, Caleb Walters, Amelia Bingamin
     */
    public JoystickButton getBButton() {
        return bButton;
    }

    /**
     * <b> High Speed Button </b>
     * <p>
     * returns HighSpeedButton to be used for RobotContainer.
     * @return yButton
     * @author Jared Brown, Caleb Walters, Amelia Bingamin
     */
    public JoystickButton getYButton() {
        return yButton;
    }

    /**
     * <b> Extreme Speed Button </b>
     * <p>
     * returns ExtremeSpeedButton to be used for RobotContainer.
     * @return xButton
     * @author Jared Brown, Caleb Walters, Amelia Bingamin
     */
    public JoystickButton getXButton() {
        return xButton;
    }

    /**
     * <b> Off Button </b>
     * <p>
     * returns OffButton to be used for RobotContainer.
     * @return rBumper
     * @author Jared Brown, Caleb Walters, Amelia Bingamin
     */
    public JoystickButton getRBumper() {
        return rBumper;
    }

    /**
     * <b> Agitate Button </b>
     * <p>
     * returns AgitateButton to be used for RobotContainer.
     * @return lBumper
     * @author Jared Brown, Caleb Walters, Amelia Bingamin
     */
    public JoystickButton getLBumper() {
        return lBumper;
    }

    /**
     * Returns a universal instance of the Controller.
     * @return Controller -- to be used anywhere the Controller is needed
     */
    public static Controller getInstance() {
        return instance;
    }

}