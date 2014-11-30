/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import remote.RemotePropertyListener;
import rmi.RMIClient;

/**
 *
 * @author Sam
 */
public class BannerController implements RemotePropertyListener {

    private IEffectenbeurs effectenBeurs;
    private Timer getFondsTimer;
    public AEXbanner AEXbanner;
    private RMIClient rmiClient;
    private String ipAddress;
    private int portNumber;

    public BannerController(AEXbanner banner) {
        askForData();
        rmiClient = new RMIClient(ipAddress, portNumber);
        effectenBeurs = rmiClient.setUp();
        try {
            //effectenBeurs = new MockEffectenbeurs();
            effectenBeurs.addListener(this, null);
        } catch (RemoteException ex) {
            Logger.getLogger(BannerController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.AEXbanner = banner;
        if (AEXbanner == null) {
            System.out.println("AEXBANNER ISNULL");
        }
        try {
            AEXbanner.setAmountOfElements(effectenBeurs.getKoersen().size());
        } catch (RemoteException ex) {
        }

//        getFondsTimer = new Timer();
//        getFondsTimer.scheduleAtFixedRate(new getFonds(), 0, 1000);
    }

    public void askForData() {
        // Welcome message
        System.out.println("CLIENT USING REGISTRY");

        // Get ip address of server
        Scanner input = new Scanner(System.in);
        System.out.print("Client: Enter IP address of server: ");
        ipAddress = input.nextLine();

        // Get port number
        System.out.print("Client: Enter port number: ");
        portNumber = input.nextInt();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException {

        Fonds f = (Fonds) evt.getNewValue();
        AEXbanner.addFond((Fonds) evt.getNewValue());

        System.out.println("Fond: " + f.toString());
    }

//    class getFonds extends TimerTask {
//
//        public int arrayListIndex = 0;
//
//        @Override
//        public void run() {
//            try {
//                ArrayList<Fonds> fondsList = effectenBeurs.getKoersen();
//                Fonds fonds = fondsList.get(arrayListIndex);
//
//                //AEXbanner.setText(fonds.getName() + ": " + fonds.getKoers());
//                AEXbanner.addFond(fonds);
//
//                arrayListIndex++;
//                if (arrayListIndex >= fondsList.size()) {
//                    arrayListIndex = 0;
//                }
//            } catch (RemoteException ex) {
//            }
//        }
//    }
}
