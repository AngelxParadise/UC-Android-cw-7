package com.example.headphones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView nametext = findViewById(R.id.textViewnamed);
        TextView pricetext = findViewById(R.id.textViewpriced);
        ImageView imghp = findViewById(R.id.imageViewhp);

        Bundle bundle = getIntent().getExtras();

        Headphones sendhp = (Headphones) bundle.getSerializable("headphones");

        nametext.setText(sendhp.getItemName());
        pricetext.setText(sendhp.getItemPrice() + " KD");

        Picasso.with(this).load(sendhp.getItemimg()).into(imghp);
    }
}