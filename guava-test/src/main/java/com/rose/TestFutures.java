package com.rose;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.JdkFutureAdapters;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TestFutures {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<?> future = es.submit(new Task());
        ListenableFuture<?> listenableFuture = JdkFutureAdapters.listenInPoolThread(future);

        System.out.println(Thread.currentThread().getName() + " is working");
        Thread.sleep(5000);

        Futures.addCallback(listenableFuture, new FutureCallback<Object>() {
            @Override
            public void onSuccess(Object o) {
                System.out.println("success" + o);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("failture");
                throwable.printStackTrace();
            }
        });
    }


    private static class Task implements Runnable {
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("dosomething");
//            throw new RuntimeException("some error");

        }
    }
}

