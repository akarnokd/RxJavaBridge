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

/**
 * Utility class to convert between RxJava 2 and RxJava 3 components.
 * @since 3.0.0
 */
public final class RxJavaBridge {

    private RxJavaBridge() {
        throw new IllegalStateException("No instances!");
    }

    // -----------------------------------------------------
    // Flowable conversions
    // -----------------------------------------------------

    /**
     * Wraps a V3 Flowable and exposes it as a V2 Flowable.
     * @param <T> the element type of the sequence
     * @param source the source V3 Flowable
     * @return the wrapper V2 Flowable
     */
    public static <T> io.reactivex.Flowable<T> toV2Flowable(io.reactivex.rxjava3.core.Flowable<T> source) {
        java.util.Objects.requireNonNull(source, "source is null");
        return io.reactivex.plugins.RxJavaPlugins.onAssembly(new FlowableV3toV2<>(source));
    }

    /**
     * Wraps a V2 Flowable and exposes it as a V3 Flowable.
     * @param <T> the element type of the sequence
     * @param source the source V2 Flowable
     * @return the wrapper V3 Flowable
     */
    public static <T> io.reactivex.rxjava3.core.Flowable<T> toV3Flowable(io.reactivex.Flowable<T> source) {
        java.util.Objects.requireNonNull(source, "source is null");
        return io.reactivex.rxjava3.plugins.RxJavaPlugins.onAssembly(new FlowableV2toV3<>(source));
    }

    /**
     * Returns the shared singleton instance of a V2 Flowable converter
     * that when applied in V2 {@link io.reactivex.Flowable#as(io.reactivex.FlowableConverter)}-based
     * fluent conversions, produces a V3 Flowable instance.
     * @param <T> the element type of the sequence
     * @return the shared singleton converter instance
     */
    @SuppressWarnings("unchecked")
    public static <T> io.reactivex.FlowableConverter<T, io.reactivex.rxjava3.core.Flowable<T>> toV3Flowable() {
        return (FlowableV2toV3<T>)FlowableV2toV3.CONVERTER;
    }

    /**
     * Returns the shared singleton instance of a V3 Flowable converter
     * that when applied in V3 {@link io.reactivex.rxjava3.core.Flowable#to(io.reactivex.rxjava3.core.FlowableConverter)}-based
     * fluent conversions, produces a V2 Flowable instance.
     * @param <T> the element type of the sequence
     * @return the shared singleton converter instance
     */
    @SuppressWarnings("unchecked")
    public static <T> io.reactivex.rxjava3.core.FlowableConverter<T, io.reactivex.Flowable<T>> toV2Flowable() {
        return (FlowableV3toV2<T>)FlowableV3toV2.CONVERTER;
    }

    // -----------------------------------------------------
    // Observable conversions
    // -----------------------------------------------------

    /**
     * Wraps a V3 Observable and exposes it as a V2 Observable.
     * @param <T> the element type of the sequence
     * @param source the source V3 Observable
     * @return the wrapper V2 Observable
     */
    public static <T> io.reactivex.Observable<T> toV2Observable(io.reactivex.rxjava3.core.Observable<T> source) {
        java.util.Objects.requireNonNull(source, "source is null");
        return io.reactivex.plugins.RxJavaPlugins.onAssembly(new ObservableV3toV2<>(source));
    }

    /**
     * Wraps a V2 Observable and exposes it as a V3 Observable.
     * @param <T> the element type of the sequence
     * @param source the source V2 Observable
     * @return the wrapper V3 Observable
     */
    public static <T> io.reactivex.rxjava3.core.Observable<T> toV3Observable(io.reactivex.Observable<T> source) {
        java.util.Objects.requireNonNull(source, "source is null");
        return io.reactivex.rxjava3.plugins.RxJavaPlugins.onAssembly(new ObservableV2toV3<>(source));
    }

