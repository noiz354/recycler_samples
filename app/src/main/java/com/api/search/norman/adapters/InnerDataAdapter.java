package com.api.search.norman.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.api.search.norman.R;
import com.api.search.norman.models.Data;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by m.normansyah on 02/10/2015.
 */
public class InnerDataAdapter extends RecyclerView.Adapter<InnerDataAdapter.ViewHolder>{

    public InnerDataAdapter(Data data){
        this.data = data;
    }

    public void addHeaderView(View view) {
        this.mView = view;
    }

    private Data data;
    private final int TYPE_HEADER = 0;
    private final int TYPE_CHILD = 1;
    private View mView;
    private Picasso picasso;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (mView != null && i == TYPE_HEADER) {
            return new ViewHolder(mView);
        }else {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_prod_view, viewGroup, false);
            ViewHolder vh = new ViewHolder(v);
            if(picasso == null)
                picasso = picasso.with(viewGroup.getContext());
            return vh;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (viewHolder.getItemViewType() == TYPE_HEADER) return;
        if (mView != null) {
            i = i - 1;
        }
        // set data here
        if(data.getDatas().get(i).getName()!=null)
            viewHolder.prodName.setText(viewHolder.prodName.getText()+data.getDatas().get(i).getName());
        if(data.getDatas().get(i).getShop().getName()!=null)
            viewHolder.prodShopName.setText(viewHolder.prodShopName.getText()+" : "+data.getDatas().get(i).getShop().getName());
        if(data.getDatas().get(i).getPrice()!=null)
            viewHolder.prodPrice.setText(viewHolder.prodPrice.getText()+" : "+data.getDatas().get(i).getPrice());
        Picasso.with(viewHolder.prodImg.getContext()).load(data.getDatas().get(i).getImageUri()).error(R.mipmap.my_avatar).into(viewHolder.prodImg);

    }

    @Override
    public int getItemCount() {
        return mView != null ? data.getDatas().size() + 1 : data.getDatas().size();
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_HEADER : TYPE_CHILD;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView prodName;
        public ImageView prodImg;
        public TextView prodShopName;
        public TextView prodPrice;
        public TextView boughtReviewView;

        public ViewHolder(View v) {
            super(v);
            prodName = (TextView)v.findViewById(R.id.prod_name);
            prodImg = (ImageView)v.findViewById(R.id.prod_img);
            prodShopName = (TextView)v.findViewById(R.id.prod_shop_name);
            prodPrice = (TextView)v.findViewById(R.id.prod_price);
            boughtReviewView = (TextView)v.findViewById(R.id.bought_review_view);
        }
    }

    public void addNewData(Data newData){
        for(int i=0;i<newData.getDatas().size();i++){
            data.addData(newData.getDatas().get(i));
        }
        notifyDataSetChanged();
    }
}
