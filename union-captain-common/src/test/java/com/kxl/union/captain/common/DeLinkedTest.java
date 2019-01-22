package com.kxl.union.captain.common;

import com.kxl.union.captain.common.collection.ComplexDeLinked;
import com.kxl.union.captain.common.collection.DeLinked;

public class DeLinkedTest {

    public static void main(String... args) {
        DeLinked<String> deLinked = new ComplexDeLinked<>();

        deLinked.add("a");//0
        deLinked.add("b");//1
        deLinked.add("c");//2
        deLinked.add("e");//3
        deLinked.add("f");//4
        deLinked.add("g");//5
//        deLinked.resetHead();
        int i = 0;

//        System.out.println(deLinked.get(4));
//        System.out.println(deLinked.getFirst());
//        System.out.println(deLinked.getLast());
        deLinked.resetHead();
        while (deLinked.hasNext()) {
            if(i == 3){
                deLinked.addAfter(deLinked.currentCursor(),"M");
            }
            System.out.println(deLinked.next());
            i++;
        }
        System.out.println(deLinked.currentCursor());
        System.out.println("-------------");
        deLinked.descreament();
        while (deLinked.hasPre()) {
            System.out.println(deLinked.pre());
        }
    }
}
