package com.example.abo_tam.photosgalary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCource extends Activity {

    private static EditText editText;
    private static Button button;
    private static Database db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cource);
        Intent intent = getIntent();

        //TODO: set maximum 7 char
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        db = new Database(this);

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean res;
                        if (!editText.getText().toString().equals("")) {

                            res = db.insertCource(
                                    new Cource(editText.getText().toString())
                                    //TODO: you need to refresh mainActivity's listView here
                            );
                            MainActivity.setListView();

                        } else
                            res = false;
                        Toast.makeText(AddCource.this, "the result is " + res, Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

}
