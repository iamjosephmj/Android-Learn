package io.iamjosephmj.dttest.presentation;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

import javax.inject.Inject;

import io.iamjosephmj.dttest.data.Request;
import io.iamjosephmj.dttest.databinding.ActivityMainBinding;
import io.iamjosephmj.dttest.di.AppComponent;
import io.iamjosephmj.dttest.domain.FeedsResponse;
import io.iamjosephmj.dttest.presentation.adapter.MainAdapter;
import io.iamjosephmj.dttest.presentation.base.BaseActivity;
import io.iamjosephmj.dttest.presentation.viewmodel.MainViewModel;
import io.iamjosephmj.dttest.presentation.viewmodel.ViewModelProviderFactory;
import io.iamjosephmj.dttest.presentation.viewmodel.ViewState;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;

    @Inject
    ViewModelProviderFactory factory;
    @Inject
    MainAdapter adapter;

    @Override
    protected void initializeDI(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel =
                new ViewModelProvider((ViewModelStoreOwner) this, factory)
                        .get(MainViewModel.class);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeUI();
    }

    private void initializeUI() {
        initializeObservable();
        iniComponents();
    }

    private void iniComponents() {
        initializeList();
        binding.submit.setOnClickListener(view -> mainViewModel.fetchData(new Request(
                binding.appId.getText().toString().trim(),
                binding.uId.getText().toString().trim(),
                binding.token.getText().toString().trim()
        )));
    }

    private void initializeList() {
        binding.list.setAdapter(adapter);
        binding.list.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initializeObservable() {
        mainViewModel.getFeedsResponse().observe(this, viewState -> {
            if (viewState instanceof ViewState.LoadingViewState) {
                showLoading();
            } else if (viewState instanceof ViewState.SuccessViewState) {
                showSuccess(((ViewState.SuccessViewState) viewState).getResponse());
            } else if (viewState instanceof ViewState.ErrorViewState) {
                showError();
            } else if (viewState instanceof ViewState.StaleViewState) {
                showStaleState();
            }
        });
    }

    private void showStaleState() {
        binding.list.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.GONE);
        binding.errorView.setVisibility(View.GONE);
    }

    private void showError() {
        binding.list.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.GONE);
        binding.errorView.setVisibility(View.VISIBLE);
    }

    private void showSuccess(FeedsResponse response) {
        binding.list.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.GONE);
        binding.errorView.setVisibility(View.GONE);
        adapter.updateData(response);
    }

    private void showLoading() {
        binding.list.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.errorView.setVisibility(View.GONE);
    }
}