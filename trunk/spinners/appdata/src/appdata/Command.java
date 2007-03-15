/*
 * Command.java
 *
 * Created on 24 November 2006, 11:50
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package appdata;

import interfaces.ICommand;
import java.io.Serializable;

/**
 *
 * @author Roger
 */
public class Command implements ICommand, Serializable{
    char c;
    int i;
    /** Creates a new instance of Command */
    public Command(char c, int i) {
        this.c = c;
        this.i = i;
    }
    public Command getCommand(){
        return this;
    }
    public void setCommand(char c, int i){
        this.c = c;
        this.i = i;
    }
    public char getName(){
        return c;
    }
    public int getIndex(){
        return i;
    }

}
