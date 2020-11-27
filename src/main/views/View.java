package main.views;

import main.utils.Console;
import main.utils.YesNoDialog;

public class View {

    public void startViewWrite() {
    	 new Console().writeln("Draughts");
    }
    
    public boolean resumeViewRead() {
    	return new YesNoDialog().read("¿Queréis jugar otra");
    }


}
