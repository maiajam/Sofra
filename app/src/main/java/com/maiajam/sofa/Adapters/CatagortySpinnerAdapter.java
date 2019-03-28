package com.maiajam.sofa.Adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.maiajam.sofa.R;
import com.maiajam.sofa.data.models.CategorySpinner;

import java.util.ArrayList;
import java.util.List;

public class CatagortySpinnerAdapter extends ArrayAdapter {


    private Context mContext;
    private ArrayList<CategorySpinner> listState;
    private CatagortySpinnerAdapter myAdapter;
    private boolean isFromView = false;

    public CatagortySpinnerAdapter(Context context, int resource, List<CategorySpinner> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.listState = (ArrayList<CategorySpinner>) objects;
        this.myAdapter = this;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(final int position, View convertView,
                              ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.spinner_item, null);
            holder = new ViewHolder();
            holder.mTextView = (TextView) convertView
                    .findViewById(R.id.text);
            holder.mCheckBox = (CheckBox) convertView
                    .findViewById(R.id.checkbox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTextView.setText(listState.get(position).getTitle());

        // To check weather checked event fire from getview() or user input
        isFromView = true;
        holder.mCheckBox.setChecked(listState.get(position).isSelected());
        isFromView = false;

        if ((position == 0)) {
            holder.mCheckBox.setVisibility(View.INVISIBLE);
        } else {
            holder.mCheckBox.setVisibility(View.VISIBLE);
        }
        holder.mCheckBox.setTag(position);
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int getPosition = (Integer) buttonView.getTag();

                if (!isFromView) {
                    Log.e("isFromView", !isFromView + "");
                    if (getPosition == 4) { // cam make a rule according to it for any position
                        if (!listState.get(4).isSelected()) {
                            if (isChecked) {
                                for (int i = 0; i < listState.size(); i++) {
                                    if (i < 4) {
                                        listState.get(i).setSelected(true);
                                    } else if (i == 4) {
                                        listState.get(i).setSelected(true);
                                    } else {
                                        listState.get(i).setSelected(false);
                                    }
                                }
                            }
                        } else {
                            if (!isChecked) {
                                for (int i = 0; i < listState.size(); i++) {
                                    if (i < 4) {
                                        listState.get(i).setSelected(false);
                                    } else if (i == 4) {
                                        listState.get(i).setSelected(false);
                                    }
                                }
                            }
                        }
                        myAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        return convertView;
    }

    private class ViewHolder {
        private TextView mTextView;
        private CheckBox mCheckBox;
    }
}
