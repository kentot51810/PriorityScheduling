package ComparatorLab;

import static java.lang.System.*;

import java.text.DecimalFormat;
import java.util.*;

public class PrioritySchedulingBaseOnTutorialHeap {

    //declaration of global variables
    private static int numberOfProcess;
    private static float averageWaitingTime, averageTurnAroundTime;
    private static float waitingTime, turnAroundTime;
    private static DecimalFormat decimalFormat;
    private static List<Integer> listOfBurstTime;
    private static List<Integer> listOfPriorityOrder;
    private static List<Integer> turnAroundTimeList;
    private static List<Map.Entry<Integer, Map.Entry<String, Integer>>> list;
    private static Map<String, Integer> processValueMap;
    private static Scanner scn;

    private static void initializeInstanceVariables() {
        listOfBurstTime = new ArrayList<>();
        listOfPriorityOrder = new ArrayList<>();
        turnAroundTimeList = new ArrayList<>();
        list = new ArrayList<>();
        processValueMap = new HashMap<>();
        scn = new Scanner(in);
        decimalFormat = new DecimalFormat("0.#");
    }

    public static void main(String[] args) {
        initializeInstanceVariables();

        out.println("\n\t\tPRIORITY SCHEDULING");
        out.print("\nEnter the total number of process: ");

        numberOfProcess = getInput(numberOfProcess);


        out.println("\n\nEnter the burst time:\n");
        getTheBurstTime();

        out.println("\n\nEnter the priority order:\n");
        getThePriorityOrder();

        out.print("\n\n");


    }

    private static void getThePriorityOrder() {
        for (int index = 0; index < listOfBurstTime.size(); index++) {
            out.print("P[" + (index + 1) + "]: {" + listOfBurstTime.get(index) + "} : ");
            int priority= 0;
            priority = getInput(priority);
            listOfPriorityOrder.add(priority);
        }
    }

    private static void getTheBurstTime() {
        for (int index = 0; index < numberOfProcess; index++) {
            out.print("P[" + (index + 1) + "]: ");
            int burstTime = 0;
            burstTime = getInput(burstTime);
            listOfBurstTime.add(burstTime);
        }
    }

    private static int getInput(int input) {
        boolean continueLoop = true;
        while (continueLoop) {
            try {
                input = scn.nextInt();
                continueLoop = false;
            } catch (InputMismatchException e) {
                //skip the key mismatched input.
                scn.nextLine();
                err.println("Error! InputMismatchException handled. Please enter the correct input.");
            }
        }
        return input;
    }
}
