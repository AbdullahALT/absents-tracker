package com.example.abo_tam.photosgalary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by abo_tam on 8/19/2015.
 */
public class CourseAdapter extends ArrayAdapter<Course> {

    private static CourseDatabase db;
    private int position;

    public CourseAdapter(Context context, Course[] objects) {
        super(context, R.layout.list_view, objects);
        db = new CourseDatabase(MainActivity.getActivity());
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View courseView = inflater.inflate(R.layout.list_view, parent, false);

        TextView courseName = (TextView) courseView.findViewById(R.id.courseName);
        TextView absents = (TextView) courseView.findViewById(R.id.absentCounter);
        Button increase = (Button) courseView.findViewById(R.id.increase);
        Button decrease = (Button) courseView.findViewById(R.id.decrease);
        this.position = position;

        courseName.setText(getItem(position).getName());
        absents.setText(String.valueOf(getItem(position).getAbsents()));

        increase.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(MainActivity.TAG, "increase button clicked on " + getItem(position).getName());
                        db.increaseAbsents(getItem(position));
                        MainActivity.setListView();
                    }
                }
        );

        decrease.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(MainActivity.TAG, "decrease button clicked on " + getItem(position).getName());
                        db.decreaseAbsents(getItem(position));
                        MainActivity.setListView();
                    }
                }
        );
        courseName.setOnLongClickListener(
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.getActivity());
                        builder.setTitle(R.string.dialogTitle)
                                .setMessage(R.string.dialogMessage)
                                .setPositiveButton(R.string.dialogPositive, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        db.removeCourse(getItem(position));
                                        MainActivity.setListView();
                                    }
                                })
                                .setNegativeButton(R.string.dialogNegative, null);
                        builder.create();
                        builder.show();
                        return true;
                    }
                }
        );

        return courseView;
    }

}
