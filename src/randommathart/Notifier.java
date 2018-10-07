package randommathart;

import java.util.TimerTask;


public class Notifier extends TimerTask{
    
    public Notifier(Frame f) {
        this.f = f;
    }
    
    @Override
    public void run() {
        f.tick();
    }
    
    private Frame f;
}
