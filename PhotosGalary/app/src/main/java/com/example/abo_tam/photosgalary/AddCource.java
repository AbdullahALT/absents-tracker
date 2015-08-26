package com.example.abo_tam.photosgalary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
                        if (editText.getText().length() != 0) {

                            res = addAndFinish();
                        } else
                            res = false;
                        Toast.makeText(AddCource.this, "the result is " + res, Toast.LENGTH_LONG).show();

                    }
                }
        );


        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(isValid(actionId)) {
                    addAndFinish();
                    return true;
                }
                return false;
            }
        });

    }

    private boolean addAndFinish(){
        //TODO: enter key add two times
        boolean res = db.insertCource(
                new Cource(editText.getText().toString())
        );
        MainActivity.setListView();
        finish();
        return res;
    }

    private boolean isValid(int actionId){
        return EditorInfo.IME_ACTION_DONE == actionId && editText.getText().toString().length() != 0 && !editText.getText().toString().contains(" ");
    }

}
