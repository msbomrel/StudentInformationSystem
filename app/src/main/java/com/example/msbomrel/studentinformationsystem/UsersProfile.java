package com.example.msbomrel.studentinformationsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.msbomrel.studentinformationsystem.API.ApiRequestStudent;
import com.example.msbomrel.studentinformationsystem.API.Retroserver;
import com.example.msbomrel.studentinformationsystem.Adapter.AdapterData;
import com.example.msbomrel.studentinformationsystem.Model.ResponsModel;
import com.example.msbomrel.studentinformationsystem.Model.Student;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by msbomrel on 7/22/17.
 */

public class UsersProfile extends AppCompatActivity {
    private RecyclerView mRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private List<Student> mItems = new ArrayList<>();
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liststudents);

        pd = new ProgressDialog(this);
        mRecycler = (RecyclerView) findViewById(R.id.liststudent);
        mManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(mManager);

        pd.setMessage("Loading ...");
        pd.setCancelable(false);
        pd.show();

        ApiRequestStudent api = Retroserver.getClient().create(ApiRequestStudent.class);
        Call<ResponsModel> getdata = api.getBiodata();
        getdata.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                pd.hide();
                Log.d("RETRO", "RESPONSE : " + response.body().getCode());
                mItems = response.body().getResult();

                mAdapter = new AdapterData(UsersProfile.this,mItems);
                mRecycler.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponsModel> call, Throwable t) {
                pd.hide();
                Log.d("RETRO", "FAILED : response unknown");

            }
        });



        //floating button
        FloatingActionButton btn = (FloatingActionButton) findViewById(R.id.fab);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(UsersProfile.this, AddStudent.class);
                startActivity(intent);
            }
        });
    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case (R.id.about):

                break;
            case (R.id.contact):

                break;
        }
        return super.onOptionsItemSelected(item);
    }

 }


