package com.rohit.garorasu.productrxtask.Form;

import android.util.Log;

import com.rohit.garorasu.productrxtask.Survey;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Rohit Nagpal on 6/4/17.
 */


public class FormInteracterImp implements FormInteracter {
    private FormPresenter presenter;

    public FormInteracterImp(FormPresenter presenter){
        this.presenter = presenter;
    }

    private static final String BASE_URL = "http://sc.productrx.com/public/survey";
    @Override
    public void submitForm(Survey survey) {
        OkHttpClient client = new OkHttpClient();


        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("emp_id", String.valueOf(survey.getEmp_id()))
                .addFormDataPart("name", survey.getName())
                .addFormDataPart("sex", survey.getSex())
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL)
                .method("POST", RequestBody.create(null, new byte[0]))
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error","exception "+e);
                presenter.submitFailure();
            }

            @Override
            public void onResponse(Call call, final Response response)  {
                if (response.isSuccessful()) {
                        presenter.submitSuccess();
                    Log.e("Success","Success "+response.body().toString());
                }else{
                        presenter.submitFailure();
                    Log.e("Error","response "+response.body().toString());
                }
            }
        });
    }
}
