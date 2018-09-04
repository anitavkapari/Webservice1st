package com.example.annu.retofit.service;


import com.example.annu.retofit.model.BaseResponse;
import com.example.annu.retofit.model.Employee;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface { //3rd steps declare end points

    @POST("myapp/api/employees/single")

    public Call<BaseResponse> addEmployee(@Body Employee employee);
}
