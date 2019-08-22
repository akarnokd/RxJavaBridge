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

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class RxJavaBridgeTest {

    @Test
    public void utilityClass() {
        TestHelper.checkUtilityClass(RxJavaBridge.class);
    }

    // -----------------------------------------------------------
    // Flowable
    // -----------------------------------------------------------

    @Test
    public void flowableV2toV3Normal() {
        RxJavaBridge.toV3Flowable(io.reactivex.Flowable.range(1, 5))
        .test()
        .assertResult(1, 2, 3, 4, 5);
    }

    @Test
    public void flowableV2toV3NormalConverter() {
        io.reactivex.Flowable.range(1, 5).as(RxJavaBridge.<Integer>toV3Flowable())
        .test()
        .assertResult(1, 2, 3, 4, 5);
    }

    @Test
    public void flowableV2toV3Error() {
        RxJavaBridge.toV3Flowable(io.reactivex.Flowable.error(new IOException()))
        .test()
        .assertFailure(IOException.class);
    }

    @Test
    public void flowableV2toV3Take() {
        RxJavaBridge.toV3Flowable(io.reactivex.Flowable.range(1, 5))
        .take(3)
        .test()
        .assertResult(1, 2, 3);
    }

    @Test
    public void flowableV3toV2Normal() {
        RxJavaBridge.toV2Flowable(io.reactivex.rxjava3.core.Flowable.range(1, 5))
        .test()
        .assertResult(1, 2, 3, 4, 5);
    }

    @Test
    public void flowableV3toV2NormalConverter() {
        io.reactivex.rxjava3.core.Flowable.range(1, 5).to(RxJavaBridge.<Integer>toV2Flowable())
        .test()
        .assertResult(1, 2, 3, 4, 5);
    }

    @Test
    public void flowableV3toV2Error() {
        RxJavaBridge.toV2Flowable(io.reactivex.rxjava3.core.Flowable.error(new IOException()))
        .test()
        .assertFailure(IOException.class);
    }

    @Test
    public void flowableV3toV2Take() {
        RxJavaBridge.toV2Flowable(io.reactivex.rxjava3.core.Flowable.range(1, 5))
        .take(3)
        .test()
        .assertResult(1, 2, 3);
    }

    // -----------------------------------------------------------
    // Observable
    // -----------------------------------------------------------

    @Test
    public void observableV2toV3Normal() {
        RxJavaBridge.toV3Observable(io.reactivex.Observable.range(1, 5))
        .test()
        .assertResult(1, 2, 3, 4, 5);
    }

    @Test
    public void observableV2toV3NormalConverter() {
        io.reactivex.Observable.range(1, 5).as(RxJavaBridge.<Integer>toV3Observable())
        .test()
        .assertResult(1, 2, 3, 4, 5);
    }

    @Test
    public void observableV2toV3Error() {
        RxJavaBridge.toV3Observable(io.reactivex.Observable.error(new IOException()))
        .test()
        .assertFailure(IOException.class);
    }

    @Test
    public void observableV2toV3Take() {
        RxJavaBridge.toV3Observable(io.reactivex.Observable.range(1, 5))
        .take(3)
        .test()
        .assertResult(1, 2, 3);
    }

    @Test
    public void observableV2toV3IsDisposed() {
        IsDisposed d = new IsDisposed();

        RxJavaBridge.toV3Observable(io.reactivex.subjects.PublishSubject.<Integer>create())
        .subscribe(d);

        assertFalse(d.before);
        assertTrue(d.after);
    }

    @Test
    public void observableV3toV2Normal() {
        RxJavaBridge.toV2Observable(io.reactivex.rxjava3.core.Observable.range(1, 5))
        .test()
        .assertResult(1, 2, 3, 4, 5);
    }

    @Test
    public void observableV3toV2NormalConverter() {
        io.reactivex.rxjava3.core.Observable.range(1, 5).to(RxJavaBridge.<Integer>toV2Observable())
        .test()
        .assertResult(1, 2, 3, 4, 5);
    }

    @Test
    public void observableV3toV2Error() {
        RxJavaBridge.toV2Observable(io.reactivex.rxjava3.core.Observable.error(new IOException()))
        .test()
        .assertFailure(IOException.class);
    }

    @Test
    public void observableV3toV2Take() {
        RxJavaBridge.toV2Observable(io.reactivex.rxjava3.core.Observable.range(1, 5))
        .take(3)
        .test()
        .assertResult(1, 2, 3);
    }


    @Test
    public void observableV3toV2IsDisposed() {
        IsDisposed d = new IsDisposed();

        RxJavaBridge.toV2Observable(io.reactivex.rxjava3.subjects.PublishSubject.<Integer>create())
        .subscribe(d);

        assertFalse(d.before);
        assertTrue(d.after);
    }

    // -----------------------------------------------------------
    // Maybe
    // -----------------------------------------------------------

    @Test
    public void maybeV2toV3Success() {
        RxJavaBridge.toV3Maybe(io.reactivex.Maybe.just(1))
        .test()
        .assertResult(1);
    }

    @Test
    public void maybeV2toV3Completed() {
        RxJavaBridge.toV3Maybe(io.reactivex.Maybe.empty())
        .test()
        .assertResult();
    }

    @Test
    public void maybeV2toV3SuccessConverter() {
        io.reactivex.Maybe.just(1).as(RxJavaBridge.<Integer>toV3Maybe())
        .test()
        .assertResult(1);
    }

    @Test
    public void maybeV2toV3Error() {
        RxJavaBridge.toV3Maybe(io.reactivex.Maybe.error(new IOException()))
        .test()
        .assertFailure(IOException.class);
    }

    @Test
    public void maybeV2toV3Dispose() {
        io.reactivex.subjects.MaybeSubject<Integer> subject = io.reactivex.subjects.MaybeSubject.create();
        io.reactivex.rxjava3.observers.TestObserver<Integer> to = RxJavaBridge.toV3Maybe(subject)
        .test();

        assertTrue(subject.hasObservers());

        to.dispose();

        assertFalse(subject.hasObservers());
    }

    @Test
    public void maybeV2toV3IsDisposed() {
        IsDisposed d = new IsDisposed();

        RxJavaBridge.toV3Maybe(io.reactivex.subjects.MaybeSubject.<Integer>create())
        .subscribe(d);

        assertFalse(d.before);
        assertTrue(d.after);
    }


    @Test
    public void maybeV3toV2Success() {
        RxJavaBridge.toV2Maybe(io.reactivex.rxjava3.core.Maybe.just(1))
        .test()
        .assertResult(1);
    }

    @Test
    public void maybeV3toV2Completed() {
        RxJavaBridge.toV2Maybe(io.reactivex.rxjava3.core.Maybe.empty())
        .test()
        .assertResult();
    }

    @Test
    public void maybeV3toV2SuccessConverter() {
        io.reactivex.rxjava3.core.Maybe.just(1).to(RxJavaBridge.<Integer>toV2Maybe())
        .test()
        .assertResult(1);
    }

    @Test
    public void maybeV3toV2Error() {
        RxJavaBridge.toV2Maybe(io.reactivex.rxjava3.core.Maybe.error(new IOException()))
        .test()
        .assertFailure(IOException.class);
    }

    @Test
    public void maybeV3toV2Dispose() {
        io.reactivex.rxjava3.subjects.MaybeSubject<Integer> subject = io.reactivex.rxjava3.subjects.MaybeSubject.create();
        io.reactivex.observers.TestObserver<Integer> to = RxJavaBridge.toV2Maybe(subject)
        .test();

        assertTrue(subject.hasObservers());

        to.dispose();

        assertFalse(subject.hasObservers());
    }


    @Test
    public void maybeV3toV2IsDisposed() {
        IsDisposed d = new IsDisposed();

        RxJavaBridge.toV2Maybe(io.reactivex.rxjava3.subjects.MaybeSubject.<Integer>create())
        .subscribe(d);

        assertFalse(d.before);
        assertTrue(d.after);
    }

    // -----------------------------------------------------------
    // Single
    // -----------------------------------------------------------

    @Test
    public void singleV2toV3Success() {
        RxJavaBridge.toV3Single(io.reactivex.Single.just(1))
        .test()
        .assertResult(1);
    }

    @Test
    public void singleV2toV3SuccessConverter() {
        io.reactivex.Single.just(1).as(RxJavaBridge.<Integer>toV3Single())
        .test()
        .assertResult(1);
    }

    @Test
    public void singleV2toV3Error() {
        RxJavaBridge.toV3Single(io.reactivex.Single.error(new IOException()))
        .test()
        .assertFailure(IOException.class);
    }

    @Test
    public void singleV2toV3Dispose() {
        io.reactivex.subjects.SingleSubject<Integer> subject = io.reactivex.subjects.SingleSubject.create();
        io.reactivex.rxjava3.observers.TestObserver<Integer> to = RxJavaBridge.toV3Single(subject)
        .test();

        assertTrue(subject.hasObservers());

        to.dispose();

        assertFalse(subject.hasObservers());
    }


    @Test
    public void singleV2toV3IsDisposed() {
        IsDisposed d = new IsDisposed();

        RxJavaBridge.toV3Single(io.reactivex.subjects.SingleSubject.<Integer>create())
        .subscribe(d);

        assertFalse(d.before);
        assertTrue(d.after);
    }

    @Test
    public void singleV3toV2Success() {
        RxJavaBridge.toV2Single(io.reactivex.rxjava3.core.Single.just(1))
        .test()
        .assertResult(1);
    }

    @Test
    public void singleV3toV2SuccessConverter() {
        io.reactivex.rxjava3.core.Single.just(1).to(RxJavaBridge.<Integer>toV2Single())
        .test()
        .assertResult(1);
    }

    @Test
    public void singleV3toV2Error() {
        RxJavaBridge.toV2Single(io.reactivex.rxjava3.core.Single.error(new IOException()))
        .test()
        .assertFailure(IOException.class);
    }

    @Test
    public void singleV3toV2Dispose() {
        io.reactivex.rxjava3.subjects.SingleSubject<Integer> subject = io.reactivex.rxjava3.subjects.SingleSubject.create();
        io.reactivex.observers.TestObserver<Integer> to = RxJavaBridge.toV2Single(subject)
        .test();

        assertTrue(subject.hasObservers());

        to.dispose();

        assertFalse(subject.hasObservers());
    }


    @Test
    public void singleV3toV2IsDisposed() {
        IsDisposed d = new IsDisposed();

        RxJavaBridge.toV2Single(io.reactivex.rxjava3.subjects.SingleSubject.<Integer>create())
        .subscribe(d);

        assertFalse(d.before);
        assertTrue(d.after);
    }

    // -----------------------------------------------------------
    // Completable
    // -----------------------------------------------------------

    @Test
    public void completableV2toV3Completed() {
        RxJavaBridge.toV3Completable(io.reactivex.Completable.complete())
        .test()
        .assertResult();
    }

    @Test
    public void completableV2toV3CompletedConverter() {
        io.reactivex.Completable.complete().as(RxJavaBridge.toV3Completable())
        .test()
        .assertResult();
    }

    @Test
    public void completableV2toV3Error() {
        RxJavaBridge.toV3Completable(io.reactivex.Completable.error(new IOException()))
        .test()
        .assertFailure(IOException.class);
    }

    @Test
    public void completableV2toV3Dispose() {
        io.reactivex.subjects.CompletableSubject subject = io.reactivex.subjects.CompletableSubject.create();
        io.reactivex.rxjava3.observers.TestObserver<Void> to = RxJavaBridge.toV3Completable(subject)
        .test();

        assertTrue(subject.hasObservers());

        to.dispose();

        assertFalse(subject.hasObservers());
    }

    @Test
    public void completableV2toV3IsDisposed() {
        IsDisposed d = new IsDisposed();

        RxJavaBridge.toV3Completable(io.reactivex.subjects.CompletableSubject.create())
        .subscribe(d);

        assertFalse(d.before);
        assertTrue(d.after);
    }

    @Test
    public void completableV3toV2Completed() {
        RxJavaBridge.toV2Completable(io.reactivex.rxjava3.core.Completable.complete())
        .test()
        .assertResult();
    }

    @Test
    public void completableV3toV2SuccessConverter() {
        io.reactivex.rxjava3.core.Completable.complete().to(RxJavaBridge.toV2Completable())
        .test()
        .assertResult();
    }

    @Test
    public void completableV3toV2Error() {
        RxJavaBridge.toV2Completable(io.reactivex.rxjava3.core.Completable.error(new IOException()))
        .test()
        .assertFailure(IOException.class);
    }

    @Test
    public void completableV3toV2Dispose() {
        io.reactivex.rxjava3.subjects.CompletableSubject subject = io.reactivex.rxjava3.subjects.CompletableSubject.create();
        io.reactivex.observers.TestObserver<Void> to = RxJavaBridge.toV2Completable(subject)
        .test();

        assertTrue(subject.hasObservers());

        to.dispose();

        assertFalse(subject.hasObservers());
    }

    @Test
    public void completableV3toV2IsDisposed() {
        IsDisposed d = new IsDisposed();

        RxJavaBridge.toV2Completable(io.reactivex.rxjava3.subjects.CompletableSubject.create())
        .subscribe(d);

        assertFalse(d.before);
        assertTrue(d.after);
    }

    // -----------------------------------------------------------
    // Disposable
    // -----------------------------------------------------------

    @Test
    public void disposableV2toV3() {
        io.reactivex.disposables.Disposable empty = io.reactivex.disposables.Disposables.empty();
        io.reactivex.rxjava3.disposables.Disposable disposable = RxJavaBridge.toV3Disposable(empty);

        assertFalse(disposable.isDisposed());
        assertFalse(empty.isDisposed());

        disposable.dispose();

        assertTrue(disposable.isDisposed());
        assertTrue(empty.isDisposed());
    }

    @Test
    public void disposableV2toV3InternalDisposed() {
        io.reactivex.disposables.Disposable empty = io.reactivex.internal.disposables.DisposableHelper.DISPOSED;
        io.reactivex.rxjava3.disposables.Disposable disposable = RxJavaBridge.toV3Disposable(empty);

        assertSame(disposable, io.reactivex.rxjava3.internal.disposables.DisposableHelper.DISPOSED);
    }

    @Test
    public void disposableV2toV3InternalEmpty() {
        io.reactivex.disposables.Disposable empty = io.reactivex.internal.disposables.EmptyDisposable.INSTANCE;
        io.reactivex.rxjava3.disposables.Disposable disposable = RxJavaBridge.toV3Disposable(empty);

        assertSame(disposable, io.reactivex.rxjava3.internal.disposables.EmptyDisposable.INSTANCE);
    }

    @Test
    public void disposableV3toV2() {
        io.reactivex.rxjava3.disposables.Disposable empty = io.reactivex.rxjava3.disposables.Disposables.empty();
        io.reactivex.disposables.Disposable disposable = RxJavaBridge.toV2Disposable(empty);

        assertFalse(disposable.isDisposed());
        assertFalse(empty.isDisposed());

        disposable.dispose();

        assertTrue(disposable.isDisposed());
        assertTrue(empty.isDisposed());
    }

    @Test
    public void disposableV3toV2InternalDisposed() {
        io.reactivex.rxjava3.disposables.Disposable empty = io.reactivex.rxjava3.internal.disposables.DisposableHelper.DISPOSED;
        io.reactivex.disposables.Disposable disposable = RxJavaBridge.toV2Disposable(empty);

        assertSame(disposable, io.reactivex.internal.disposables.DisposableHelper.DISPOSED);
    }

    @Test
    public void disposableV3toV2InternalEmpty() {
        io.reactivex.rxjava3.disposables.Disposable empty = io.reactivex.rxjava3.internal.disposables.EmptyDisposable.INSTANCE;
        io.reactivex.disposables.Disposable disposable = RxJavaBridge.toV2Disposable(empty);

        assertSame(disposable, io.reactivex.internal.disposables.EmptyDisposable.INSTANCE);
    }

    // -----------------------------------------------------------
    // Disposable
    // -----------------------------------------------------------

    @Test
    public void schedulerUseV2toV3() {
        final io.reactivex.schedulers.TestScheduler scheduler = new io.reactivex.schedulers.TestScheduler();
        io.reactivex.functions.Function<io.reactivex.Scheduler, io.reactivex.Scheduler> handler = new io.reactivex.functions.Function<io.reactivex.Scheduler, io.reactivex.Scheduler>() {
            @Override
            public io.reactivex.Scheduler apply(io.reactivex.Scheduler s) throws Exception {
                return scheduler;
            }
        };
        final AtomicInteger count = new AtomicInteger();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                count.getAndIncrement();
            }
        };

        try {
            io.reactivex.plugins.RxJavaPlugins.setComputationSchedulerHandler(handler);
            io.reactivex.plugins.RxJavaPlugins.setIoSchedulerHandler(handler);
            io.reactivex.plugins.RxJavaPlugins.setSingleSchedulerHandler(handler);
            io.reactivex.plugins.RxJavaPlugins.setNewThreadSchedulerHandler(handler);

            RxJavaBridge.startUsingV2Schedulers();

            io.reactivex.rxjava3.schedulers.Schedulers.shutdown();
            io.reactivex.rxjava3.schedulers.Schedulers.start();

            checkScheduler(scheduler, io.reactivex.rxjava3.schedulers.Schedulers.computation(), r);
            checkScheduler(scheduler, io.reactivex.rxjava3.schedulers.Schedulers.io(), r);
            checkScheduler(scheduler, io.reactivex.rxjava3.schedulers.Schedulers.single(), r);
            checkScheduler(scheduler, io.reactivex.rxjava3.schedulers.Schedulers.newThread(), r);

            assertEquals(4 * 5 * 2, count.get());

            RxJavaBridge.stopUsingV2Schedulers();

            assertNull(io.reactivex.rxjava3.plugins.RxJavaPlugins.getComputationSchedulerHandler());
            assertNull(io.reactivex.rxjava3.plugins.RxJavaPlugins.getIoSchedulerHandler());
            assertNull(io.reactivex.rxjava3.plugins.RxJavaPlugins.getSingleSchedulerHandler());
            assertNull(io.reactivex.rxjava3.plugins.RxJavaPlugins.getNewThreadSchedulerHandler());
        } finally {
            io.reactivex.plugins.RxJavaPlugins.reset();
            io.reactivex.rxjava3.plugins.RxJavaPlugins.reset();
        }
    }

    @Test
    public void schedulerUseV3toV2() {
        final io.reactivex.rxjava3.schedulers.TestScheduler scheduler = new io.reactivex.rxjava3.schedulers.TestScheduler();
        io.reactivex.rxjava3.functions.Function<io.reactivex.rxjava3.core.Scheduler, io.reactivex.rxjava3.core.Scheduler> handler = new io.reactivex.rxjava3.functions.Function<io.reactivex.rxjava3.core.Scheduler, io.reactivex.rxjava3.core.Scheduler>() {
            @Override
            public io.reactivex.rxjava3.core.Scheduler apply(io.reactivex.rxjava3.core.Scheduler s) throws Exception {
                return scheduler;
            }
        };
        final AtomicInteger count = new AtomicInteger();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                count.getAndIncrement();
            }
        };

        try {
            io.reactivex.rxjava3.plugins.RxJavaPlugins.setComputationSchedulerHandler(handler);
            io.reactivex.rxjava3.plugins.RxJavaPlugins.setIoSchedulerHandler(handler);
            io.reactivex.rxjava3.plugins.RxJavaPlugins.setSingleSchedulerHandler(handler);
            io.reactivex.rxjava3.plugins.RxJavaPlugins.setNewThreadSchedulerHandler(handler);

            RxJavaBridge.startUsingV3Schedulers();

            io.reactivex.schedulers.Schedulers.shutdown();
            io.reactivex.schedulers.Schedulers.start();

            checkScheduler(scheduler, io.reactivex.schedulers.Schedulers.computation(), r);
            checkScheduler(scheduler, io.reactivex.schedulers.Schedulers.io(), r);
            checkScheduler(scheduler, io.reactivex.schedulers.Schedulers.single(), r);
            checkScheduler(scheduler, io.reactivex.schedulers.Schedulers.newThread(), r);

            assertEquals(4 * 5 * 2, count.get());

            RxJavaBridge.stopUsingV3Schedulers();

            assertNull(io.reactivex.plugins.RxJavaPlugins.getComputationSchedulerHandler());
            assertNull(io.reactivex.plugins.RxJavaPlugins.getIoSchedulerHandler());
            assertNull(io.reactivex.plugins.RxJavaPlugins.getSingleSchedulerHandler());
            assertNull(io.reactivex.plugins.RxJavaPlugins.getNewThreadSchedulerHandler());
        } finally {
            io.reactivex.plugins.RxJavaPlugins.reset();
            io.reactivex.rxjava3.plugins.RxJavaPlugins.reset();
        }
    }

    static void checkScheduler(io.reactivex.schedulers.TestScheduler tester, io.reactivex.rxjava3.core.Scheduler scheduler, Runnable r) {
        assertEquals(tester.now(TimeUnit.SECONDS), scheduler.now(TimeUnit.SECONDS));

        scheduler.scheduleDirect(r);
        scheduler.scheduleDirect(r, 1, TimeUnit.SECONDS);

        io.reactivex.rxjava3.disposables.Disposable d = scheduler.schedulePeriodicallyDirect(r, 2, 1, TimeUnit.SECONDS);

        tester.advanceTimeBy(4, TimeUnit.SECONDS);

        assertEquals(tester.now(TimeUnit.SECONDS), scheduler.now(TimeUnit.SECONDS));

        d.dispose();

        io.reactivex.rxjava3.core.Scheduler.Worker worker = scheduler.createWorker();
        try {
            assertEquals(tester.now(TimeUnit.SECONDS), worker.now(TimeUnit.SECONDS));

            worker.schedule(r);
            worker.schedule(r, 1, TimeUnit.SECONDS);

            worker.schedulePeriodically(r, 2, 1, TimeUnit.SECONDS);

            tester.advanceTimeBy(4, TimeUnit.SECONDS);

            assertEquals(tester.now(TimeUnit.SECONDS), scheduler.now(TimeUnit.SECONDS));
        } finally {
            worker.dispose();
            assertTrue(worker.isDisposed());
        }
    }

    static void checkScheduler(io.reactivex.rxjava3.schedulers.TestScheduler tester, io.reactivex.Scheduler scheduler, Runnable r) {
        assertEquals(tester.now(TimeUnit.SECONDS), scheduler.now(TimeUnit.SECONDS));

        scheduler.scheduleDirect(r);
        scheduler.scheduleDirect(r, 1, TimeUnit.SECONDS);

        io.reactivex.disposables.Disposable d = scheduler.schedulePeriodicallyDirect(r, 2, 1, TimeUnit.SECONDS);

        tester.advanceTimeBy(4, TimeUnit.SECONDS);

        d.dispose();

        assertEquals(tester.now(TimeUnit.SECONDS), scheduler.now(TimeUnit.SECONDS));

        io.reactivex.Scheduler.Worker worker = scheduler.createWorker();
        try {
            assertEquals(tester.now(TimeUnit.SECONDS), worker.now(TimeUnit.SECONDS));

            worker.schedule(r);
            worker.schedule(r, 1, TimeUnit.SECONDS);

            worker.schedulePeriodically(r, 2, 1, TimeUnit.SECONDS);

            tester.advanceTimeBy(4, TimeUnit.SECONDS);

            assertEquals(tester.now(TimeUnit.SECONDS), scheduler.now(TimeUnit.SECONDS));
        } finally {
            worker.dispose();
            assertTrue(worker.isDisposed());
        }
    }

    static final class IsDisposed implements
    io.reactivex.Observer<Integer>,
    io.reactivex.rxjava3.core.Observer<Integer>,
    io.reactivex.MaybeObserver<Integer>,
    io.reactivex.rxjava3.core.MaybeObserver<Integer>,
    io.reactivex.SingleObserver<Integer>,
    io.reactivex.rxjava3.core.SingleObserver<Integer>,
    io.reactivex.CompletableObserver,
    io.reactivex.rxjava3.core.CompletableObserver
    {

        boolean before;

        boolean after;

        @Override
        public void onSuccess(Integer t) {
        }

        @Override
        public void onSubscribe(io.reactivex.rxjava3.disposables.Disposable d) {
            before = d.isDisposed();
            d.dispose();
            after = d.isDisposed();
        }

        @Override
        public void onSubscribe(io.reactivex.disposables.Disposable d) {
            before = d.isDisposed();
            d.dispose();
            after = d.isDisposed();
        }

        @Override
        public void onNext(Integer t) {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {
        }
    }
}
