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

final class FlowableV3toV2<T> extends io.reactivex.Flowable<T>
implements io.reactivex.rxjava3.core.FlowableConverter<T, io.reactivex.Flowable<T>> {

    final io.reactivex.rxjava3.core.Flowable<T> source;

    static final FlowableV3toV2<Object> CONVERTER = new FlowableV3toV2<>(null);

    FlowableV3toV2(io.reactivex.rxjava3.core.Flowable<T> source) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(org.reactivestreams.Subscriber<? super T> s) {
        source.subscribe(new FlowableSubscriberBridge<T>(s));
    }

    @Override
    public io.reactivex.Flowable<T> apply(io.reactivex.rxjava3.core.Flowable<T> upstream) {
        return new FlowableV3toV2<>(upstream);
    }
}
