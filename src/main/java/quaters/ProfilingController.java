package quaters;

public class ProfilingController implements ProfilingControllerMBean{
    private boolean enabled = true; //по умолчанию профилирование включено

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


}
