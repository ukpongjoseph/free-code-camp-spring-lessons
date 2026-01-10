package dev.joseph_free_code_camp.freecodecamp;

public class MyFirstClass {

    private String myName;

    public MyFirstClass(String name){
        this.myName = name;
    }

    public String sayHello(){
        return "Hello from my first class, my name is " + myName;
    }

    public String sayHi(){
        return "Hi";
    }

}
