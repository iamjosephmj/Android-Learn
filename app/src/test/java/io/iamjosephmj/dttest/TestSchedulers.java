package io.iamjosephmj.dttest;

import io.iamjosephmj.dttest.rx.DTSchedulers;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.TestScheduler;

public class TestSchedulers implements DTSchedulers {
    public static TestScheduler testScheduler = new TestScheduler();

    @Override
    public Scheduler io() {
        return testScheduler;
    }

    @Override
    public Scheduler ui() {
        return testScheduler;
    }
}
