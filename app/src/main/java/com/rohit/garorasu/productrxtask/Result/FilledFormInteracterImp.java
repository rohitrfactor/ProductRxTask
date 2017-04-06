package com.rohit.garorasu.productrxtask.Result;

import android.util.Log;

import com.rohit.garorasu.productrxtask.FilledForm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by garorasu on 6/4/17.
 */

public class FilledFormInteracterImp implements FilledFormInteracter {
    private FilledFormsPresenter presenter;
    private static final String BASE_URL = "http://sc.productrx.com/public/survey";

    public FilledFormInteracterImp(FilledFormsPresenter presenter){
        this.presenter = presenter;
    }
    @Override
    public void getFilledForms() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        String date = String.valueOf(new StringBuilder().append(year).append(month).append(day));
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BASE_URL+"/"+date)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Interacter","Failure with exception"+e);
                presenter.showFailure();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        String responseData = response.body().string();
                        //JSONObject json = new JSONObject(responseData);
                        //JSONArray array = json.getJSONArray("forms");
                        JSONArray array = new JSONArray(responseData);
                        ArrayList<FilledForm> forms = new ArrayList<FilledForm>();
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            forms.add(FilledForm.jsonToFilledForm(object));
                        }
                        presenter.addForms(forms);
                        for(int j=0;j<forms.size();j++){
                            System.out.println("Name : "+forms.get(j).getName());
                        }

                    } catch (JSONException e) {
                        Log.e("Interacter","JSON Exception "+e);
                        presenter.showFailure();
                    }
                    Log.e("Success","Success "+response.body().toString());
                }else{
                    presenter.showFailure();
                    Log.e("Error","response "+response.body().toString());
                }

            }
        });
    }
}
