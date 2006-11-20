/*
 * IComm.java
 *
 * Created on 20 November 2006, 20:12
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package interfaces;

import linkclient.SimpleClient;
import java.awt.List;

/**
 *
 * @author Roger
 */
public interface IComm {
    public SimpleClient getClient();
    public int getPort();
    public void setPort(int p);
    public String getHost();
    public void setHost(String h);
    public List getList();
    public void setList(List list);
}
