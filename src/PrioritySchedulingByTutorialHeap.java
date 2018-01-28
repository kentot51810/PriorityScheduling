import java.util.Scanner;

public class PrioritySchedulingByTutorialHeap {

    public static void main(String args[]) {
        Scanner scn = new Scanner(System.in);

        int temp, numberofProcess, process[]
                , processPriority[], burstTime[]
                , waitTime[], turnAroundTime[]
                , averageWaitingTime, averageTurnaroundTime, index;

        process = new int[10];
        processPriority = new int[10];
        burstTime = new int[10];
        waitTime = new int[10];
        turnAroundTime = new int[10];

        //numberofProcess is number of process
        //process is process
        //processPriority is process priority
        //burstTime is process burst time
        //waitTime is wait time
        // turnAroundTime is turnaround time
        //averageWaitingTime is average waiting time
        //averageTurnaroundTime is average turnaround time


        System.out.print("Enter the number of process : ");
        numberofProcess = scn.nextInt();
        System.out.print("\n\t Enter burst time : time priorities \n");

        for (index = 0; index < numberofProcess; index++) {
            System.out.print("\nProcess[" + (index + 1) + "]:");
            burstTime[index] = scn.nextInt();
            processPriority[index] = scn.nextInt();
            process[index] = index + 1;
        }

        //Bubble sorting on the basis of priority
        for (index = 0; index < numberofProcess - 1; index++) {
            for (int i = index + 1; i < numberofProcess; i++) {
                if (processPriority[index] < processPriority[i]) {
                    temp = processPriority[index];
                    processPriority[index] = processPriority[i];
                    processPriority[i] = temp;
                    temp = burstTime[index];
                    burstTime[index] = burstTime[i];
                    burstTime[i] = temp;
                    temp = process[index];
                    process[index] = process[i];
                    process[i] = temp;
                }
            }
        }
        waitTime[0] = 0;
        averageWaitingTime = 0;
        turnAroundTime[0] = burstTime[0];
        averageTurnaroundTime = turnAroundTime[0];
        for (index = 1; index < numberofProcess; index++) {
            waitTime[index] = turnAroundTime[index - 1];
            averageWaitingTime += waitTime[index];
            turnAroundTime[index] = waitTime[index] + burstTime[index];
            averageTurnaroundTime += turnAroundTime[index];
        }

        //Displaying the process

        System.out.print("\n\nProcess \t Burst Time \t Wait Time \t Turn Around Time   Priority \n");
        for (index = 0; index < numberofProcess; index++)
            System.out.print("\n   " + process[index] + "\t\t   " + burstTime[index] + "\t\t     " + waitTime[index] + "\t\t     " + turnAroundTime[index] + "\t\t     " + processPriority[index] + "\n");
        averageWaitingTime /= numberofProcess;
        averageTurnaroundTime /= numberofProcess;
        System.out.print("\n Average Wait Time : " + averageWaitingTime);
        System.out.print("\n Average Turn Around Time : " + averageTurnaroundTime);

    }
}