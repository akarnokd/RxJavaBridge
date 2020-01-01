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

final class ObservableV2toV3<T> extends io.reactivex.rxjava3.core.Observable<T>
implements io.reactivex.ObservableConverter<T, io.reactivex.rxjava3.core.Observable<T>> {

    final io.reactivex.Observable<T> source;

    static final ObservableV2toV3<Object> CONVERTER = new ObservableV2toV3<>(null);

    ObservableV2toV3(io.reactivex.Observable<T> source) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(io.reactivex.rxjava3.core.Observer<? super T> s) {
        source.subscribe(new ObserverV3toV2<T>(s));
    }

    @Override
    public io.reactivex.rxjava3.core.Observable<T> apply(io.reactivex.Observable<T> upstream) {
        return new ObservableV2toV3<>(upstream);
    }

    static final class ObserverV3toV2<T>
    implements io.reactivex.Observer<T>, io.reactivex.rxjava3.disposables.Disposable {

        final io.reactivex.rxjava3.core.Observer<? super T> downstream;

        io.reactivex.disposables.Disposable upstream;

        ObserverV3toV2(io.reactivex.rxjava3.core.Observer<? super T> downstream) {
            this.downstream = downstream;
        }

        @Override
        public void onSubscribe(io.reactivex.disposables.Disposable d) {
            this.upstream = d;
            downstream.onSubscribe(this);
        }

        @Override
        public void onNext(T t) {
            downstream.onNext(t);
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