    /**
     * Returns the shared singleton instance of a V2 Observable converter
     * that when applied in V2 {@link io.reactivex.Observable#as(io.reactivex.ObservableConverter)}-based
     * fluent conversions, produces a V3 Observable instance.
     * @param <T> the element type of the sequence
     * @return the shared singleton converter instance
     */
    @SuppressWarnings("unchecked")
    public static <T> io.reactivex.ObservableConverter<T, io.reactivex.rxjava3.core.Observable<T>> toV3Observable() {
        return (ObservableV2toV3<T>)ObservableV2toV3.CONVERTER;
    }

    /**
     * Returns the shared singleton instance of a V3 Observable converter
     * that when applied in V3 {@link io.reactivex.rxjava3.core.Observable#to(io.reactivex.rxjava3.core.ObservableConverter)}-based
     * fluent conversions, produces a V2 Observable instance.
     * @param <T> the element type of the sequence
     * @return the shared singleton converter instance
     */
    @SuppressWarnings("unchecked")
    public static <T> io.reactivex.rxjava3.core.ObservableConverter<T, io.reactivex.Observable<T>> toV2Observable() {
        return (ObservableV3toV2<T>)ObservableV3toV2.CONVERTER;
    }

    // -----------------------------------------------------
    // Maybe conversions
    // -----------------------------------------------------

    /**
     * Wraps a V3 Maybe and exposes it as a V2 Maybe.
     * @param <T> the element type of the sequence
     * @param source the source V3 Maybe
     * @return the wrapper V2 Maybe
     */
    public static <T> io.reactivex.Maybe<T> toV2Maybe(io.reactivex.rxjava3.core.Maybe<T> source) {
        java.util.Objects.requireNonNull(source, "source is null");
        return io.reactivex.plugins.RxJavaPlugins.onAssembly(new MaybeV3toV2<>(source));
    }

    /**
     * Wraps a V2 Maybe and exposes it as a V3 Maybe.
     * @param <T> the element type of the sequence
     * @param source the source V2 Maybe
     * @return the wrapper V3 Maybe
     */
    public static <T> io.reactivex.rxjava3.core.Maybe<T> toV3Maybe(io.reactivex.Maybe<T> source) {
        java.util.Objects.requireNonNull(source, "source is null");
        return io.reactivex.rxjava3.plugins.RxJavaPlugins.onAssembly(new MaybeV2toV3<>(source));
    }

    /**
     * Returns the shared singleton instance of a V2 Maybe converter
     * that when applied in V2 {@link io.reactivex.Maybe#as(io.reactivex.MaybeConverter)}-based
     * fluent conversions, produces a V3 Maybe instance.
     * @param <T> the element type of the sequence
     * @return the shared singleton converter instance
     */
    @SuppressWarnings("unchecked")
    public static <T> io.reactivex.MaybeConverter<T, io.reactivex.rxjava3.core.Maybe<T>> toV3Maybe() {
        return (MaybeV2toV3<T>)MaybeV2toV3.CONVERTER;
    }

    /**
     * Returns the shared singleton instance of a V3 Maybe converter
     * that when applied in V3 {@link io.reactivex.rxjava3.core.Maybe#to(io.reactivex.rxjava3.core.MaybeConverter)}-based
     * fluent conversions, produces a V2 Maybe instance.
     * @param <T> the element type of the sequence
     * @return the shared singleton converter instance
     */
    @SuppressWarnings("unchecked")
    public static <T> io.reactivex.rxjava3.core.MaybeConverter<T, io.reactivex.Maybe<T>> toV2Maybe() {
        return (MaybeV3toV2<T>)MaybeV3toV2.CONVERTER;
    }

    // -----------------------------------------------------
    // Single conversions
    // -----------------------------------------------------

    /**
     * Wraps a V3 Single and exposes it as a V2 Single.
     * @param <T> the element type of the sequence
     * @param source the source V3 Single
     * @return the wrapper V2 Single
     */
    public static <T> io.reactivex.Single<T> toV2Single(io.reactivex.rxjava3.core.Single<T> source) {
        java.util.Objects.requireNonNull(source, "source is null");
        return io.reactivex.plugins.RxJavaPlugins.onAssembly(new SingleV3toV2<>(source));
    }

