package com.su.primeavto.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.su.primeavto.Constants;
import com.su.primeavto.OnItemClickListener;
import com.su.primeavto.R;
import com.su.primeavto.model.AutoModel;
import com.su.primeavto.util.ViewUtil;
import com.su.primeavto.view.activity.DetailsActivity;
import com.su.primeavto.view.adapter.AutoAdapter;
import com.su.primeavto.view.adapter.CategoriesAdapter;
import com.su.primeavto.viewmodel.CarViewModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.su.primeavto.util.Connectivity.isOnline;


public class CarsFragment extends BaseFragment {

    public static final String TAG = CarsFragment.class.getSimpleName();
    private CarViewModel carViewModel;
    private SwipeRefreshLayout swipe;
    private AutoAdapter adapter;
    private CategoriesAdapter categoriesAdapter;
    private List<String> categories;
    private List<AutoModel> allCars;
    String lastClickedCategory;
    private RecyclerView recViewOfCars, recViewOfCategories;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lastClickedCategory = getString(R.string.all_cars);
        allCars = new ArrayList<>();
        carViewModel = provider.get(CarViewModel.class);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_cars, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipe = view.findViewById(R.id.swipe);
        recViewOfCars = view.findViewById(R.id.rec_view);
        recViewOfCategories = view.findViewById(R.id.rec_view_categories);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);

        recViewOfCategories.setLayoutManager(layoutManager);
        recViewOfCategories.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.i(TAG, "onScrollStateChanged: newState = " + newState);
//                if (newState == 0) {
//                    int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
//                    String name = categories.get(firstVisiblePosition);
//                    categoriesAdapter.changeSelectedStare(name, firstVisiblePosition);
//                }
            }
        });

        adapter = new AutoAdapter(getContext());
        adapter.setOnItemClickListener((v, autoModel, position) -> openDetails(autoModel));


        categoriesAdapter = new CategoriesAdapter(getContext());
        categoriesAdapter.setOnItemClickListener((v, name, position) -> {
            lastClickedCategory = name.toLowerCase();
            adapter.setData(getFilteredCars(allCars, name));

//            ViewUtil.runLayoutAnimation(recViewOfCategories, true);
            ViewUtil.runLayoutAnimation(recViewOfCars, true);
        });

        recViewOfCars.setLayoutManager(new LinearLayoutManager(getContext()));
        recViewOfCars.setAdapter(adapter);
        recViewOfCategories.setAdapter(categoriesAdapter);


        getCars();

        swipe.setOnRefreshListener(this::getCars);
    }

    private void openDetails(AutoModel autoModel) {

        Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra(Constants.INTENT_KEY_AVTO, autoModel);
        startActivity(intent);
    }

    private List<AutoModel> getFilteredCars(List<AutoModel> cars, String name) {
        List<AutoModel> filteredList = new ArrayList<>();
        if (name.toLowerCase().equals(getString(R.string.all_cars).toLowerCase())) {
            return cars;
        } else {
            for (AutoModel car :
                    cars) {
                if (name.toLowerCase().equals(car.getRentTerms().getValue().toLowerCase())) {
                    filteredList.add(car);
                }
            }
            return filteredList;
        }
    }

    private void getCars() {
        swipe.setRefreshing(true);
        if (isOnline()) {
            carViewModel.getCars(Constants.TOKEN).observe(getViewLifecycleOwner(), cars -> {
                allCars = cars;
                adapter.setData(getFilteredCars(cars, lastClickedCategory));

                categories = getCategories(cars);
                categoriesAdapter.setData(categories);

                swipe.setRefreshing(false);
                ViewUtil.runLayoutAnimation(recViewOfCategories, true);
                ViewUtil.runLayoutAnimation(recViewOfCars, true);
            });
        } else {
            swipe.setRefreshing(false);
            openConnectivityDialog();
        }
    }

    private void openConnectivityDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setTitle(R.string.error);
        alertDialogBuilder.setMessage(R.string.message);
        alertDialogBuilder.setNegativeButton(R.string._cancel, (dialog, which) -> dialog.dismiss());

        alertDialogBuilder.setPositiveButton(R.string.refresh, (dialog, which) -> {
            getCars();
            dialog.dismiss();
        });
        alertDialogBuilder.create().show();
    }

    private List<String> getCategories(List<AutoModel> cars) {
        Set<String> result = new HashSet<>();
        result.add(getString(R.string.all_cars));
        for (AutoModel car : cars) {
            result.add(car.getRentTerms().getValue());
        }
        return new ArrayList<>(result);
    }
}