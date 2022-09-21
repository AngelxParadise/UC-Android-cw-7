package com.example.headphones;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter {

    ArrayList<Headphones> headphonesList = new ArrayList<>();
    Context context;

    public ItemAdapter(ArrayList<Headphones> phoneList, Context context) {
        this.headphonesList = phoneList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ((ViewHolder)holder).textname.setText(headphonesList.get(position).getItemName());
        ((ViewHolder)holder).textviewprice.setText(headphonesList.get(position).getItemPrice() + " KD");

        Picasso.with(context).load(headphonesList.get(position).getItemimg()).into(((ViewHolder)holder).img);

        ((ViewHolder)holder).v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("headphones", headphonesList.get(position));
                context.startActivity(intent);
            }
        });

        ((ViewHolder)holder).delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Headphones removedhp = headphonesList.get(position);
                int x = position;

                AlertDialog.Builder alert = new AlertDialog.Builder(context)
                        .setTitle("Attention")
                        .setMessage("Are you sure you want to delete?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                headphonesList.remove(position);
                                notifyDataSetChanged();
                                Snackbar.make(context, v, "One item deleted", 5000)
                                        .setAction("Undo", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                headphonesList.add(x, removedhp);
                                                notifyDataSetChanged();
                                            }
                                        }).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alert.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return headphonesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img, delete;
        TextView textname, textviewprice;
        View v;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;
            img = v.findViewById(R.id.imageView);
            textname = v.findViewById(R.id.textViewName);
            textviewprice = v.findViewById(R.id.textViewPrice);
            delete = v.findViewById(R.id.imageViewdelete);
        }
    }

}
