package com.mxdl.retrofit.api.entity;

/**
 * Description: <User><br>
 * Author:      mxdl<br>
 * Date:        2020/3/26<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class User {

    private int age;//	integer($int32)
    private int id;//	integer($int64)
    private int salary;//	integer($int64)
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", id=" + id +
                ", salary=" + salary +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
