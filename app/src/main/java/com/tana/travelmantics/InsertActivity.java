package com.tana.travelmantics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    EditText mTxtTitle;
    EditText mTxtPrice;
    EditText mTxtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("traveldeals");
        mTxtTitle = (EditText) findViewById(R.id.txtTitle);
        mTxtPrice = (EditText) findViewById(R.id.txtPrice);
        mTxtDescription = (EditText) findViewById(R.id.txtDescription);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_menu:
                saveDeal();
                Toast.makeText(this, "Deal Saved", Toast.LENGTH_LONG).show();
                clean();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveDeal() {
        String title = mTxtTitle.getText().toString();
        String price = mTxtPrice.getText().toString();
        String description = mTxtDescription.getText().toString();

        TravelDeal travelDeal = new TravelDeal(title, price, description, "");

        mDatabaseReference.push().setValue(travelDeal);
    }

    private void clean() {
        mTxtTitle.setText("");
        mTxtPrice.setText("");
        mTxtDescription.setText("");
        mTxtTitle.requestFocus();
    }
}