    /**
     * Wraps a V2 Single and exposes it as a V3 Single.
     * @param <T> the element type of the sequence
     * @param source the source V2 Single
     * @return the wrapper V3 Single
     */
    public static <T> io.reactivex.rxjava3.core.Single<T> toV3Single(io.reactivex.Single<T> source) {
        java.util.Objects.requireNonNull(source, "source is null");
        return io.reactivex.rxjava3.plugins.RxJavaPlugins.onAssembly(new SingleV2toV3<>(source));
    }

    /**
     * Returns the shared singleton instance of a V2 Single converter
     * that when applied in V2 {@link io.reactivex.Single#as(io.reactivex.SingleConverter)}-based
     * fluent conversions, produces a V3 Single instance.
     * @param <T> the element type of the sequence
     * @return the shared singleton converter instance
     */
    @SuppressWarnings("unchecked")
    public static <T> io.reactivex.SingleConverter<T, io.reactivex.rxjava3.core.Single<T>> toV3Single() {
        return (SingleV2toV3<T>)SingleV2toV3.CONVERTER;
    }

    /**
     * Returns the shared singleton instance of a V3 Single converter
     * that when applied in V3 {@link io.reactivex.rxjava3.core.Single#to(io.reactivex.rxjava3.core.SingleConverter)}-based
     * fluent conversions, produces a V2 Single instance.
     * @param <T> the element type of the sequence
     * @return the shared singleton converter instance
     */
    @SuppressWarnings("unchecked")
    public static <T> io.reactivex.rxjava3.core.SingleConverter<T, io.reactivex.Single<T>> toV2Single() {
        return (SingleV3toV2<T>)SingleV3toV2.CONVERTER;
    }

    // -----------------------------------------------------
    // Completable conversions
    // -----------------------------------------------------

    /**
     * Wraps a V3 Completable and exposes it as a V2 Completable.
     * @param source the source V3 Completable
     * @return the wrapper V2 Completable
     */
    public static io.reactivex.Completable toV2Completable(io.reactivex.rxjava3.core.Completable source) {
        java.util.Objects.requireNonNull(source, "source is null");
        return io.reactivex.plugins.RxJavaPlugins.onAssembly(new CompletableV3toV2(source));
    }

    /**
     * Wraps a V2 Completable and exposes it as a V3 Completable.
     * @param source the source V2 Completable
     * @return the wrapper V3 Completable
     */
    public static io.reactivex.rxjava3.core.Completable toV3Completable(io.reactivex.Completable source) {
        java.util.Objects.requireNonNull(source, "source is null");
        return io.reactivex.rxjava3.plugins.RxJavaPlugins.onAssembly(new CompletableV2toV3(source));
    }

    /**
     * Returns the shared singleton instance of a V2 Completable converter
     * that when applied in V2 {@link io.reactivex.Completable#as(io.reactivex.CompletableConverter)}-based
     * fluent conversions, produces a V3 Completable instance.
     * @return the shared singleton converter instance
     */
    public static io.reactivex.CompletableConverter<io.reactivex.rxjava3.core.Completable> toV3Completable() {
        return CompletableV2toV3.CONVERTER;
    }

    /**
     * Returns the shared singleton instance of a V3 Completable converter
     * that when applied in V3 {@link io.reactivex.rxjava3.core.Completable#to(io.reactivex.rxjava3.core.CompletableConverter)}-based
     * fluent conversions, produces a V2 Completable instance.
     * @return the shared singleton converter instance
     */
    public static io.reactivex.rxjava3.core.CompletableConverter<io.reactivex.Completable> toV2Completable() {
        return CompletableV3toV2.CONVERTER;
    }

    // -----------------------------------------------------
    // Disposable conversions
    // -----------------------------------------------------

