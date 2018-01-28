import java.text.DecimalFormat;
import java.util.*;

import static java.lang.System.*;

public class PriorityScheduling {

    //declaration of global variables
    private static int numberOfProcess;
    private static float averageWaitingTime, averageTurnAroundTime;
    private static float waitingTime, turnAroundTime;
    private static DecimalFormat decimalFormat;
    private static List<Integer> listOfBurstTime;
    private static List<Integer> listOfPriorityOrder;
    private static List<Float> turnAroundTimeList;
    private static List<Map.Entry<Map.Entry<String, Integer>, Integer>> list;
    private static Map<String, Integer> processValueMap;
    private static Scanner scn;

    private static void initializeInstanceVariables() {
        listOfBurstTime = new ArrayList<>();
        listOfPriorityOrder = new ArrayList<>();
        turnAroundTimeList = new ArrayList<>();
        scn = new Scanner(in);
        decimalFormat = new DecimalFormat("0.#");
        processValueMap = new HashMap<>();
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

        saveInMap();

        getTheAverageWaitingTime();
        out.println("THE AVERAGE WAITING TIME IS: " + decimalFormat.format(averageWaitingTime));

        getTheTotalTurnaroundTime();
        out.print("THE AVERAGE TURNAROUND TIME IS: " + decimalFormat.format(averageTurnAroundTime));


        out.print("\n\n");
        showTheGanttChart();
    }

    private static void showTheGanttChart() {
        out.println("--------------------------------Gantt Chart--------------------------------");

        for (Map.Entry<Map.Entry<String, Integer>, Integer> entry : list) {
            out.print("|\t" + entry.getKey().getKey() + "\t");
        }
        out.println("|");

        for (Map.Entry<Map.Entry<String, Integer>, Integer> entry : list) {
            out.print("|\t" + entry.getKey().getValue() + "\t");
        }
        out.println("|");

        out.print(0);
        for (int index = 0; index < listOfBurstTime.size(); index++) {
            out.print("\t\t" + decimalFormat.format(turnAroundTimeList.get(index)));
        }

        out.println("\n\n");
    }

    private static void getTheTotalTurnaroundTime() {
        for (Map.Entry<Map.Entry<String, Integer>, Integer> entry : list) {
            turnAroundTime += entry.getKey().getValue();
            turnAroundTimeList.add(turnAroundTime);
            averageTurnAroundTime += turnAroundTime;
        }
        averageTurnAroundTime = averageTurnAroundTime / numberOfProcess;
    }

    private static void getTheAverageWaitingTime() {
        int index = 0;
        for (Map.Entry<Map.Entry<String, Integer>, Integer> entry : list) {
            if (index < list.size() - 1){
                waitingTime += entry.getKey().getValue();
                averageWaitingTime += waitingTime;
                index++;
            }
        }
        averageWaitingTime = averageWaitingTime / numberOfProcess;
    }

    private static void saveInMap() {
        Map<Map.Entry<String, Integer>, Integer> prioritySampleMapWithEntries = new LinkedHashMap<>();

        //create a list of process name. i.e. P1, P2, P3, P4...
        String[] processName = new String[numberOfProcess];
        for (int index = 0; index < numberOfProcess; index++) {
            processName[index] = "P" + (index + 1);
        }

        //
        for (int index = 0; index < listOfBurstTime.size(); index++) {
            processValueMap.put(processName[index], listOfBurstTime.get(index));
        }
        int index = 0;
        Iterator<Map.Entry<String, Integer>> entryIterator = processValueMap.entrySet().iterator();
        while (entryIterator.hasNext()) {
            //ERROR!! CAN'T STORE KEYS WITH HAVING THE SAME KEYS. DUPLICATE KEYS WILL ONLY OVERWRITE THE PREVIOUS VALUE
            //THUS RESULTING TO AN ERROR OF HAVING INCOMPLETE ARRAY.
            prioritySampleMapWithEntries.put(entryIterator.next(), listOfPriorityOrder.get(index));
            index++;
        }

        sortUnsortedMap(prioritySampleMapWithEntries);

        //Working but too complicated
//        for (int index = 0; index < listOfBurstTime.size(); index++) {
//            int finalIndex = index;
//            prioritySampleMap.put(listOfPriorityOrder.get(index), new HashMap<String, Integer>() {{
//                put(processName[finalIndex], listOfBurstTime.get(finalIndex));
//            }});
//        }

//        Map<Map.Entry<String, Integer>, Integer> priorityMap = new HashMap<>();
//        List<Map.Entry<String, Integer>> sampleList = new ArrayList<>(processValueMap.entrySet());
//        for (Map.Entry<String, Integer> entry: sampleList){
//            priorityMap.put(processValueMap.entrySet() , listOfPriorityOrder)
//        }

    }

    private static void sortUnsortedMap(Map<Map.Entry<String, Integer>, Integer> mapWithEntries) {
        list = new ArrayList<>(mapWithEntries.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Map.Entry<String, Integer>, Integer>>() {
            @Override
            public int compare(Map.Entry<Map.Entry<String, Integer>, Integer> o1, Map.Entry<Map.Entry<String, Integer>, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
    }

    private static void getThePriorityOrder() {
        for (int index = 0; index < listOfBurstTime.size(); index++) {
            out.print("P[" + (index + 1) + "]: {" + listOfBurstTime.get(index) + "}\t: ");
            int priorityOrder = 0;
            priorityOrder = getInput(priorityOrder);
            listOfPriorityOrder.add(priorityOrder);
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
                scn.nextLine();
                err.println("Error! InputMismatchException handled. Please enter accepted integers only");
            }
        }
        return input;
    }
}
