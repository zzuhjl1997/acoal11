package com.plat.acoal.test.java8.sort;

import java.util.HashMap;
import java.util.Map;

public class test {
   /* class Solution {
        public void nextPermutation(int[] nums) {
            //  int len=nums.length;
            int i = nums.length - 2;

            while (i >= 0 && nums[i + 1] <= nums[i]) {//一路向下
                i--;
            }
            if (i >= 0) {
                int j = nums.length - 1;
                if (j >= 0 && nums[j] <= nums[i]) {
                    j--;
                }
                swap(nums, i, j);
            }
            reverse(nums, i + 1);
        }

        public void reverse(int[] nums, int start) {
            int i = start;
            int j = nums.length - 1;
            while (i < j) {
                swap(nums, i, j);
                i++;
                j--;
            }

        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }*/

    public static void main(String[] args) {
        String t="ABC";
        Map<String, Integer> need = new HashMap<>();
        for (char c : t) {
            //need[c]++;
        }
        System.out.println(need);
    }







}
