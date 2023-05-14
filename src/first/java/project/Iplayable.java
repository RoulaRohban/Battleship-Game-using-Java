/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package first.java.project;

/**
 *
 * @author RoR
 */
public interface  Iplayable
{
    public void subscribe(Player player) throws playerNotCompatibleException;

    public void leave(Player player) throws playerNotCompatibleException;   
}
class playerNotCompatibleException extends Exception
{
}
    

