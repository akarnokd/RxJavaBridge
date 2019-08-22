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

final class FlowableSubscriberBridge<T>
implements io.reactivex.FlowableSubscriber<T>,
            io.reactivex.rxjava3.core.FlowableSubscriber<T>,
            org.reactivestreams.Subscription {

    final org.reactivestreams.Subscriber<? super T> downstream;

    org.reactivestreams.Subscription upstream;

    FlowableSubscriberBridge(org.reactivestreams.Subscriber<? super T> downstream) {
        this.downstream = downstream;
    }

    @Override
    public void onSubscribe(org.reactivestreams.Subscription s) {
        this.upstream = s;
        downstream.onSubscribe(this);
    }

    @Override
    public void onNext(T t) {
        downstream.onNext(t);
    }

    @Override
    public void onError(Throwable t) {
        downstream.onError(t);
    }

    @Override
    public void onComplete() {
        downstream.onComplete();
    }

    @Override
    public void request(long n) {
        upstream.request(n);
    }

    @Override
    public void cancel() {
        upstream.cancel();
    }
}