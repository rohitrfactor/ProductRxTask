package com.rohit.garorasu.productrxtask.Result;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rohit.garorasu.productrxtask.R;
import com.rohit.garorasu.productrxtask.Schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by garorasu on 6/4/17.
 */

public class FormAdapter extends RecyclerView.Adapter<FormAdapter.ViewHolder> {
    private  Context context;
    private ArrayList<HashMap<String,String>> forms;
    private ArrayList<Schema> schemas;
    public FormAdapter(Context context){
        this.context = context;
        this.forms = new ArrayList<>();
    }
    public void addForms(ArrayList<HashMap<String,String>> forms){
        this.forms = forms;
        notifyDataSetChanged();
    }
    public void addSchemas(ArrayList<Schema> schemas){
           this.schemas = schemas;
    }
    public void clear(){
        forms.clear();
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.list_item,parent,false);
        LinearLayout cardView = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10,10,10,10);
        params.gravity = Gravity.CENTER;
        cardView.setLayoutParams(params);
        cardView.setPadding(10,10,10,10);
        cardView.setOrientation(LinearLayout.VERTICAL);

        for(int i=0;i<schemas.size();i++){
            TextView textView = new TextView(context);
            LinearLayout.LayoutParams larams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            larams.setMargins(10,10,10,10);
            textView.setLayoutParams(larams);
            textView.setText("Sample text "+i);
            textView.setId(i);
            textView.setTextSize(22);
            textView.setTextColor(context.getResources().getColor(R.color.textColor));
            cardView.addView(textView);
        }
        view.addView(cardView);
        ViewHolder viewHolder =  new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HashMap<String, String> tmpData = (HashMap<String, String>) forms.get(position);
        Set<String> key = tmpData.keySet();
        Iterator it = key.iterator();
        int i=0;
        while (it.hasNext()) {
            String hmKey = (String)it.next();
            String hmData = tmpData.get(hmKey);
            TextView textView = (TextView) holder.itemView.findViewById(i);
            textView.setText(hmKey+" : "+hmData);
            System.out.println("Key: "+hmKey +" & Data: "+hmData+" & Position : "+position);
            i++;
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    @Override
    public int getItemCount() {
        return forms.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
