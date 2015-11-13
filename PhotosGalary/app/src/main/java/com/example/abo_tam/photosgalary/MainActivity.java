package com.example.abo_tam.photosgalary;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    public final static String TAG = "test";
    private static Activity activity;
    private static ListView listView;
    private static CourseDatabase db;
    private Intent intent;

    public static void setListView() {
        listView = (ListView) MainActivity.getActivity().findViewById(R.id.listView);
        Cursor cursor = db.getData();
        Course[] courses = new Course[cursor.getCount()];

        if (cursor.getCount() == 0)
            Toast.makeText(MainActivity.getActivity(), "no rows", Toast.LENGTH_LONG).show();

        int i = 0;
        while (cursor.moveToNext()) {
            courses[i++] = new Course(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
        }
        ListAdapter adapter = new CourseAdapter(MainActivity.getActivity(), courses);
        listView.setAdapter(adapter);
    }

    public static Activity getActivity(){
        return activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, AddCourse.class);

        db = new CourseDatabase(this);
        activity = this;

        Log.d(TAG, "list view have been found");
        setListView();
        View button = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_view_footer, null, false);

        Button b = (Button) button.findViewById(R.id.add);
        b.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(intent);
                    }
                }
        );

        listView.addFooterView(button);
        //test();
        Log.d(TAG, "list view seted");


    }



}
