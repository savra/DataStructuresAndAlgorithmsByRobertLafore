package com.hvdbs.savra.datastructuresandalgorithmsbyrobertlafore.Chapter2;

/*
	Solution for Programming projects
	2.4, 2.5
 */

import java.util.Random;

class OrdArray {
    private final long[] a;
    private int nElems;

    public OrdArray(int max) {
        a = new long[max];
        nElems = 0;
    }

    public int size() {
        return nElems;
    }

    public int find(long searchKey) {
        int lowerBound = 0;
        int upperBound = nElems - 1;
        int curIn;

        while (true) {
            curIn = (lowerBound + upperBound) / 2;

            if (a[curIn] == searchKey) {
                return curIn;
            } else if (lowerBound > upperBound) {
                return lowerBound;
            } else {
                if (a[curIn] < searchKey) {
                    lowerBound = curIn + 1;
                } else {
                    upperBound = curIn - 1;
                }
            }
        }
    }

    public void insert(long value) {
        int j = find(value);

        for (int k = nElems; k > j; k--) {
            a[k] = a[k - 1];
        }

        a[j] = value;
        nElems++;
    }

    public boolean delete(long value) {
        int j = find(value);

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

    public long getElement(int index) {
        if (index <= nElems - 1) {
            return a[index];
        }

        throw new ArrayIndexOutOfBoundsException();
    }

    public OrdArray merge(OrdArray a, OrdArray b) {
        OrdArray ordArray = new OrdArray(a.size() + b.size());

        int i = 0, j = 0;
        long currentA, currentB;

        while (i < a.size() || j < b.size()) {
            if (i < a.size()) {
                currentA = a.getElement(i);

                if (j < b.size()) {
                    currentB = b.getElement(j);

                    if (currentA <= currentB) {
                        ordArray.insert(currentA);
                        i++;
                    } else {
                        ordArray.insert(currentB);
                        j++;
                    }

                    continue;
                }

                ordArray.insert(currentA);
                i++;
            } else {
                if (j < b.size()) {
                    currentB = b.getElement(j);
                    ordArray.insert(currentB);
                    j++;
                } else {
                    return ordArray;
                }
            }
        }

        return ordArray;
    }

    public void display() {
        for (int j = 0; j < nElems; j++) {
            System.out.print(a[j] + " ");
        }

        System.out.println();
    }
}

public class OrderedApp {
    public static void main(String[] args) {
        int maxSize = 100;
        OrdArray arr;
        arr = new OrdArray(maxSize);

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

        int searchKey = 55;

        if (arr.find(searchKey) != arr.size()) {
            System.out.println("Found " + searchKey);
        } else {
            System.out.println("Can't find " + searchKey);
        }

        arr.display();

        arr.delete(0);
        arr.delete(55);
        arr.delete(99);

        arr.display();
        System.out.println();
        System.out.println("===Testing merge method===");

        System.out.println("First array size less than second");
        Random random = new Random();
        OrdArray ordArray1 = new OrdArray(5);
        ordArray1.insert(random.nextInt(100));
        ordArray1.insert(random.nextInt(100));
        ordArray1.insert(random.nextInt(100));
        ordArray1.insert(random.nextInt(100));
        ordArray1.insert(random.nextInt(100));

        OrdArray ordArray2 = new OrdArray(9);
        ordArray2.insert(random.nextInt(100));
        ordArray2.insert(random.nextInt(100));
        ordArray2.insert(random.nextInt(100));
        ordArray2.insert(random.nextInt(100));
        ordArray2.insert(random.nextInt(100));
        ordArray2.insert(random.nextInt(100));
        ordArray2.insert(random.nextInt(100));
        ordArray2.insert(random.nextInt(100));
        ordArray2.insert(random.nextInt(100));

        OrdArray ordArray3 = ordArray1.merge(ordArray1, ordArray2);
        System.out.println("source ordArray1");
        ordArray1.display();
        System.out.println("source ordArray2");
        ordArray2.display();

        System.out.println("Merged array");
        ordArray3.display();

        System.out.println();
        System.out.println();
        System.out.println("First array size greater than second");

        ordArray1 = new OrdArray(14);
        ordArray1.insert(random.nextInt(100));
        ordArray1.insert(random.nextInt(100));
        ordArray1.insert(random.nextInt(100));
        ordArray1.insert(random.nextInt(100));
        ordArray1.insert(random.nextInt(100));
        ordArray1.insert(random.nextInt(100));
        ordArray1.insert(random.nextInt(100));
        ordArray1.insert(random.nextInt(100));
        ordArray1.insert(random.nextInt(100));
        ordArray1.insert(random.nextInt(100));
        ordArray1.insert(random.nextInt(100));
        ordArray1.insert(random.nextInt(100));
        ordArray1.insert(random.nextInt(100));
        ordArray1.insert(random.nextInt(100));

        ordArray2 = new OrdArray(7);
        ordArray2.insert(random.nextInt(100));
        ordArray2.insert(random.nextInt(100));
        ordArray2.insert(random.nextInt(100));
        ordArray2.insert(random.nextInt(100));
        ordArray2.insert(random.nextInt(100));
        ordArray2.insert(random.nextInt(100));
        ordArray2.insert(random.nextInt(100));

        ordArray3 = ordArray1.merge(ordArray1, ordArray2);

        System.out.println("source ordArray1");
        ordArray1.display();
        System.out.println("source ordArray2");
        ordArray2.display();

        System.out.println("Merged array");
        ordArray3.display();
    }
}
