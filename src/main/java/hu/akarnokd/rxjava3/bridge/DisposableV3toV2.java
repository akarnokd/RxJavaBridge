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

final class DisposableV3toV2 implements io.reactivex.disposables.Disposable {

    final io.reactivex.rxjava3.disposables.Disposable disposable;

    DisposableV3toV2(io.reactivex.rxjava3.disposables.Disposable disposable) {
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

    static io.reactivex.disposables.Disposable wrap(io.reactivex.rxjava3.disposables.Disposable disposable) {
        if (disposable.isDisposed()) {
            return io.reactivex.disposables.Disposables.disposed();
        }
        return new DisposableV3toV2(disposable);
    }
}
