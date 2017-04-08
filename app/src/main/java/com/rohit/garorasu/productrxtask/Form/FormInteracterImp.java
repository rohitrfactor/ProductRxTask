package com.rohit.garorasu.productrxtask.Form;

import android.os.AsyncTask;
import android.util.Log;

import com.rohit.garorasu.productrxtask.FilledForm;
import com.rohit.garorasu.productrxtask.Schema;
import com.rohit.garorasu.productrxtask.Survey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
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
    private static final String SCHEMA_URL = "http://sc.productrx.com/public/schema/survey";

    @Override
    public void submitForm(HashMap<String,String> params) {
            new submitInBackground().execute(params);
    }

    @Override
    public void requestFormSchema() {
        new schemaInBackground().execute();
    }
    private class schemaInBackground extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();


            Request request = new Request.Builder()
                    .url(SCHEMA_URL)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("Error","exception "+e);
                    presenter.schemaFailure();
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    if (response.isSuccessful()) {
                        try {
                            String responseData = response.body().string();
                            JSONArray array = new JSONArray(responseData);
                            ArrayList<Schema> schemas = new ArrayList<Schema>();
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                schemas.add(Schema.jsonToSchema(object));
                            }
                            presenter.addSchema(schemas);
                            for(int j=0;j<schemas.size();j++){
                                System.out.println("Field : "+schemas.get(j).getField());
                            }

                        } catch (JSONException e) {
                            Log.e("Interacter","JSON Exception "+e);
                            presenter.schemaFailure();
                        }
                        Log.e("Success","Success "+response.body().toString());
                    }else{
                        presenter.schemaFailure();
                        Log.e("Error","response "+response.body().toString());
                    }
                }
            });
            return null;
        }
    }
    private class submitInBackground extends AsyncTask<HashMap<String,String>,Void,Void>{

        @Override
        protected Void doInBackground(HashMap<String,String> ...param) {
            OkHttpClient client = new OkHttpClient();


            FormBody.Builder formBuilder = new FormBody.Builder();

            Iterator field = param[0].entrySet().iterator();
            while(field.hasNext()){
                Map.Entry pair = (Map.Entry) field.next();
                formBuilder.add(pair.getKey().toString(),pair.getValue().toString());
                field.remove();
            }

            RequestBody formBody = formBuilder.build();
            Request request = new Request.Builder()
                    .url(BASE_URL)
                    .post(formBody)
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
            return null;
        }
    }
}
