# RxJavaBridge
Bridge between RxJava 2 and RxJava 3

<a href='https://travis-ci.org/akarnokd/RxJavaBridge/builds'><img src='https://travis-ci.org/akarnokd/RxJavaBridge.svg?branch=master'></a>
[![codecov.io](http://codecov.io/github/akarnokd/RxJavaBridge/coverage.svg?branch=master)](http://codecov.io/github/akarnokd/RxJavaBridge?branch=master)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.akarnokd/rxjava3-bridge/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.akarnokd/rxjava3-bridge)

RxJava 2: [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.reactivex.rxjava2/rxjava/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.reactivex.rxjava2/rxjava)
 
RxJava 3: [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.reactivex.rxjava3/rxjava/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.reactivex.rxjava3/rxjava)


```groovy

dependencies {
    implementation "com.github.akarnokd:rxjava3-bridge:3.0.0-RC8"
}
```


# Features

## Convert between Flowables

### via static methods

```java
import hu.akarnokd.rxjava3.bridge.RxJavaBridge;

io.reactivex.Flowable              f2 = RxJavaBridge.toV2Flowable(io.reactivex.rxjava3.core.Flowable)

io.reactivex.rxjava3.core.Flowable f3 = RxJavaBridge.toV3Flowable(io.reactivex.Flowable)
```

### via FlowableConverter application

```java
f3 = f2.as(RxJavaBridge.toV3Flowable())

f2 = f3.to(RxJavaBridge.toV2Flowable())
```

## Convert between Observables

### via static methods

```java
import hu.akarnokd.rxjava3.bridge.RxJavaBridge;

io.reactivex.Observable              o2 = RxJavaBridge.toV2Observable(io.reactivex.rxjava3.core.Observable)

io.reactivex.rxjava3.core.Observable o3 = RxJavaBridge.toV3Observable(io.reactivex.Observable)
```

### via ObservableConverter application

```java
o3 = o2.as(RxJavaBridge.toV3Observable())

o2 = o3.to(RxJavaBridge.toV2Observable())
```

## Convert between Maybes

### via static methods

```java
import hu.akarnokd.rxjava3.bridge.RxJavaBridge;

io.reactivex.Maybe              m2 = RxJavaBridge.toV2Maybe(io.reactivex.rxjava3.core.Maybe)

io.reactivex.rxjava3.core.Maybe m3 = RxJavaBridge.toV3Maybe(io.reactivex.Maybe)
```

### via MaybeConverter application

```java
m3 = m2.as(RxJavaBridge.toV3Maybe())

m2 = m3.to(RxJavaBridge.toV2Maybe())
```

## Convert between Singles

### via static methods

```java
import hu.akarnokd.rxjava3.bridge.RxJavaBridge;

io.reactivex.Single              s2 = RxJavaBridge.toV2Single(io.reactivex.rxjava3.core.Single)

io.reactivex.rxjava3.core.Single s3 = RxJavaBridge.toV3Single(io.reactivex.Single)
```

### via SingleConverter application

```java
s3 = s2.as(RxJavaBridge.toV3Single())

s2 = s3.to(RxJavaBridge.toV2Single())
```


## Convert between Completables

### via static methods

```java
import hu.akarnokd.rxjava3.bridge.RxJavaBridge;

io.reactivex.Completable              c2 = RxJavaBridge.toV2Completable(io.reactivex.rxjava3.core.Completable)

io.reactivex.rxjava3.core.Completable c3 = RxJavaBridge.toV3Completable(io.reactivex.Completable)
```

### via CompletableConverter application

```java
c3 = c2.as(RxJavaBridge.toV3Completable())

c2 = c3.to(RxJavaBridge.toV2Completable())
```


## Convert between Disposables

### via static methods

```java
import hu.akarnokd.rxjava3.bridge.RxJavaBridge;

io.reactivex.disposables.Disposable        d2 = RxJavaBridge.toV2Disposable(io.reactivex.rxjava3.disposables.Disposable)

io.reactivex.rxjava3.disosables.Observable d3 = RxJavaBridge.toV3Disposable(io.reactivex.disposables.Disposable)
```

## Convert between Schedulers

### via static methods

```java
import hu.akarnokd.rxjava3.bridge.RxJavaBridge;

io.reactivex.Scheduler              sch2 = RxJavaBridge.toV2Scheduler(io.reactivex.rxjava3.core.Scheduler)

io.reactivex.rxjava3.core.Scheduler sch3 = RxJavaBridge.toV3Scheduler(io.reactivex.Scheduler)
```

### use 3.x standard schedulers in 2.x

```java
import hu.akarnokd.rxjava3.bridge.RxJavaBridge;

io.reactivex.schedulers.Schedulers.shutdown();

RxJavaBridge.startUsingV3Schedulers();

// when done

RxJavaBridge.stopUsingV3Schedulers();

io.reactivex.schedulers.Schedulers.start();
```

### use 2.x standard schedulers in 3.x

```java
import hu.akarnokd.rxjava3.bridge.RxJavaBridge;

io.reactivex.rxjava3.schedulers.Schedulers.shutdown();

RxJavaBridge.startUsingV2Schedulers();

// when done

RxJavaBridge.stopUsingV2Schedulers();

io.reactivex.rxjava3.schedulers.Schedulers.start();
```
