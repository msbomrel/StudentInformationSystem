package com.example.msbomrel.studentinformationsystem;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.msbomrel.studentinformationsystem.API.ApiRequestStudent;
import com.example.msbomrel.studentinformationsystem.API.Retroserver;
import com.example.msbomrel.studentinformationsystem.Model.ResponsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by msbomrel on 8/9/17.
 */

public class AddStudent extends AppCompatActivity {
    EditText name, batch, email, mobile;
    Button save, update, delete;
    String iddata = null;
    ProgressDialog pd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent);

        //adding values; saving from form
        name = (EditText) findViewById(R.id.fullname);
        batch = (EditText) findViewById(R.id.batch);
        email = (EditText) findViewById(R.id.email);
        mobile = (EditText) findViewById(R.id.mobile);

        save = (Button) findViewById(R.id.addstudent);
        update = (Button) findViewById(R.id.updatestudent);
        delete = (Button) findViewById(R.id.deletestudent);
        pd = new ProgressDialog(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("sending data ... ");
                pd.setCancelable(false);
                pd.show();
                insert();
            }
        });

        Intent data = getIntent();
        iddata = data.getStringExtra("id");
        if(iddata != null) {
            save.setVisibility(View.GONE);
            update.setVisibility(View.VISIBLE);
            delete.setVisibility(View.VISIBLE);
            name.setText(data.getStringExtra("name"));
            batch.setText(data.getStringExtra("batch"));
            email.setText(data.getStringExtra("email"));
            mobile.setText(data.getStringExtra("mobile"));
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setMessage("updating data ....");
                pd.setCancelable(false);
                pd.show();
                update();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
            }
        });
    }

    //insert student
    public void insert() {
        String sname = name.getText().toString();
        String sbatch = batch.getText().toString();
        String semail= email.getText().toString();
        String ssmobile = mobile.getText().toString();

        ApiRequestStudent api = Retroserver.getClient().create(ApiRequestStudent.class);

        Call<ResponsModel> sendbio = api.sendBiodata(sname, sbatch, semail, ssmobile);
        sendbio.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                Log.d("RETRO", "response : " + response.body().toString());
                String code = response.body().getCode();

                if (code.equals("1")) {
                    Intent intent = new Intent(AddStudent.this, UsersProfile.class);
                    startActivity(intent);
                    Toast.makeText(AddStudent.this, "Data inserted", Toast.LENGTH_SHORT).show();
                } else if (code.equals("3")){
                    pd.hide();
                    Toast.makeText(AddStudent.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                else if (code.equals("4")){
                    pd.hide();
                    Toast.makeText(AddStudent.this, "Phone number is not correct", Toast.LENGTH_SHORT).show();
                }
                else {
                    pd.hide();
                    Toast.makeText(AddStudent.this, "Data not inserted", Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onFailure(Call<ResponsModel> call, Throwable t) {
                pd.hide();
            }
        });
    }

    public void update(){
        ApiRequestStudent api = Retroserver.getClient().create(ApiRequestStudent.class);
        Call<ResponsModel> update = api.updateData(iddata, name.getText().toString(),batch.getText().toString(),email.getText().toString(), mobile.getText().toString());
        update.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                Log.d("Retro", "Response");
                Intent intent = new Intent(AddStudent.this, UsersProfile.class);
                startActivity(intent);
                Toast.makeText(AddStudent.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                pd.hide();
            }

            @Override
            public void onFailure(Call<ResponsModel> call, Throwable t) {
                pd.hide();
            }
        });
    }

    public void createDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Are you sure you want to delete?");
        alert.setCancelable(false);

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                delete();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.create().show();
    }

    public void delete(){
        pd.setMessage("Deleting ...");
        pd.setCancelable(false);
        pd.show();
        ApiRequestStudent api = Retroserver.getClient().create(ApiRequestStudent.class);
        Call<ResponsModel> del  = api.deleteData(iddata);
        del.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                Toast.makeText(AddStudent.this, "Deleted",Toast.LENGTH_SHORT).show();
                Intent gotohome = new Intent(AddStudent.this,UsersProfile.class);
                startActivity(gotohome);

            }
            @Override
            public void onFailure(Call<ResponsModel> call, Throwable t) {
                pd.hide();
            }
        });
    }
}




