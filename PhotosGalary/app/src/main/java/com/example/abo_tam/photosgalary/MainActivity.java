package com.example.abo_tam.photosgalary;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    public final static String TAG = "test";
    public static Activity activity;
    private static ListView listView;
    private static Button addButton;
    private static Database db;
    private Intent intent;

    public static void setListView() {
        listView = (ListView) activity.findViewById(R.id.listView);
        Cursor cursor = db.getData();
        Cource[] cources = new Cource[cursor.getCount()];

        if (cursor.getCount() == 0)
            Toast.makeText(activity, "no rows", Toast.LENGTH_LONG).show();

        int i = 0;
        while (cursor.moveToNext()) {
            cources[i++] = new Cource(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
        }
        ListAdapter adapter = new CourceAdapter(activity, cources);
        listView.setAdapter(adapter);
    }

    public static void increaseAbsent(Cource cource) {
        db.execute("UPDATE Course SET Absents = Absents + 1 WHERE ID = " + cource.getId());
        setListView();
    }

    public static void decreaseAbsent(Cource cource) {
        if (cource.getAbsents() == 0)
            return;
        db.execute("UPDATE Course SET Absents = Absents - 1 WHERE ID = " + cource.getId());
        setListView();
    }

    public static void deleteCourse(final Cource cource) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.dialogTitle)
                .setMessage(R.string.dialogMessage)
                .setPositiveButton(R.string.dialogPositive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.execute("DELETE FROM Course WHERE ID = " + cource.getId());
                        setListView();
                    }
                })
                .setNegativeButton(R.string.dialogNegative, null);
        builder.create();
        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, AddCource.class);

        db = new Database(this);
        addButton = (Button) findViewById(R.id.add);
        activity = this;

        Log.d(TAG, "list view have been found");
        setListView();
        //test();
        setAddButton();
        Log.d(TAG, "list view seted");


    }

    public void setAddButton() {
        addButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(intent);
                    }
                }
        );
    }

}
