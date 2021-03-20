package com.example.hw8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    int id;
    Test(int id){
        this.id = id;
    }


    public static void main(String[] args) {
        List<Test> list = new ArrayList<>();
        list.add(new Test(1));
        list.add(new Test(2));
        list.add(new Test(3));
        list.add(new Test(4));
        System.out.println(list.size());
        System.out.println(list.stream().filter(l -> l.id != 1).collect(Collectors.toList()).size());

    }
}
