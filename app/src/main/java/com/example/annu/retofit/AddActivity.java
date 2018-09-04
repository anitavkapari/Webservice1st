package com.example.annu.retofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.annu.retofit.model.BaseResponse;
import com.example.annu.retofit.model.Employee;
import com.example.annu.retofit.service.ApiClient;
import com.example.annu.retofit.service.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity {
EditText edtName,edtAddress,edtSalary,edtPhone,edtDesignation;
Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add );

        edtName=findViewById( R.id.edtName );
        edtAddress=findViewById( R.id.edtAddress );
        edtSalary=findViewById( R.id.edtSalary );
        edtPhone=findViewById( R.id.edtPhone );
        edtDesignation=findViewById( R.id.edtDesignation );
        btnAdd=findViewById( R.id.btnAdd );


    }

    public void addToServer(View view) {
        String name=edtName.getText().toString().trim();
        String address=edtAddress.getText().toString().trim();
        String phoneNumber=edtPhone.getText().toString().trim();
        String salString=edtSalary.getText().toString().trim();
        String designation=edtDesignation.getText().toString().trim();

        if (TextUtils.isEmpty( name)||TextUtils.isEmpty( address )||TextUtils.isEmpty( phoneNumber )
                ||TextUtils.isEmpty( salString )||TextUtils.isEmpty( designation)){
            Toast.makeText( this,"please the enter the field",Toast.LENGTH_SHORT ).show();
            return;
        }
long salary= Long.parseLong( salString );
        Employee employee= new Employee(  name,address,phoneNumber,salary,designation );
        ApiInterface apiInterface= ApiClient.getClient().create( ApiInterface.class );
        Call<BaseResponse> call=apiInterface.addEmployee( employee );
        call.enqueue( new Callback<BaseResponse>( ) {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse=response.body();
                Toast.makeText( AddActivity.this,baseResponse.getMessage(),Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        } );
    }
}
