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

final class DisposableV2toV3 implements io.reactivex.rxjava3.disposables.Disposable {

    final io.reactivex.disposables.Disposable disposable;

    DisposableV2toV3(io.reactivex.disposables.Disposable disposable) {
        this.disposable = disposable;
    }

    @Override
    public boolean isDisposed() {
        return disposable.isDisposed();
    }

    @Override
    public void dispose() {
        disposable.dispose();
    }

    static io.reactivex.rxjava3.disposables.Disposable wrap(io.reactivex.disposables.Disposable disposable) {
        if (disposable == io.reactivex.internal.disposables.DisposableHelper.DISPOSED) {
            return io.reactivex.rxjava3.internal.disposables.DisposableHelper.DISPOSED;
        }
        if (disposable == io.reactivex.internal.disposables.EmptyDisposable.INSTANCE) {
            return io.reactivex.rxjava3.internal.disposables.EmptyDisposable.INSTANCE;
        }
        return new DisposableV2toV3(disposable);
    }
}
