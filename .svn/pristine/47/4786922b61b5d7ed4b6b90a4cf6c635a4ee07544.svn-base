package com.plat.acoal;


//class A {
//    static {
//        System.out.print("1");
//    }
//
//    public A() {
//        System.out.print("2");
//    }
//}
//
//class B extends A {
//    static {
//        System.out.print("a");
//    }
//
//    public B() {
//        System.out.print("b");
//    }
//
//    public static void main(String[] args) {
//        A ab = new B();
//        ab = new B();
//    }
//
//}


public class Max {
    int[] myList = {1, 2, 3, 100,3,10,11,12,13,-1};
    int num = myList[0];
    void getValue(){ //创建一般方法
        for (int i = 0; i < myList.length; i++) {
            num=(myList[i] < num?num: myList[i]);//三元运算符
        }
        System.out.println("最大值为" + num);
    }
    public static void main(String args[]){
        Max max=new Max(); //创建对象
        max.getValue(); //通过对象调用一般方法
    }
}

