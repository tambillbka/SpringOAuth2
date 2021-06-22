package com.education.tutoringappserver.common.utils;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ThreadUtils {
    public static void awaitCountDown(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static <T> void runByMaxThreads(List<T> dataList, int maxThreads, DataThreadRunner<T> threadRunner) {
        if (CollectionUtils.isEmpty(dataList)) {
            return;
        }
        int dataSize = dataList.size();
        int dataPerThread = (int) Math.ceil((double) (dataSize) / maxThreads);
        if (dataPerThread > 0) {
            AtomicReference<CountDownLatch> latch = new AtomicReference<>(
                    new CountDownLatch((int) Math.ceil((double) (dataSize) / dataPerThread)));

            Lists.partition(dataList, dataPerThread).forEach(
                    subDataList -> CompletableFuture.runAsync(() -> {
                        if (!CollectionUtils.isEmpty(subDataList)) {
                            threadRunner.run(subDataList);
                        }
                        latch.get().countDown();
                    }, Executors.newSingleThreadExecutor()));

            awaitCountDown(latch.get());
        }
    }
}
