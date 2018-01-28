package ComparatorLab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SampleWithComparator {
    public static void main(String[] args) {
        List<IntegerObject> list = new ArrayList<>();
        list.add(new IntegerObject(6, 5));
        list.add(new IntegerObject(5, 5));
        list.add(new IntegerObject(4, 4));
        list.add(new IntegerObject(3, 3));
        list.add(new IntegerObject(2, 1));
        list.add(new IntegerObject(1, 1));

        Collections.sort(list, new Comparator<IntegerObject>() {
            @Override
            public int compare(IntegerObject o1, IntegerObject o2) {
                return o1.getI2().compareTo(o2.getI2());
            }
        });

        for (IntegerObject entry :
                list) {
            System.out.println(entry.getI1()  + "   " +  entry.getI2());
        }
    }
}