    /**
     * Wraps a V3 Disposable and exposes it as a V2 Disposable.
     * @param disposable the source V3 Disposable
     * @return the wrapper V2 Disposable
     */
    public static io.reactivex.disposables.Disposable toV2Disposable(io.reactivex.rxjava3.disposables.Disposable disposable) {
        java.util.Objects.requireNonNull(disposable, "disposable is null");
        return DisposableV3toV2.wrap(disposable);
    }

    /**
     * Wraps a V2 Disposable and exposes it as a V3 Disposable.
     * @param disposable the source V2 Disposable
     * @return the wrapper V3 Disposable
     */
    public static io.reactivex.rxjava3.disposables.Disposable toV3Disposable(io.reactivex.disposables.Disposable disposable) {
        java.util.Objects.requireNonNull(disposable, "disposable is null");
        return DisposableV2toV3.wrap(disposable);
    }

    // -----------------------------------------------------
    // Scheduler conversions
    // -----------------------------------------------------

    /**
     * Wraps a V3 Scheduler and exposes it as a V2 Scheduler.
     * @param scheduler the source V3 Scheduler
     * @return the wrapper V2 Scheduler
     */
    public static io.reactivex.Scheduler toV2Scheduler(io.reactivex.rxjava3.core.Scheduler scheduler) {
        java.util.Objects.requireNonNull(scheduler, "scheduler is null");
        return new SchedulerV3toV2(scheduler);
    }

    /**
     * Wraps a V2 Scheduler and exposes it as a V3 Scheduler.
     * @param scheduler the source V2 Scheduler
     * @return the wrapper V3 Scheduler
     */
    public static io.reactivex.rxjava3.core.Scheduler toV3Scheduler(io.reactivex.Scheduler scheduler) {
        java.util.Objects.requireNonNull(scheduler, "scheduler is null");
        return new SchedulerV2toV3(scheduler);
    }

    /**
     * Wraps all standard V2 Schedulers into V3 Schedulers and installs
     * scheduler hooks in the V3 RxJavaPlugins so that both RxJava 2 and
     * RxJava 3 use the same backing scheduler implementations.
     */
    public static void startUsingV2Schedulers() {
        final io.reactivex.rxjava3.core.Scheduler computation = toV3Scheduler(io.reactivex.schedulers.Schedulers.computation());
        final io.reactivex.rxjava3.core.Scheduler ios = toV3Scheduler(io.reactivex.schedulers.Schedulers.io());
        final io.reactivex.rxjava3.core.Scheduler single = toV3Scheduler(io.reactivex.schedulers.Schedulers.single());
        final io.reactivex.rxjava3.core.Scheduler newThread = toV3Scheduler(io.reactivex.schedulers.Schedulers.newThread());

        io.reactivex.rxjava3.plugins.RxJavaPlugins.setComputationSchedulerHandler(new io.reactivex.rxjava3.functions.Function<io.reactivex.rxjava3.core.Scheduler, io.reactivex.rxjava3.core.Scheduler>() {
            @Override
            public io.reactivex.rxjava3.core.Scheduler apply(io.reactivex.rxjava3.core.Scheduler v) throws Throwable {
                return computation;
            }
        });
        io.reactivex.rxjava3.plugins.RxJavaPlugins.setIoSchedulerHandler(new io.reactivex.rxjava3.functions.Function<io.reactivex.rxjava3.core.Scheduler, io.reactivex.rxjava3.core.Scheduler>() {
            @Override
            public io.reactivex.rxjava3.core.Scheduler apply(io.reactivex.rxjava3.core.Scheduler v) throws Throwable {
                return ios;
            }
        });
        io.reactivex.rxjava3.plugins.RxJavaPlugins.setSingleSchedulerHandler(new io.reactivex.rxjava3.functions.Function<io.reactivex.rxjava3.core.Scheduler, io.reactivex.rxjava3.core.Scheduler>() {
            @Override
            public io.reactivex.rxjava3.core.Scheduler apply(io.reactivex.rxjava3.core.Scheduler v) throws Throwable {
                return single;
            }
        });
        io.reactivex.rxjava3.plugins.RxJavaPlugins.setNewThreadSchedulerHandler(new io.reactivex.rxjava3.functions.Function<io.reactivex.rxjava3.core.Scheduler, io.reactivex.rxjava3.core.Scheduler>() {
            @Override
            public io.reactivex.rxjava3.core.Scheduler apply(io.reactivex.rxjava3.core.Scheduler v) throws Throwable {
                return newThread;
            }
        });
    }

