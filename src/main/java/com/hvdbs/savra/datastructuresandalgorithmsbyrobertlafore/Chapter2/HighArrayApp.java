package com.hvdbs.savra.datastructuresandalgorithmsbyrobertlafore.Chapter2;

/*
	Solution for Programming projects
	2.1, 2.2, 2.3, 2.6
 */

class HighArray {
    private final long[] a;
    private int nElems;

    public HighArray(int max) {
        a = new long[max];
        nElems = 0;
    }

    public boolean find(long searchKey) {
        int j;

        for (j = 0; j < nElems; j++) {
            if (a[j] == searchKey) {
                break;
            }
        }

        return j != nElems;
    }

    public void insert(long value) {
        a[nElems] = value;
        nElems++;
    }

    public boolean delete(long value) {
        int j;

        for (j = 0; j < nElems; j++) {
            if (value == a[j]) {
                break;
            }
        }

        if (j == nElems) {
            return false;
        } else {
            for (int k = j; k < nElems; k++) {
                a[k] = a[k + 1];
            }

            nElems--;
            return true;
        }
    }

    public void display() {
        for (int j = 0; j < nElems; j++) {
            System.out.print(a[j] + " ");
        }

        System.out.println();
    }

    public long getMax() {
        long max = -1;

        for (int i = 0; i < nElems; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }

        return max;
    }

    public long removeMax() {
        long max = getMax();
        delete(max);

        return max;
    }

    public long[] noDups() {
        for (int i = 0; i < nElems; i++) {
            for (int j = i + 1; j < nElems; j++) {
                if (a[i] == a[j]) {
                    for (int k = j; k < nElems - 1; k++) {
                        a[k] = a[k + 1];
                    }
                    nElems--;
                    j--;
                }
            }
        }
        return a;
    }
}

public class HighArrayApp {
    public static void main(String[] args) {
        int maxSize = 100;
        HighArray arr;
        arr = new HighArray(maxSize);

        System.out.println("arr is empty. The getMax method should return -1");
        System.out.println("The getMax method returned " + arr.getMax());

        arr.insert(77);
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(0);
        arr.insert(66);
        arr.insert(33);

        arr.display();

        int searchKey = 35;

        if (arr.find(searchKey)) {
            System.out.println("Found " + searchKey);
        } else {
            System.out.println("Can't find " + searchKey);
        }

        arr.delete(0);
        arr.delete(55);
        arr.delete(99);

        arr.display();

        HighArray sortedArray = new HighArray(maxSize);
        int i = 0;

        long currentMax = arr.removeMax();

        while (currentMax != -1) {
            sortedArray.insert(currentMax);
            currentMax = arr.removeMax();
        }

        sortedArray.display();

        System.out.println("=====Tets noDups method=====");

        arr.insert(123);
        arr.insert(55);
        arr.insert(22);
        arr.insert(123);
        arr.insert(88);
        arr.insert(11);
        arr.insert(123);
        arr.insert(123);

        System.out.println();
        System.out.println("Source array");
        arr.display();

        System.out.println();
        System.out.println();

        System.out.println("Array after delete dups");
        arr.noDups();
        arr.display();
    }
}
