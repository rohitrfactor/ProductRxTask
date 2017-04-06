package com.rohit.garorasu.productrxtask.Result;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rohit.garorasu.productrxtask.FilledForm;
import com.rohit.garorasu.productrxtask.R;

import java.util.ArrayList;

/**
 * Created by garorasu on 6/4/17.
 */

public class FormAdapter extends RecyclerView.Adapter<FormAdapter.ViewHolder> {
    private  Context context;
    private ArrayList<FilledForm> forms;
    public FormAdapter(Context context){
        this.context = context;
        this.forms = new ArrayList<FilledForm>();
    }
    public void addForms(ArrayList<FilledForm> forms){
        this.forms = forms;
        notifyDataSetChanged();
    }
    public void clear(){
        forms.clear();
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item,parent,false);
        ViewHolder viewHolder =  new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            FilledForm form = forms.get(position);
            holder.mId.setText(form.getId());
            holder.mEmpId.setText(form.getEmp_id());
            holder.mName.setText(form.getName());
            holder.mSex.setText(form.getSex());
    }

    @Override
    public int getItemCount() {
        return forms.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mId,mEmpId,mName,mSex;
        public ViewHolder(View itemView) {
            super(itemView);
            mId = (TextView) itemView.findViewById(R.id.mId);
            mEmpId = (TextView) itemView.findViewById(R.id.mEmpId);
            mName = (TextView) itemView.findViewById(R.id.mName);
            mSex = (TextView) itemView.findViewById(R.id.mSex);
        }
    }
}
