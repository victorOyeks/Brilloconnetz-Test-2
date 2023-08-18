package org.example;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class PancakeSimulationConcurrent {

    private static final int MAX_PANCAKES_PER_USER = 5;
    private static final int MAX_PANCAKES_MADE = 12;
    private static final int SIMULATION_DURATION = 30000;

    private static int pancakesMade;
    private static int pancakesEaten;

    private static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                simulatePancakeProduction();
                simulatePancakeConsumption();
                displayResults();
            }
        }, 0, SIMULATION_DURATION);
    }

    private static void simulatePancakeProduction() {
        try {
            semaphore.acquire();
            pancakesMade = new Random().nextInt(MAX_PANCAKES_MADE + 1);
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void simulatePancakeConsumption() {
        try {
            semaphore.acquire();
            pancakesEaten = 0;
            for (int i = 0; i < 3; i++) {
                int pancakesToEat = new Random().nextInt(MAX_PANCAKES_PER_USER + 1);
                pancakesEaten += pancakesToEat;
            }
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void displayResults() {
        System.out.println("Starting time: " + System.currentTimeMillis());
        System.out.println("Ending time: " + (System.currentTimeMillis() + SIMULATION_DURATION));
        System.out.println("Pancakes made by shopkeeper: " + pancakesMade);
        System.out.println("Pancakes eaten by users: " + pancakesEaten);

        if (pancakesMade >= pancakesEaten) {
            System.out.println("Shopkeeper met the needs of all users.");
            System.out.println("Pancakes wasted: " + (pancakesMade - pancakesEaten));
        } else {
            System.out.println("Shopkeeper couldn't meet the needs of all users.");
            System.out.println("Pancake orders not met: " + (pancakesEaten - pancakesMade));
        }

        System.out.println("-----------------------");
    }
}
