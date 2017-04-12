package com.liangfeng.rxjavademo;

import java.util.List;

/**
 * Created by mzf on 2017/4/12.
 */

public class Student {
    String name;
    String age;
    String sex;
    List<String> score;

    public List<String> getScore() {
        return score;
    }

    public void setScore(List<String> score) {
        this.score = score;
    }

    public Student(String name, List<String> score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
