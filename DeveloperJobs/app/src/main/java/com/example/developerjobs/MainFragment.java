package com.example.developerjobs;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainFragment extends Fragment {

    private APIService apiService;
    private Retrofit retrofit;
    List<Job> jobs = new ArrayList<>();
    RecyclerViewAdapter.ClickListener clickListener;
    RecyclerViewAdapter adapter;
    RecyclerView recyclerView;

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    public static MainFragment newInstance(String description, String location, boolean is_full_time){
        MainFragment mainFragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString("description", description);
        args.putString("location", location);
        args.putBoolean("is_full_time", is_full_time);
        mainFragment.setArguments(args);
        return mainFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Developer jobs");
        setHasOptionsMenu(true);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        clickListener = new RecyclerViewAdapter.ClickListener(){
            @Override
            public void replaceFragment(int id, Job job) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, JobDetailFragment.newInstance(job))
                        .addToBackStack(null)
                        .commit();
            }
        };
        adapter = new RecyclerViewAdapter(jobs, clickListener);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://jobs.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            apiService = retrofit.create(APIService.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try {
            String description = getArguments().getString("description", "");
            String location = getArguments().getString("location", "");
            boolean is_full_time = getArguments().getBoolean("is_full_time");
            this.getFilteredJobs(description, location, is_full_time);
        }
        catch (Exception e){
            this.getJobs();
        }
    }



    public void getJobs(){
        apiService.getJobs().enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.body() != null) {
                    jobs.clear();
                    jobs.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Log.e("Error on getJobs:",t.getMessage());
            }
        });
    }

    public void getFilteredJobs(String description, String location, boolean is_full_time){
        apiService.getFilteredJobs(description, location, is_full_time).enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.body() != null) {
                    jobs.clear();
                    jobs.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Log.e("Error on getFiltered:",t.getMessage());
                Toast.makeText(getActivity().getApplicationContext(), "Could not find such a job", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.action_bar, menu);
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                apiService.getJobsFromSearch(query).enqueue(new Callback<List<Job>>() {
                    @Override
                    public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                        jobs.clear();
                        jobs.addAll(response.body());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<Job>> call, Throwable t) {
                        Log.e("Error on search:", t.getMessage());
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                apiService.getJobsFromSearch(newText).enqueue(new Callback<List<Job>>() {
                    @Override
                    public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                        jobs.clear();
                        jobs.addAll(response.body());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<Job>> call, Throwable t) {
                        Log.e("Error on search:", t.getMessage());
                    }
                });
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.filter){
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            FilterJobsFragment filterJobsFragment = FilterJobsFragment.newInstance();
            filterJobsFragment.show(fragmentTransaction, "dialog");
            return true;
        }
        return false;
    }
}
