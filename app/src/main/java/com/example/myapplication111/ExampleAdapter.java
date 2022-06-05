package com.example.myapplication111;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> implements Filterable {
private List<ExampleItem> exampleList;
private List<ExampleItem> exampleListFull;
    Context mContext;
class  ExampleViewHolder extends RecyclerView.ViewHolder {
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;

    ExampleViewHolder(View itemView) {
        super(itemView);
        textView1 = itemView.findViewById(R.id.textView1);
        textView2 = itemView.findViewById(R.id.textView2);
        textView3 = itemView.findViewById(R.id.textView3);
        textView4 = itemView.findViewById(R.id.textView4);

    }

}
    ExampleAdapter(List<ExampleItem> exampleList,Context mContext) {
        this.exampleList = exampleList;
        this.mContext=mContext;
        exampleListFull = new ArrayList<>(exampleList);
    }
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
        return new ExampleViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ExampleViewHolder holder,int position){
        ExampleItem currentItem = exampleList.get(position);

        holder.textView1.setText(currentItem.getText1());
        holder.textView2.setText(currentItem.getText2());
        holder.textView3.setText(currentItem.getLat());
        holder.textView4.setText(currentItem.getLon());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String room=holder.textView1.getText().toString();
                String hool=holder.textView2.getText().toString();
                String lat=holder.textView3.getText().toString();
                String lon=holder.textView4.getText().toString();

                Intent intent = new Intent(mContext,SearchActivity.class);
                intent.putExtra("Room",room);
                intent.putExtra("Hool",hool);
                intent.putExtra("LAT",lat);
                intent.putExtra("LON",lon);
                mContext.startActivity(intent);

            }
        });



    }
    @Override
    public int getItemCount(){
        return exampleList.size();
    }



    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ExampleItem> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ExampleItem item : exampleListFull) {
                    if (item.getText1().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            exampleList.clear();
            exampleList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}