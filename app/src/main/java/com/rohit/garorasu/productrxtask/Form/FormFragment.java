package com.rohit.garorasu.productrxtask.Form;


import android.content.Context;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.rohit.garorasu.productrxtask.R;
import com.rohit.garorasu.productrxtask.Schema;
import com.rohit.garorasu.productrxtask.Survey;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * A simple {@link Fragment} subclass.
 */
public class FormFragment extends Fragment implements FormView {
    private EditText emp_id,name;
    private Switch sex;
    private String mEmp_id,mName,mSex = "male";
    private FormPresenter presenter;
    private LinearLayout mForm,mError;
    private ProgressBar mProgressBar;
    private InputMethodManager imm;
    private SwipeRefreshLayout swipeContainer;
    private ArrayList<String> keys;
    private ArrayList<Boolean> mandatory;
    private ArrayList<String> responses;
    private HashMap<String,String> params;

    public FormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_form, container, false);
        mForm =(LinearLayout) view.findViewById(R.id.form);
        mError =(LinearLayout) view.findViewById(R.id.form_error);
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
                        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            }
        });

        presenter = new FormPresenterImp(this);
        getSchema();
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshform);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getSchema();
            }
        });
        Button submit = (Button) view.findViewById(R.id.form_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
        return view;
    }



    @Override
    public void submitForm() {
        params = new HashMap<>();
        boolean refill = false;
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        System.out.println("Output size is : "+keys.size());
        for(int i=1;i<=keys.size();i++){
            EditText editText = (EditText) getView().findViewById(new Integer(i));
            String text = editText.getText().toString();
            if(text.length()<1 && mandatory.get(i-1)){
                editText.setError("This field is mandatory");
                refill = true;
            }else{
                 params.put(keys.get(i-1),text);
            }
        }
        if(!refill){
                presenter.submitForm(params);
        }
        for(String key:keys){
            System.out.println("Keys : "+key);
        }

    }

    @Override
    public void submitSuccess() {
            //update UI thread only
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(),"Form successfully submitted", Toast.LENGTH_SHORT);
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
                hideProgress();
                Toast.makeText(getContext(),"Unable to submit response. Please try again", Toast.LENGTH_SHORT);
            }
        });
    }
    public void showProgress(){
           mProgressBar.setVisibility(View.VISIBLE);
           mForm.setVisibility(View.INVISIBLE);
           mError.setVisibility(View.INVISIBLE);
    }

    @Override
    public void getSchema() {
            presenter.getSchema();
    }

    @Override
    public void renderViews(final ArrayList<Schema> schemas) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                hideProgress();
                mForm.removeAllViews();
                int i=0;
                swipeContainer.setRefreshing(false);
                keys = new ArrayList<String>();
                mandatory = new ArrayList<Boolean>();
                for(Schema schema:schemas){
                    if(schema.getKey().compareTo("PRI")!=0) {

                        keys.add(schema.getField());

                        if(schema.getNull()=="NO"){
                            mandatory.add(FALSE);
                        }else{
                            mandatory.add(TRUE);
                        }
                        TextView textView = new TextView(getContext());
                        EditText editText = new EditText(getContext());
                        editText.setId(i+1);
                        ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        editText.setLayoutParams(lparams);
                        textView.setLayoutParams(lparams);
                        textView.setTextColor(getResources().getColor(R.color.textColor));
                        editText.setTextColor(getResources().getColor(R.color.textColor));
                        editText.setHintTextColor(getResources().getColor(R.color.textColor));
                        textView.setFocusableInTouchMode(FALSE);
                        editText.setFocusableInTouchMode(TRUE);
                        editText.setSingleLine();
                        textView.setText(schema.getField().toUpperCase());
                        if(schema.getDefault()!="null"){
                            editText.setText(schema.getDefault());
                        }
                        if(schema.get_type()=="int"){
                            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                        }
                        System.out.println("set : "+i+" schema : "+schemas.size());
                        if(i==schemas.size()-2){
                            editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
                        }else{
                            editText.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                        }
                        editText.setHint("Please enter " + schema.getField());
                        InputFilter[] fArray = new InputFilter[1];
                        fArray[0] = new InputFilter.LengthFilter(schema.size());
                        editText.setFilters(fArray);
                        mForm.addView(textView);
                        mForm.addView(editText);
                        if(schema.getComment().compareTo("")!=0){
                            TextView commentView = new TextView(getContext());
                            commentView.setLayoutParams(lparams);
                            commentView.setTextColor(getResources().getColor(R.color.textColor));
                            System.out.println("Comment :"+schema.getComment()+":");
                            commentView.setText("Hint : "+schema.getComment());
                            mForm.addView(commentView);
                        }
                        i++;
                    }
                }
                Button form_submit = new Button(getContext());
                LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                buttonParams.gravity = Gravity.CENTER;
                buttonParams.setMargins(0,10,0,0);
                form_submit.setLayoutParams(buttonParams);
                form_submit.setId(R.id.form_submit);
                form_submit.setTextColor(getResources().getColor(R.color.textColor));
                form_submit.setHintTextColor(getResources().getColor(R.color.textColor));
                form_submit.setText("Submit");
                mForm.addView(form_submit);
                form_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        submitForm();
                    }
                });
            }
        });
    }

    public void hideProgress(){
        mProgressBar.setVisibility(View.INVISIBLE);
        mForm.setVisibility(View.VISIBLE);
        mError.setVisibility(View.INVISIBLE);
    }
    public void resetForm(){
            emp_id.setText("");
            name.setText("");
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
    }
}