    /**
     * Stop using the V2 standard schedulers by resetting the V3 scheduler hooks to their
     * default (null) handlers in the V3 RxJavaPlugins.
     */
    public static void stopUsingV2Schedulers() {
        io.reactivex.rxjava3.plugins.RxJavaPlugins.setComputationSchedulerHandler(null);
        io.reactivex.rxjava3.plugins.RxJavaPlugins.setIoSchedulerHandler(null);
        io.reactivex.rxjava3.plugins.RxJavaPlugins.setSingleSchedulerHandler(null);
        io.reactivex.rxjava3.plugins.RxJavaPlugins.setNewThreadSchedulerHandler(null);
    }

    /**
     * Wraps all standard V3 Schedulers into V2 Schedulers and installs
     * scheduler hooks in the V2 RxJavaPlugins so that both RxJava 2 and
     * RxJava 3 use the same backing scheduler implementations.
     */
    public static void startUsingV3Schedulers() {
        final io.reactivex.Scheduler computation = toV2Scheduler(io.reactivex.rxjava3.schedulers.Schedulers.computation());
        final io.reactivex.Scheduler ios = toV2Scheduler(io.reactivex.rxjava3.schedulers.Schedulers.io());
        final io.reactivex.Scheduler single = toV2Scheduler(io.reactivex.rxjava3.schedulers.Schedulers.single());
        final io.reactivex.Scheduler newThread = toV2Scheduler(io.reactivex.rxjava3.schedulers.Schedulers.newThread());

        io.reactivex.plugins.RxJavaPlugins.setComputationSchedulerHandler(new io.reactivex.functions.Function<io.reactivex.Scheduler, io.reactivex.Scheduler>() {
            @Override
            public io.reactivex.Scheduler apply(io.reactivex.Scheduler v) throws Exception {
                return computation;
            }
        });
        io.reactivex.plugins.RxJavaPlugins.setIoSchedulerHandler(new io.reactivex.functions.Function<io.reactivex.Scheduler, io.reactivex.Scheduler>() {
            @Override
            public io.reactivex.Scheduler apply(io.reactivex.Scheduler v) throws Exception {
                return ios;
            }
        });
        io.reactivex.plugins.RxJavaPlugins.setSingleSchedulerHandler(new io.reactivex.functions.Function<io.reactivex.Scheduler, io.reactivex.Scheduler>() {
            @Override
            public io.reactivex.Scheduler apply(io.reactivex.Scheduler v) throws Exception {
                return single;
            }
        });
        io.reactivex.plugins.RxJavaPlugins.setNewThreadSchedulerHandler(new io.reactivex.functions.Function<io.reactivex.Scheduler, io.reactivex.Scheduler>() {
            @Override
            public io.reactivex.Scheduler apply(io.reactivex.Scheduler v) throws Exception {
                return newThread;
            }
        });
    }

    /**
     * Stop using the V3 standard schedulers by resetting the V2 scheduler hooks to their
     * default (null) handlers in the V2 RxJavaPlugins.
     */
    public static void stopUsingV3Schedulers() {
        io.reactivex.plugins.RxJavaPlugins.setComputationSchedulerHandler(null);
        io.reactivex.plugins.RxJavaPlugins.setIoSchedulerHandler(null);
        io.reactivex.plugins.RxJavaPlugins.setSingleSchedulerHandler(null);
        io.reactivex.plugins.RxJavaPlugins.setNewThreadSchedulerHandler(null);
    }
}
