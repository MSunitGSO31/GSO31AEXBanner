/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aexbanner;

/**
 *
 * @author Sam
 */
public class Fonds {
    
    private String name;
    private double koers;
    
    public Fonds(String name, double koers){
        this.name = name;
        this.koers = koers;
    }

    public String getName() {
        return name;
    }

    public double getKoers() {
        return koers;
    }

    public void setKoers(double koers) {
        this.koers = koers;
    }
    
    @Override
    public String toString(){
        return name + ": " + koers;
    }

}
