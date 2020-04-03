package com.plat.acoal.test.java8;

import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;

public class Tree {

    /**
     * 二叉树节点
     */
    private static class TreeNode{
        int data;
        TreeNode leftchild;
        TreeNode rightchild;
        TreeNode(int data){
            this.data=data;
        }
    }





    /* *//**
     * 构建二叉树
     * @param inputList
     *//*
    public static TreeNode createBinaryTree(LinkedList<Integer> inputList){
        TreeNode node=null;
        if(inputList==null||inputList.isEmpty()){
            return null;
        }
        Integer data=inputList.removeFirst();
        if(data!=null){
            node=new TreeNode(data);



        return  null;
    }*/






}
