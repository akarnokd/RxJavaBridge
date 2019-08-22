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

final class CompletableV3toV2 extends io.reactivex.Completable
implements io.reactivex.rxjava3.core.CompletableConverter<io.reactivex.Completable> {

    final io.reactivex.rxjava3.core.Completable source;

    static final CompletableV3toV2 CONVERTER = new CompletableV3toV2(null);

    CompletableV3toV2(io.reactivex.rxjava3.core.Completable source) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(io.reactivex.CompletableObserver s) {
        source.subscribe(new CompletableObserverV2toV3(s));
    }

    @Override
    public io.reactivex.Completable apply(io.reactivex.rxjava3.core.Completable upstream) {
        return new CompletableV3toV2(upstream);
    }

    static final class CompletableObserverV2toV3
    implements io.reactivex.rxjava3.core.CompletableObserver, io.reactivex.disposables.Disposable {

        final io.reactivex.CompletableObserver downstream;

        io.reactivex.rxjava3.disposables.Disposable upstream;

        CompletableObserverV2toV3(io.reactivex.CompletableObserver downstream) {
            this.downstream = downstream;
        }

        @Override
        public void onSubscribe(io.reactivex.rxjava3.disposables.Disposable d) {
            this.upstream = d;
            downstream.onSubscribe(this);
        }

        @Override
        public void onError(Throwable e) {
            downstream.onError(e);
        }

        @Override
        public void onComplete() {
            downstream.onComplete();
        }

        @Override
        public boolean isDisposed() {
            return upstream.isDisposed();
        }

        @Override
        public void dispose() {
            upstream.dispose();
        }
    }
}
