package com.example.abo_tam.photosgalary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by abo_tam on 9/2/2015.
 */
public class ClicksHandler implements Button.OnClickListener, Button.OnLongClickListener {

    private int position;
    private CourseDatabase db;
    private CourseAdapter courseAdapter;

    public ClicksHandler(CourseAdapter courseAdapter, CourseDatabase db, int position) {
        this.courseAdapter = courseAdapter;
        this.db = db;
        this.position = position;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.increase:
                Log.d(MainActivity.TAG, "increase button clicked on " + courseAdapter.getItem(position).getName());
                db.increaseAbsents(courseAdapter.getItem(position));
                MainActivity.setListView();
                break;
            case R.id.decrease:
                Log.d(MainActivity.TAG, "decrease button clicked on " + courseAdapter.getItem(position).getName());
                db.decreaseAbsents(courseAdapter.getItem(position));
                MainActivity.setListView();
                break;
            default:
        }
    }

    @Override
    public boolean onLongClick(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.getActivity());
        builder.setTitle(R.string.dialogTitle)
                .setMessage(R.string.dialogMessage)
                .setPositiveButton(R.string.dialogPositive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.removeCourse(courseAdapter.getItem(position));
                        MainActivity.setListView();
                    }
                })
                .setNegativeButton(R.string.dialogNegative, null);
        builder.create();
        builder.show();
        return true;
    }
}
