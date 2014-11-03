/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Sam
 */
public class MockEffectenbeurs implements IEffectenbeurs {

    public ArrayList<Fonds> fondsList;
    private Timer fondsTimer;

    public MockEffectenbeurs() {
        fondsList = new ArrayList<>();

        fondsList.add(new Fonds("SAM", 300.0));
        fondsList.add(new Fonds("INTEL", 400.0));
        fondsList.add(new Fonds("nVidia", 200.0));
        fondsList.add(new Fonds("AMD", 150.0));

        fondsTimer = new Timer();
        fondsTimer.scheduleAtFixedRate(new fondsCalculator(), 0, 500);
    }

    @Override
    public ArrayList<Fonds> getKoersen() {
        return fondsList;
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
            }
        }
    }

}
