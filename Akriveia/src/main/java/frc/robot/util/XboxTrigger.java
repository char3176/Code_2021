public class XBoxTrigger extends Button {
    Joystick joystick;
    int axis;

    public XBoxTrigger(Joystick joystick, int axis) {
        this.joystick = joystick;
        this.axis = axis;
    }

    public double getTriggerValue() {
        return joystick.getRawAxis(axis);
    }

    @Override
    public boolean get() {
        return joystick.getRawAxis(axis) > 0.15;
    }

}
