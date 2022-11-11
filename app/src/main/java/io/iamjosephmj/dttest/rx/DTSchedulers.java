package io.iamjosephmj.dttest.rx;

import io.reactivex.Scheduler;

/**
 * This class is also used in the tests to mock the schedulers.
 */
public interface DTSchedulers {

    Scheduler io();

    Scheduler ui();
}
