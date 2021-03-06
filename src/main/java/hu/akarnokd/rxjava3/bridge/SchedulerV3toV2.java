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

import java.util.concurrent.TimeUnit;

final class SchedulerV3toV2 extends io.reactivex.Scheduler {

    final io.reactivex.rxjava3.core.Scheduler scheduler;

    SchedulerV3toV2(io.reactivex.rxjava3.core.Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public io.reactivex.disposables.Disposable scheduleDirect(Runnable run) {
        return DisposableV3toV2.wrap(scheduler.scheduleDirect(run));
    }

    @Override
    public io.reactivex.disposables.Disposable scheduleDirect(Runnable run, long delay, TimeUnit unit) {
        return DisposableV3toV2.wrap(scheduler.scheduleDirect(run, delay, unit));
    }

    @Override
    public io.reactivex.disposables.Disposable schedulePeriodicallyDirect(Runnable run, long initialDelay, long period, TimeUnit unit) {
        return DisposableV3toV2.wrap(scheduler.schedulePeriodicallyDirect(run, initialDelay, period, unit));
    }

    @Override
    public void start() {
        scheduler.start();
    }

    @Override
    public void shutdown() {
        scheduler.shutdown();
    }

    @Override
    public long now(TimeUnit unit) {
        return scheduler.now(unit);
    }

    @Override
    public Worker createWorker() {
        return new WorkerV3toV2(scheduler.createWorker());
    }

    static final class WorkerV3toV2 extends Worker {

        final io.reactivex.rxjava3.core.Scheduler.Worker worker;

        WorkerV3toV2(io.reactivex.rxjava3.core.Scheduler.Worker worker) {
            this.worker = worker;
        }

        @Override
        public io.reactivex.disposables.Disposable schedule(Runnable run) {
            return DisposableV3toV2.wrap(worker.schedule(run));
        }

        @Override
        public io.reactivex.disposables.Disposable schedule(Runnable run, long delay, TimeUnit unit) {
            return DisposableV3toV2.wrap(worker.schedule(run, delay, unit));
        }

        @Override
        public io.reactivex.disposables.Disposable schedulePeriodically(Runnable run, long initialDelay, long period, TimeUnit unit) {
            return DisposableV3toV2.wrap(worker.schedulePeriodically(run, initialDelay, period, unit));
        }

        @Override
        public long now(TimeUnit unit) {
            return worker.now(unit);
        }

        @Override
        public boolean isDisposed() {
            return worker.isDisposed();
        }

        @Override
        public void dispose() {
            worker.dispose();
        }
    }
}
