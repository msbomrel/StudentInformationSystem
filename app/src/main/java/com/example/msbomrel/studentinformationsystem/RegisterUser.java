package com.example.msbomrel.studentinformationsystem;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.msbomrel.studentinformationsystem.API.RegisterAPI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by msbomrel on 7/22/17.
 */

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private Button buttonRegister;

//    private static final String REGISTER_URL = "http://192.168.1.106/api/register.php";
    private static final String REGISTER_URL = "https://mohanbomrel.000webhostapp.com/";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextUsername = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);
        editTextEmail = (EditText) findViewById(R.id.email);

        buttonRegister = (Button) findViewById(R.id.register);

        buttonRegister.setOnClickListener(this);
    }

    private void registerUser(){
        //Here we will handle the http request to insert user to mysql db
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(REGISTER_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        RegisterAPI api = adapter.create(RegisterAPI.class);

        //Defining the method insertuser of our interface
        api.insertUser(

                //Passing the values by getting it from editTexts
                editTextUsername.getText().toString(),
                editTextPassword.getText().toString(),
                editTextEmail.getText().toString(),

                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {
                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object
                        BufferedReader reader = null;
                        //An string to store output from the server
                        String output = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
                            //Reading the output in the string
                            output = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(RegisterUser.this, MainActivity.class);
                        startActivity(intent);
                        //Displaying the output as a toast
                        Toast.makeText(RegisterUser.this, output, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(RegisterUser.this, error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    //Overriding onclick method
    @Override
    public void onClick(View v) {
        //Calling insertUser on button click
        registerUser();
    }

}
