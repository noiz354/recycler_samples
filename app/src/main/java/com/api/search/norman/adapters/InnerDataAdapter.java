package com.api.search.norman.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.api.search.norman.R;
import com.api.search.norman.models.Data;

import org.w3c.dom.Text;

/**
 * Created by m.normansyah on 02/10/2015.
 */
public class InnerDataAdapter extends RecyclerView.Adapter<InnerDataAdapter.ViewHolder>{

    public InnerDataAdapter(Data data){
        this.data = data;
    }

    private Data data;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_test, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.mTextView.setText(data.getDatas().get(i).getId()+" "+data.getDatas().get(i).getImageUri());
    }

    @Override
    public int getItemCount() {
        return data.getDatas().size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView)v.findViewById(R.id.hitAllhere);
        }
    }
}
