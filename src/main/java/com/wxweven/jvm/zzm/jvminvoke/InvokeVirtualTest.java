package com.wxweven.jvm.zzm.jvminvoke;

/**
 * @author wxweven
 * @date 2017/9/15
 */
public class InvokeVirtualTest {
    public static void main(String[] args) {
        Father test = new Son();
        test.say();
    }
}

class Father {
    public void say() {
        System.out.println("i am fater");
        System.out.println(this);
        this.hello();
        this.hi();
    }

    private void hello() {
        System.out.println("father say hello");
    }

    public void hi() {
        System.out.println("father say hi");
    }
}

class Son extends Father {

    public void hello() {
        System.out.println("son say hello");
    }

    //public void hi() {
    //    System.out.println("son say hi");
    //}
}
