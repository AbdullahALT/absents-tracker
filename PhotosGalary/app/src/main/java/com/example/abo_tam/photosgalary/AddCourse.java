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

public class AddCourse extends Activity {

    private static EditText editText;
    private static Button button;
    private static CourseDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cource);
        Intent intent = getIntent();


        //TODO: set maximum 7 char
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        db = new CourseDatabase(this);

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (editText.getText().length() != 0) {
                            addAndFinish();
                        } else

                            Toast.makeText(AddCourse.this, "Course " + editText.getText().toString() + " is not added", Toast.LENGTH_LONG).show();

                    }
                }
        );


        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (isValid(actionId)) {
                    addAndFinish();
                    return true;
                }
                Toast.makeText(AddCourse.this, "Course " + editText.getText().toString() + " is not added", Toast.LENGTH_LONG).show();
                return false;
            }
        });

    }

    private void addAndFinish(){
        //TODO: enter key add two times
        boolean isSuccess = db.insertCourse(
                new Course(editText.getText().toString())
        );
        if(isSuccess) {
            Toast.makeText(this, "Course " + editText.getText().toString() + " is added", Toast.LENGTH_LONG).show();
            MainActivity.setListView();
            finish();
        }
    }

    private boolean isValid(int actionId){
        return EditorInfo.IME_ACTION_DONE == actionId && editText.getText().toString().length() != 0 && !editText.getText().toString().contains(" ");
    }

}
