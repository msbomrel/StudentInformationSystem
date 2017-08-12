package com.example.msbomrel.studentinformationsystem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.msbomrel.studentinformationsystem.AddStudent;
import com.example.msbomrel.studentinformationsystem.Model.Student;
import com.example.msbomrel.studentinformationsystem.R;

import java.util.List;


/**
 * Created by mohan on 4/16/2017.
 */

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private List<Student> mList;
    private Context context;

    public AdapterData(Context context, List<Student> mList)
    {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_onestudent, parent, false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        Student student =  mList.get(position);
        holder.txt_name.setText(student.getName());
        holder.txt_batch.setText(student.getBatch());
        holder.txt_mail.setText(student.getEmail());
        holder.txt_mobile.setText(student.getMobile());
        holder.student = student;

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView txt_name,txt_batch,txt_mobile,txt_mail;
        ImageButton dial;
        Student student;

        public HolderData(View v){
            super(v);
            txt_name=(TextView) v.findViewById(R.id.name);
            txt_batch=(TextView) v.findViewById(R.id.batch);
            txt_mail=(TextView) v.findViewById(R.id.mobile);
            txt_mobile=(TextView) v.findViewById(R.id.email);
//            dial = (ImageButton) v.findViewById(R.id.dialmobile) ;

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goInput = new Intent(context, AddStudent.class);
                    goInput.putExtra("id", student.getId());
                    goInput.putExtra("name", student.getName());
                    goInput.putExtra("batch", student.getBatch());
                    goInput.putExtra("email", student.getEmail());
                    goInput.putExtra("mobile", student.getMobile());

                    context.startActivity(goInput);
                }
            });

//            dial.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse(student.getMobile()));
//                    initActivity(call);
//                }
//            });

        }
//        private void initActivity(Intent intent) {
//            context.startActivity(intent);
//        }

    }
}