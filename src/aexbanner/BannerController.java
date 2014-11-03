/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import rmi.RMIClient;

/**
 *
 * @author Sam
 */
public class BannerController {

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
        //effectenBeurs = new MockEffectenbeurs();

        this.AEXbanner = banner;
        try {
            banner.setAmountOfElements(effectenBeurs.getKoersen().size());
        } catch (RemoteException ex) {
        }

        getFondsTimer = new Timer();
        getFondsTimer.scheduleAtFixedRate(new getFonds(), 0, 1000);
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

    class getFonds extends TimerTask {

        public int arrayListIndex = 0;

        @Override
        public void run() {
            try {
                ArrayList<Fonds> fondsList = effectenBeurs.getKoersen();
                Fonds fonds = fondsList.get(arrayListIndex);

                //AEXbanner.setText(fonds.getName() + ": " + fonds.getKoers());
                AEXbanner.addFond(fonds);

                arrayListIndex++;
                if (arrayListIndex >= fondsList.size()) {
                    arrayListIndex = 0;
                }
            } catch (RemoteException ex) {
            }
        }
    }
}
