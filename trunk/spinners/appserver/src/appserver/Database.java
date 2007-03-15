/*
 * Database.java
 *
 * Created on 17 January 2007, 19:21
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package appserver;

import interfaces.*;
import appdata.*;
import java.util.*;

public class Database{
    private ArrayList theDatabase;
    
    public Database(int clientCount) {
        theDatabase = new ArrayList();
    }
    public void setUp(int i, IAppObj appObj){
        theDatabase.add(i, appObj);
        System.out.println("Database.setUp()"+i+" appObj: "+
                appObj.getAppModel()+" "+appObj.getAppController()+
                " status: "+appObj.getStatus()+" record: "+appObj.getRecord());        
    }    

    public IAppObj getAppObj(int i){
        return (IAppObj)theDatabase.get(i);
    }
    public void setAppObj(int i, IAppObj val) {
        theDatabase.set(i, val);
    }

}

/*Operating on a collection:
 *  Copy an object from the database if it is available:
        Object en = new Object();
        if (!theDatabase.isEmpty()){
            en = theDatabase.get(0); 
        }
    and print it out:
        System.out.println("Database.setUp() AppObj(0): "+en);
    
 *  Run through the entire database and print out its contents:   
        Iterator itr = theDatabase.iterator();
        while (itr.hasNext()){
            Object element = itr.next();
            System.out.println("Database.setUp() "+ element);          
        }
*/