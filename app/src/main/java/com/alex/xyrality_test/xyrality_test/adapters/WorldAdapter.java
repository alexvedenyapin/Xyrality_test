package com.alex.xyrality_test.xyrality_test.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alex.xyrality_test.xyrality_test.R;
import com.alex.xyrality_test.xyrality_test.rest.results.World;

public class WorldAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private World[] mWorlds;

    public WorldAdapter(Context ctx, World[] worlds) {
        mInflater = LayoutInflater.from(ctx);
        mWorlds = worlds;
    }

    @Override
    public int getCount() {
        return mWorlds.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        final ViewHolder holder;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.world_item, parent, false);
            holder = new ViewHolder();
            holder.worldName = (TextView) view.findViewById(R.id.worldNameTv);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        holder.worldName.setText(mWorlds[position].getName());

        return view;
    }

    private class ViewHolder {
        TextView worldName;
    }
}
