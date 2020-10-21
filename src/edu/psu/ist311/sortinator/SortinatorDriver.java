package edu.psu.ist311.sortinator;
import java.util.Comparator;

public class SortinatorDriver {
    public static void main(String[] args) {
        Comparator<Person> personComparator = new Comparator<>() {
            @Override
            public int compare(Person o1, Person o2) {
                if (o1.getAge() < o2.getAge()) {
                    return -1;
                }
                if (o1.getAge() > o2.getAge()) {
                    return 1;
                }
                return 0;

            }

            public Boolean equals(Person p1, Person p2) {
                if (p1.getAge() == p2.getAge() && p1.getName() == p2.getName() && p1.getHeight() == p2.getHeight()) {
                    return true;
                }
                return false;
            }

        };

        Comparator<Integer> integerComparator = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        };


        System.out.println("HEAP EXAMPLE:");
        ISortinator<Person> sHeap = new HeapImpl<>(personComparator);

        sHeap.add(new Person("Jerry", 105));
        sHeap.add(new Person("Alice", 19));
        sHeap.add(new Person("Jerry", 105));
        sHeap.add(new Person("Billy", 2));
        sHeap.add(new Person("Moe", 23));
        sHeap.add(new Person("Agatha", 86));

        sHeap.switchState();

        int initialSizeHeap = sHeap.elementCount();

        for (int i = 0; i < initialSizeHeap; i++) {
            System.out.println(sHeap.removeSmallest());
        }

        System.out.println("\nLIST EXAMPLE:");
        ISortinator<Person> sList = new ListImpl<>(personComparator);

        sList.add(new Person("Jerry", 105));
        sList.add(new Person("Alice", 19));
        sList.add(new Person("Jerry", 105));
        sList.add(new Person("Billy", 2));
        sList.add(new Person("Moe", 23));
        sList.add(new Person("Agatha", 86));

        sList.switchState();

        int initialSizeList = sList.elementCount();

        for (int i = 0; i < initialSizeList; i++) {
            System.out.println(sList.removeSmallest());
        }


    }
}
