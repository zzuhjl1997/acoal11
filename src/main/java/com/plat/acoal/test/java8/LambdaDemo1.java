package com.plat.acoal.test.java8;

public class LambdaDemo1 {
    interface Printer {
        void printer(String val);
    }

    public void printSomething(String something, Printer printer) {
        printer.printer(something);
    }

    public static void main(String[] args) {
        LambdaDemo1 lambdaDemo1 = new LambdaDemo1();
        String some = "erw34523";
//        Printer printer = new Printer() {
//            @Override
//            public void printer(String val) {
//                System.out.println(val);
//            }
//    };

      /* Printer printer=(String val)->{
           System.out.println(val);
       };*/

       /* Printer printer=(val)->{
            System.out.println(val);
        };
     */
        /*Printer printer=val->{
            System.out.println(val);
        };
       */
//        Printer printer=val->System.out.println(val);

        lambdaDemo1.printSomething(some,val->System.out.println(val));
//        lambdaDemo1.printSomething(some,printer);
}}