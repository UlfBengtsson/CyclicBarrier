package org.example;

import org.example.models.Material;
import org.example.models.MaterialState;
import org.example.models.MetalManager;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App
{

    public static void main( String[] args )
    {

        metalFactory();

    }

    /*
        Simulating a metal factory that only can run one set of machines at a time (all of the same type)
    */
    public static void metalFactory() {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(4);

            Material[] metals = new Material[4];
            metals[0] = new Material(MaterialState.Raw,"Iron");
            metals[1] = new Material(MaterialState.Raw,"Copper");
            metals[2] = new Material(MaterialState.Raw,"Zinc");
            metals[3] = new Material(MaterialState.Raw,"Gold");

            MetalManager metalManager = new MetalManager();

            CyclicBarrier allProcessDone = new CyclicBarrier(4);
            CyclicBarrier allTurnedToLiquid = new CyclicBarrier(4);
            CyclicBarrier allHardenedInMold = new CyclicBarrier(4);

            for (int i = 0; i < 4 ; i++) {
                int finalI = i;//relative final (inside this for block)
                executorService.submit( () -> metalManager.productionTask(metals[finalI], allProcessDone, allTurnedToLiquid, allHardenedInMold));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }
}
