package com.ComapnyAdvisors.hikeAdvisor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ComapnyAdvisors.hikeAdvisor.Util.DetailsFragment;
import com.ComapnyAdvisors.hikeAdvisor.adapter.OnParkClickListener;
import com.ComapnyAdvisors.hikeAdvisor.adapter.ParkRecyclerViewAdapter;
import com.ComapnyAdvisors.hikeAdvisor.model.Park;
import com.ComapnyAdvisors.hikeAdvisor.model.ParkViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ParksFragment extends Fragment implements OnParkClickListener {

    private RecyclerView recyclerView;
    private ParkRecyclerViewAdapter parkRecyclerViewAdapter;
    private List<Park> parkList;
    ParkViewModel parkViewModel;

    public ParksFragment() {
        // Required empty public constructor
    }

    public static ParksFragment newInstance() {
        ParksFragment fragment = new ParksFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parkList = new ArrayList<>();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        parkViewModel = new ViewModelProvider(requireActivity())
                .get(ParkViewModel.class);

        if(parkViewModel.getParks().getValue() != null) {
            parkList = parkViewModel.getParks().getValue();
            parkRecyclerViewAdapter = new ParkRecyclerViewAdapter(parkList,this);
            recyclerView.setAdapter(parkRecyclerViewAdapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parks, container, false);
        recyclerView = view.findViewById(R.id.park_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onParkClicked(Park park) {
        Log.d("Park", "onParkClicked: " + park.getName());
        parkViewModel.selectPark(park);
        getFragmentManager().beginTransaction()
                .replace(R.id.park_fragment, DetailsFragment.newInstance())
                .commit();
    }
}