// netbeans\spinners\thread_spinners\src\thread_spinners\AppModelStub.java

package appgui;

import interfaces.IModel;
import java.util.*; 	// for Date and Observable/Observer
import server.*;

/*
 *  A 'AppModelStub' for use with the MainFormTest of mainForm
 * @author Roger Prowse
 * @version "%I%, %G%"
 */
public class AppModelStub implements IModel{
    int clientCount;
    int sysMode;
     
    public AppModelStub(){
        clientCount = 4;
        sysMode = 0;
        String title = "appgui test. Mode = ";
    }
    public void startModel(){       
    }
    public int getCount(){
        return clientCount;
    }
    public int getMode(int i){
        return 0;
    }
    public Observable getObservable(int i){
        Observable ob = new Observable();
        return ob;
    }    
}//AppModelStub
/////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////


