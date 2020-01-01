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

final class FlowableV2toV3<T> extends io.reactivex.rxjava3.core.Flowable<T>
implements io.reactivex.FlowableConverter<T, io.reactivex.rxjava3.core.Flowable<T>> {

    final io.reactivex.Flowable<T> source;

    static final FlowableV2toV3<Object> CONVERTER = new FlowableV2toV3<>(null);

    FlowableV2toV3(io.reactivex.Flowable<T> source) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(org.reactivestreams.Subscriber<? super T> s) {
        source.subscribe(new FlowableSubscriberBridge<T>(s));
    }

    @Override
    public io.reactivex.rxjava3.core.Flowable<T> apply(io.reactivex.Flowable<T> upstream) {
        return new FlowableV2toV3<>(upstream);
    }
}
