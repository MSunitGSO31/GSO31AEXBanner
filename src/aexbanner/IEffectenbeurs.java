/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aexbanner;

import java.rmi.Remote;
import java.util.ArrayList;

/**
 *
 * @author Sam
 */
public interface IEffectenbeurs extends Remote{
    
    ArrayList<Fonds> getKoersen();
    
}
