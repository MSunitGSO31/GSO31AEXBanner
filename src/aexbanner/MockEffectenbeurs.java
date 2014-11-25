/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner;

import remote.BasicPublisher;
import remote.RemotePublisher;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import remote.RemotePropertyListener;

/**
 *
 * @author Sam
 */
public class MockEffectenbeurs extends UnicastRemoteObject implements IEffectenbeurs, RemotePublisher {

    public ArrayList<Fonds> fondsList;
    private transient Timer fondsTimer;
    private BasicPublisher basicPublisher;

    public MockEffectenbeurs() throws RemoteException {
        fondsList = new ArrayList<>();

        fondsList.add(new Fonds("SAM", 300.0));
        fondsList.add(new Fonds("INTEL", 400.0));
        fondsList.add(new Fonds("nVidia", 200.0));
        fondsList.add(new Fonds("AMD", 150.0));

        basicPublisher = new BasicPublisher(new String[]{"koers"});

        fondsTimer = new Timer();
        fondsTimer.scheduleAtFixedRate(new fondsCalculator(), 0, 500);

    }

    @Override
    public ArrayList<Fonds> getKoersen() throws RemoteException {
        return fondsList;
    }

    public void addListener(RemotePropertyListener listener, String property) throws RemoteException {
        basicPublisher.addListener(listener, null);
    }

    public void removeListener(RemotePropertyListener listener, String property) throws RemoteException {
        basicPublisher.removeListener(listener, null);
    }

    class fondsCalculator extends TimerTask {

        @Override
        public void run() {
            Random randomizer = new Random();

            for (Fonds fonds : fondsList) {
                int randomInt = randomizer.nextInt(1000);
                double randomDouble = randomizer.nextDouble();

                randomDouble = (double) Math.round(randomDouble * 10) / 10;

                fonds.setKoers(randomInt + randomDouble);
                fonds.setName(randomInt + "");

                informListener(fonds);

            }
        }
    }

    public void informListener(Fonds fonds) {
        basicPublisher.inform(this, null, null, fonds);
    }
}
