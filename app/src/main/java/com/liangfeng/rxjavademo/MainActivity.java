package com.liangfeng.rxjavademo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> score = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            score.add("6"+i);
        }
        Student xm = new Student("小米", score);
        Student oppo = new Student("OPPO", score);
        Student hw = new Student("华为", score);
        students.add(xm);
        students.add(oppo);
        students.add(hw);

        //创建被观察者
//        Observable<String> stringObservable = getStringObservable1();
        //创建订阅者
//        Subscriber<String> subscriber = getStringSubscriber();
        //订阅（使被观察者和订阅者发送关联）
//        stringObservable.subscribe(subscriber);


       /* Observable<String> stringObservable = Observable.just("Hello-------World");
        Action1<String> action1 = new Action1<String>() {
            @Override
            public void call(String s) {
                LogUtils.e(s);
            }
        };
        stringObservable.subscribe(action1);*/

        //Rxjava链式编程
        /*Observable.just("Hello-------World")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        LogUtils.e(s);
                    }
                });*/
        //Rxjava链式编程,使用java8的lambda可以使代码更简洁
        Observable.just("Hello, world!")
                .subscribe(s -> LogUtils.e(s));



        //使用from（）方法可以传入集合或者数组
        //from（）将每次传递一个元素个订阅者
        String[] arr = {"a", "b", "c", "d", "e", "f", "g"};
        Observable.from(arr)
                .subscribe(s -> LogUtils.e(s));
       /* Observable.from(arr)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        LogUtils.e(s);
                    }
                });*/




        //使用Rxjava中的map操作符（将一个对象转换成另一个对象）
       /* Observable.just("Hello, world!")
                .map(new Func1<String, String>() {//第二个泛型是返回值得类型
                    @Override
                    public String call(String s) {
                        return s+"---liangfeng";
                    }
                })
                .subscribe(s -> LogUtils.e(s));*/
        //一对一转化
        Observable.just("Hello, world!")
                .map(s -> s + "---liangfeng")
                .subscribe(s -> LogUtils.e(s));
        //一对多的转化flatMap（）
        Observable.from(students)
                .flatMap(student -> Observable.from(student.score))
                .subscribe(s -> LogUtils.e(s));
        /*Observable.from(students)
                .flatMap(new Func1<Student, Observable<String>>() {
                    @Override
                    public Observable<String> call(Student student) {
                        return Observable.from(student.score);
                    }
                })
        .subscribe(s -> LogUtils.e(s));*/
        LogUtils.e("============================");
        //过滤和最大输出值（操作符）
        Observable.from(students)
                .flatMap(student -> Observable.from(student.score))
                .filter(a -> !a.equals("60"))//过滤60分的
                .take(3)//只输出三个
                .subscribe(s -> LogUtils.e(s));

    }

    @NonNull
    private Subscriber<String> getStringSubscriber() {
        return new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                LogUtils.e(s);
            }
        };
    }

    @NonNull
    private Observable<String> getStringObservable1() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //执行事件
                subscriber.onNext("HelloWorld");
                //结束事件
                subscriber.onCompleted();
            }
        });
    }
}
