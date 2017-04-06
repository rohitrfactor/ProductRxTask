package com.rohit.garorasu.productrxtask.Form;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Switch;

import com.rohit.garorasu.productrxtask.R;
import com.rohit.garorasu.productrxtask.Survey;

import java.text.Normalizer;

/**
 * A simple {@link Fragment} subclass.
 */
public class FormFragment extends Fragment implements FormView {
    private EditText emp_id,name;
    private Switch sex;
    private String mEmp_id,mName,mSex = "male";
    private FormPresenter presenter;
    private LinearLayout mForm;
    private ProgressBar mProgressBar;

    public FormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_form, container, false);
        mForm =(LinearLayout) view.findViewById(R.id.form);
        mProgressBar = (ProgressBar) view.findViewById(R.id.form_progress);
        emp_id = (EditText) view.findViewById(R.id.emp_id);
        name = (EditText) view.findViewById(R.id.name);
        Spinner spinner = (Spinner) view.findViewById(R.id.gender_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        mSex = adapterView.getItemAtPosition(i).toString().toLowerCase();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button submit = (Button) view.findViewById(R.id.form_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgress();
                submitForm();
            }
        });
        return view;
    }



    @Override
    public void submitForm() {
        mEmp_id = emp_id.getText().toString();
        mName = name.getText().toString();
        if(mEmp_id.length() == 0){
            emp_id.setError("Field is mandatory");
        }
        if(mName.length() == 0){
            name.setError("Field is mandatory");
        }

        if(mEmp_id.length()>0 && mName.length()>0){
            presenter = new FormPresenterImp(this);
            presenter.submitForm(new Survey(Integer.parseInt(mEmp_id),mName,mSex));
        }
    }

    @Override
    public void submitSuccess() {
            //update UI thread only
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hideProgress();
                    resetForm();
                }
            });
    }

    @Override
    public void submitFailure() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
    public void showProgress(){
           mProgressBar.setVisibility(View.VISIBLE);
           mForm.setVisibility(View.INVISIBLE);
    }
    public void hideProgress(){
        mProgressBar.setVisibility(View.INVISIBLE);
        mForm.setVisibility(View.VISIBLE);
    }
    public void resetForm(){
            emp_id.setText("");
            name.setText("");
    }
}
