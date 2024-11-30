package org.JavaLearning;

// Q:-> Why Solving Multiple Inheritance Through interface.Super Why Not Class.Super
// Reason:- Super Denotes immediate parent class but suppose if we do multiple inheritance through classes which class to refer would be
//  a great challenge. as there are multiple levels
public class DiamondProblemDemo {

    public  static interface A {
         default void display() {
           System.out.println("Hi We Are in Interface A");
        }
    }

    public static interface B {
        default void display() {
            System.out.println("Hi We Are in Interface B ");
        }
    }

    public static class Demo implements A,B {

        @Override public void display() {
            A.super.display();
        }
    }

    public static void main(String[] args) {
        Demo temp = new Demo();
        temp.display();
    }
}
