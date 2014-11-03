/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Sam
 */
public class BannerController {

    private IEffectenbeurs effectenBeurs;
    private Timer getFondsTimer;
    public AEXbanner AEXbanner;

    public BannerController(AEXbanner banner) {
        effectenBeurs = new MockEffectenbeurs();

        this.AEXbanner = banner;
        banner.setAmountOfElements(effectenBeurs.getKoersen().size());

        getFondsTimer = new Timer();
        getFondsTimer.scheduleAtFixedRate(new getFonds(), 0, 1000);
    }

    class getFonds extends TimerTask {

        public int arrayListIndex = 0;

        @Override
        public void run() {
            ArrayList<Fonds> fondsList = effectenBeurs.getKoersen();
            Fonds fonds = fondsList.get(arrayListIndex);

            //AEXbanner.setText(fonds.getName() + ": " + fonds.getKoers());
            AEXbanner.addFond(fonds);

            arrayListIndex++;
            if (arrayListIndex >= fondsList.size()) {
                arrayListIndex = 0;
            }
        }
    }
}
