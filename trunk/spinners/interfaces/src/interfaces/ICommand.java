/*
 * ICommand.java
 *
 * Created on 24 November 2006, 11:57
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package interfaces;

/**
 *
 * @author Roger
 */
public interface ICommand {
   public ICommand getCommand();
   public void setCommand(char c, int i);
   public char getName();
}
