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

final class SingleV3toV2<T> extends io.reactivex.Single<T>
implements io.reactivex.rxjava3.core.SingleConverter<T, io.reactivex.Single<T>> {

    final io.reactivex.rxjava3.core.Single<T> source;

    static final SingleV3toV2<Object> CONVERTER = new SingleV3toV2<Object>(null);

    SingleV3toV2(io.reactivex.rxjava3.core.Single<T> source) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(io.reactivex.SingleObserver<? super T> s) {
        source.subscribe(new SingleObserverV2toV3<T>(s));
    }

    @Override
    public io.reactivex.Single<T> apply(io.reactivex.rxjava3.core.Single<T> upstream) {
        return new SingleV3toV2<T>(upstream);
    }

    static final class SingleObserverV2toV3<T>
    implements io.reactivex.rxjava3.core.SingleObserver<T>, io.reactivex.disposables.Disposable {

        final io.reactivex.SingleObserver<? super T> downstream;

        io.reactivex.rxjava3.disposables.Disposable upstream;

        SingleObserverV2toV3(io.reactivex.SingleObserver<? super T> downstream) {
            this.downstream = downstream;
        }

        @Override
        public void onSubscribe(io.reactivex.rxjava3.disposables.Disposable d) {
            this.upstream = d;
            downstream.onSubscribe(this);
        }

        @Override
        public void onSuccess(T t) {
            downstream.onSuccess(t);
        }

        @Override
        public void onError(Throwable e) {
            downstream.onError(e);
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
