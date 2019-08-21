/*
 * Copyright 2019 David Karnok
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hu.akarnokd.rxjava3.bridge;

import static org.junit.Assert.*;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public final class TestHelper {

    private TestHelper() {
        throw new IllegalStateException("No instances!");
    }

    /**
     * Validates that the given class, when forcefully instantiated throws
     * an IllegalArgumentException("No instances!") exception.
     * @param clazz the class to test, not null
     */
    public static void checkUtilityClass(Class<?> clazz) {
        try {
            Constructor<?> c = clazz.getDeclaredConstructor();

            c.setAccessible(true);

            try {
                c.newInstance();
                fail("Should have thrown InvocationTargetException(IllegalStateException)");
            } catch (InvocationTargetException ex) {
                assertEquals("No instances!", ex.getCause().getMessage());
            }
        } catch (Exception ex) {
            AssertionError ae = new AssertionError(ex.toString());
            ae.initCause(ex);
            throw ae;
        }
    }

    /**
     * Checks if tasks can be immediately executed on the computation scheduler.
     * @throws ObstructionException if the schedulers don't respond within 1 second
     */
    public static void checkObstruction() {
        final int ncpu = Runtime.getRuntime().availableProcessors();

        final CountDownLatch cdl = new CountDownLatch(ncpu);
        final List<Scheduler.Worker> workers = new ArrayList<Scheduler.Worker>();
        final Runnable task = new Runnable() {
            @Override
            public void run() {
                cdl.countDown();
            }
        };

        for (int i = 0; i < ncpu; i++) {
            workers.add(Schedulers.computation().createWorker());
        }
        for (Scheduler.Worker w : workers) {
            w.schedule(task);
        }
        try {
            if (!cdl.await(5, TimeUnit.SECONDS)) {
                int cnt = 0;
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<Thread, StackTraceElement[]> e : Thread.getAllStackTraces().entrySet()) {
                    if (e.getKey().getName().contains("Computation")) {
                        cnt++;
                        sb.append("Thread: ").append(e.getKey()).append("\r\n");
                        for (StackTraceElement entry : e.getValue()) {
                            sb.append(" at ").append(entry).append("\r\n");
                        }
                    }
                }

                RuntimeException ex = new ObstructionException("Obstruction/Timeout detected! ncpu = " + ncpu + ", computation workers = " + cnt + "\r\n" + sb);

                throw ex;
            }
        } catch (InterruptedException ex) {
            throw new ObstructionException("Interrupted: " + ex);
        } finally {
            for (Scheduler.Worker w : workers) {
                w.dispose();
            }
        }
    }
    /**
     * Exception thrown if obstruction was detected.
     */
    public static final class ObstructionException extends RuntimeException {
        private static final long serialVersionUID = -6380717994471291795L;
        public ObstructionException(String message) {
            super(message);
        }
    }
}
