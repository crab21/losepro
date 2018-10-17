package com.learn.ExcutorLearn;

import java.util.concurrent.*;

/**
 * Created by k on 2018/9/28.
 */
public class ExcutorServiceBaseLearn {
    static ExecutorService executorService = new ThreadPoolExecutor(50, 100, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<>(100000));

    public static void cacheLearn() {

        for (int i = 0; i < 100; ++i) {
            final int index = i;

//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            Future<?> submit = executorService.submit(() -> {
                System.out.println(index);
            });

        }
        ThreadPoolExecutor ts = (ThreadPoolExecutor) executorService;
        while (true) {
            int queueSize = ts.getQueue().size();
            int activeCount = ts.getActiveCount();
            long completedTaskCount = ts.getCompletedTaskCount();
            if (queueSize > 0 | activeCount > 0 | completedTaskCount > 0) {
                System.out.println(queueSize + ".......");
                System.out.println(completedTaskCount + ".......>>>>>>>");
                System.out.println(activeCount + ".............");

            }

        }
    }

    public static void main(String[] args) {
        cacheLearn();
//        long a = (long) 0.1;
//        Boolean aa = null;
//        System.out.println(aa);
    }
}
