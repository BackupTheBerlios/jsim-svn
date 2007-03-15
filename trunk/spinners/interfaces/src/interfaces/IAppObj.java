/*
 * IAppObj.java
 *
 * Created on 02 March 2007, 14:10
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package interfaces;

/**
 *
 * @author Roger
 */
public interface IAppObj {
    public void makeApp(IStatus status);
    
    public IAppModel getAppModel();    
    public void setAppModel(IAppModel appModel);   
    public void makeAppModel(IStatus val);
    
    public IAppController getAppController();    
    public void setAppController(IAppController appController);   
    public void makeAppController();
    
    public IStatus getStatus();    
    public void setStatus(IStatus status);
    public IRecord getRecord();
    public void setRecord(IRecord record);
}
