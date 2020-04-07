package com.plat.acoal.test.java8.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class BucketSortDemo {
    public static int[] countSort(int[] arr) {
        //1.获取数组最大值
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //2、根据数列最大值确定统计数组的长度
        int[] countArray = new int[max + 1];
        //3、遍历数列，填充统计数组
        for (int i = 0; i < arr.length; i++) {
            countArray[arr[i]]++;
        }
        //4、遍历统计数组，输出结果
        int index = 0;
        int[] sortedArray = new int[arr.length];
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                sortedArray[index++] = i;
            }
        }
        return sortedArray;
    }

    /**
     * 升级
     *
     * @param arr
     * @return
     */
    public static int[] countSort2(int[] arr) {
        //1，得到数列的最大值和最小值，并算出差值d
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        int d = max - min;
        //2、创建统计数组并统计对应元素的个数
        int[] countArray = new int[d + 1];
        for (int i = 0; i < arr.length; i++) {
            countArray[arr[i] - min]++;
        }
        //3、统计数组做变形,后面的元素等于前面元素的和  自己加上之前的
        for (int i = 1; i < countArray.length; i++) {
            // i+=i-1;->i=i+(i-1)
            countArray[i] += countArray[i - 1];
        }
        //4、倒序遍历原始序列，从统计数组找到正确位置，输出到结果数组
        int[] sortedArray = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            /**
             * 这代码抽象的也没谁了=。=
             * 一一拆解吧
             * arr[i]-min指的是定值到最小值的距离，也就是arr[i]的相对位置
             * 95-90=5 第五个 所以位置是4 s[4]=arr[i];
             * countArray[5]-1=4-1;下次遇到同一个数字是，排名就是3
             */
            sortedArray[countArray[arr[i] - min] - 1] = arr[i];
            countArray[arr[i] - min]--;
        }
        return sortedArray;
    }

    public static double[] bucketSort(double[] arr) {
        //获取数列的最大值和最小值，并求出差值d
        double max = arr[0];
        double min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        double d = max - min;
        //2.初始化桶
        int bucketNum = arr.length;
        ArrayList<LinkedList<Double>> bucketlist = new ArrayList<LinkedList<Double>>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketlist.add(new LinkedList<Double>());
        }
        //3.遍历原始数组，将每个元素放入桶中
        for (int i = 0; i < arr.length; i++) {
            //num是个啥 num应该就是具体哪个桶
            //arr[i]-min
            //举例 最大4.5 最小0.5 当前值3.3 3.3应该放进哪个桶呢  4.5-0.5=d  这段木头可以做成水槽 (3.3-0.5)*(5-1)/d 还是不懂

            int num = (int) ((arr[i] - min) * (bucketNum - 1) / d);
            bucketlist.get(num).add(arr[i]);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] array = new int[]{4, 4, 6, 5, 3, 2, 8, 1, 7, 5, 6, 0, 10};
        int[] sortedArray = countSort2(array);
        System.out.println(Arrays.toString(sortedArray));
    }
}
