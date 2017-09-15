package com.wxweven.jvm.zzm;

class Father {

    public void say() {
        System.out.println("father say ");
        hello();//invokespecial调用
        System.out.println(this);
        this.hello();//invokespecial调用
        this.hi();//invokevirtual调用
    }

    private void hello() {
        System.out.println("hello father");
    }

    public void hi() {
        System.out.println("hi father");
    }
}


class Son extends Father {

    public void hello() {
        System.out.println("hello son");
    }

    public void hi() {
        System.out.println("hi son");
    }

    public String toString() {
        return "Son{}";
    }
}

public class ThisTest {
    public static void main(String[] args) {
        Father test = new Son();
        test.say();//编译时指向父类中国的fMe()，在运行时由于是invokevirtual调用，因此test将变成实际类型Son，如果Son中有Fme()，就调用Son自己的，若没有就调用父类的
    }
}