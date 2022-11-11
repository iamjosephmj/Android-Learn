package io.iamjosephmj.dttest.presentation.viewmodel;

import io.iamjosephmj.dttest.domain.FeedsResponse;

// This was implemented to substitute kotlin sealed classes.
public interface ViewState {
    class LoadingViewState implements ViewState {
    }

    class StaleViewState implements ViewState {

    }

    class ErrorViewState implements ViewState {
    }

    class SuccessViewState implements ViewState {

        private final FeedsResponse response;

        public SuccessViewState(FeedsResponse feedsResponse) {
            this.response = feedsResponse;
        }

        public FeedsResponse getResponse() {
            return response;
        }

    }

}
