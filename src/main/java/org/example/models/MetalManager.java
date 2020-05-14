package org.example.models;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MetalManager {

    public void productionTask(Material metal, CyclicBarrier process, CyclicBarrier melt, CyclicBarrier mold) {
        try {
            processMetal(metal);
            process.await();//CyclicBarrier wait for as many as its told

            meltMetal(metal);
            melt.await();

            moldMetal(metal);
            mold.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private void processMetal(Material metal) {
        if (metal.getState() == MaterialState.Raw) {
            metal.setState(MaterialState.Processed);
            System.out.println(metal.getName() + " was " + MaterialState.Processed);
        }
        else {
            System.out.println(metal.getName() + " was not in the right state to be " + MaterialState.Processed);
        }
    }

    private void meltMetal(Material metal) {
        if (metal.getState() == MaterialState.Processed) {
            metal.setState(MaterialState.Liquid);
            System.out.println(metal.getName() + " was turned into " + MaterialState.Liquid + " state.");
        }
        else {
            System.out.println(metal.getName() + " was not in the right state to be turned into a " + MaterialState.Liquid);
        }
    }

    private void moldMetal(Material metal) {
        if (metal.getState() == MaterialState.Liquid) {
            metal.setState(MaterialState.Hardened);
            System.out.println(metal.getName() + " was " + MaterialState.Hardened);
        }
        else {
            System.out.println(metal.getName() + " was not in the right state to be " + MaterialState.Hardened + " in the mold.");
        }
    }
}
