package org.example;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PancakeSimulationNonConcurrent {

    private static final int MAX_PANCAKES_PER_USER = 5;
    private static final int MAX_PANCAKES_MADE = 12;
    private static final int SIMULATION_DURATION = 30000;

    private static int pancakesMade;
    private static int pancakesEaten;

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
        pancakesMade = new Random().nextInt(MAX_PANCAKES_MADE + 1);
    }

    private static void simulatePancakeConsumption() {
        pancakesEaten = 0;
        for (int i = 0; i < 3; i++) {
            int pancakesToEat = new Random().nextInt(MAX_PANCAKES_PER_USER + 1);
            pancakesEaten += pancakesToEat;
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
