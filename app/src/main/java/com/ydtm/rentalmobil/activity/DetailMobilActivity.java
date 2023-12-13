package com.ydtm.rentalmobil.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ydtm.rentalmobil.R;
import com.ydtm.rentalmobil.helper.DataHelper;

public class DetailMobilActivity extends AppCompatActivity {

    private Cursor cursor;
    private String sMerk, sHarga, sGambar;
    private DataHelper dbHelper;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mobil);

        dbHelper = new DataHelper(this);

        // Get the intent that started this activity
        Intent intent = getIntent();

        // Get the value passed in the intent with the key "merk"
        String merk = intent.getStringExtra("merk");

        // Open a readable database
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Query the database to get details for the selected merk
        cursor = db.rawQuery("SELECT * FROM mobil WHERE merk = ?", new String[]{merk});

        // Move the cursor to the first row
        if (cursor.moveToFirst()) {
            // Get the values from the cursor
            sMerk = cursor.getString(0);
            sHarga = cursor.getString(1);

            // Get the corresponding image name
            sGambar = getGambarName(sMerk);
        }

        // Find views by ID
        ImageView ivGambar = findViewById(R.id.ivMobil);
        TextView tvMerk = findViewById(R.id.JMobil);
        TextView tvHarga = findViewById(R.id.JHarga);

        // Set values to the views
        tvMerk.setText(sMerk);
        ivGambar.setImageResource(getResources().getIdentifier(sGambar, "drawable", getPackageName()));
        tvHarga.setText("Rp. " + sHarga);

        // Set up the toolbar
        setupToolbar();
    }

    private String getGambarName(String merk) {
        // Switch statement to get the corresponding image name
        switch (merk) {
            case "Avanza":
                return "avanza";
            case "Xenia":
                return "xenia";
            case "Ertiga":
                return "ertiga";
            case "APV":
                return "apv";
            case "Innova":
                return "innova";
            case "Xpander":
                return "xpander";
            case "Pregio":
                return "pregio";
            case "Elf":
                return "elf";
            case "Alphard":
                return "alphard";
            default:
                return "";
        }
    }

    private void setupToolbar() {
        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.tbDetailMbl);
        toolbar.setTitle("Detail Mobil");
        setSupportActionBar(toolbar);

        // Enable the Up button for navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle toolbar item clicks
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
