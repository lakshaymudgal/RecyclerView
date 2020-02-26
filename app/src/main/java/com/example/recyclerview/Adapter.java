package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {

    private Context context;
    private ArrayList<ViewModel> modelsList;

//    !!!!!!!!!! Interface and methods to move the data from another activity !!!!!!!!!!!!!!!!!!

    private onItemClickListener listener;

    public interface onItemClickListener{
        void onItenClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        this.listener = listener;
    }

//    !!!!!!!!!!!! End !!!!!!!!!!!!!!!!!!!!!!!

    public Adapter(Context context, ArrayList<ViewModel> modelsList){

        this.context = context;
        this.modelsList = modelsList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_model, null, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        ViewModel viewModel = modelsList.get(position);

        String name = viewModel.getName();
        int id = viewModel.getId();

        holder.name.setText(name);
        holder.id.setText("Id: " + id);

    }

    @Override
    public int getItemCount() {
        return modelsList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView id;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_name);
            id = itemView.findViewById(R.id.tv_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(listener != null){
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            listener.onItenClick(position);

                        }
                    }
                }
            });


        }
    }
}
