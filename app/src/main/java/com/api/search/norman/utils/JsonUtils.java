package com.api.search.norman.utils;

import android.content.Context;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import com.api.search.norman.R;
import com.api.search.norman.models.Data;
import com.api.search.norman.models.Rating;
import com.api.search.norman.models.Shop;
import com.api.search.norman.models.Status;
import com.api.search.norman.models.WholesalePrice;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by m.normansyah on 02/10/2015.
 */
public class JsonUtils {

    public Object[] getStatus(Context mContext) throws IOException{
        JsonReader mJsonReader = FactoryHelper.onJsonReaderCreate(FactoryHelper.getJsonExample(mContext));
//        mJsonReader.nextName();
        Object[] rets = new Object[5];

        mJsonReader.beginObject();
        Status status = new Status();
        if(mJsonReader.nextName().equals(mContext.getString(R.string.status))){
            mJsonReader.beginObject();
            String d = null;
            int b = 0;
            String a = mJsonReader.nextName();
            if(a.equals(mContext.getString(R.string.error_code)))
                b = mJsonReader.nextInt();
            String c = mJsonReader.nextName();
            if(c.equals(mContext.getString(R.string.message)))
                d = mJsonReader.nextString();

            status.setError_code(b);
            status.setMessage(d);

            Log.d(Constant.LOG, " masuk sini !!! " + a + " " + b + " " + c + " " + d);// +mJsonReader.nextName()+""+mJsonReader.nextName()
            mJsonReader.endObject();
            rets[0] = status;
        }
        Data data = new Data();
        if(mJsonReader.nextName().equals(mContext.getString(R.string.data))){
            Log.d(Constant.LOG, "masuk sini #2 !!! ");

            mJsonReader.beginArray();
            while(mJsonReader.hasNext()) {
                Data.InnerData innerData = new Data.InnerData();

                mJsonReader.beginObject();
                int a = 0;
                String b = null,c= null,d= null, e=null;
                if (mJsonReader.nextName().equals(mContext.getString(R.string.id)))
                    a=mJsonReader.nextInt();
                if (mJsonReader.nextName().equals(mContext.getString(R.string.name)))
                    b=mJsonReader.nextString();
                if (mJsonReader.nextName().equals(mContext.getString(R.string.uri)))
                    c=mJsonReader.nextString();
                if (mJsonReader.nextName().equals(mContext.getString(R.string.image_uri)))
                    d=mJsonReader.nextString();
                if (mJsonReader.nextName().equals(mContext.getString(R.string.price)))
                    e=mJsonReader.nextString();

                innerData.setId(a);
                innerData.setName(b);
                innerData.setUri(c);
                innerData.setImageUri(d);
                innerData.setPrice(e);

                Log.d(Constant.LOG, a + " " + b + " " + c + " " + d + " " + e);

                if (mJsonReader.nextName().equals(mContext.getString(R.string.shop))) {
                    Log.d(Constant.LOG, "masuk sini #3 !!! ");
                    int f = 0, g= 0, h=0, i=0;
                    Shop shop = new Shop();
                    mJsonReader.beginObject();
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.id)))
                        a=mJsonReader.nextInt();
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.name)))
                        b=mJsonReader.nextString();
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.uri)))
                        c=mJsonReader.nextString();
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.is_gold)))
                        f = mJsonReader.nextInt();

                    shop.setId(a);
                    shop.setName(b);
                    shop.setUri(c);
                    shop.setIs_gold(f);

                    if(mJsonReader.nextName().equals(mContext.getString(R.string.rating))){
                        mJsonReader.beginObject();
                        Rating rating = new Rating();
                        if (mJsonReader.nextName().equals(mContext.getString(R.string.speed)))
                            f = mJsonReader.nextInt();
                        if (mJsonReader.nextName().equals(mContext.getString(R.string.accuracy)))
                            g = mJsonReader.nextInt();
                        if (mJsonReader.nextName().equals(mContext.getString(R.string.service)))
                            h = mJsonReader.nextInt();
                        if (mJsonReader.nextName().equals(mContext.getString(R.string.reputation_score)))
                            i = mJsonReader.nextInt();

                        rating.setSpeed(f);
                        rating.setAccuracy(g);
                        rating.setService(h);
                        rating.setReputation_score(i);
                        shop.setRating(rating);

                        mJsonReader.endObject();
                    }
                    Log.d(Constant.LOG, f + " " + g + " " + h + " " + i);
                    String k=null,l=null,m=null;
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.location)))
                        k=mJsonReader.nextString();
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.reputation_image_uri)))
                        l=mJsonReader.nextString();
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.shop_lucky)))
                        m=mJsonReader.nextString();

                    shop.setLocation(k);
                    shop.setReputationImageUri(l);
                    shop.setShopLucky(m);

                    innerData.setShop(shop);

                    Log.d(Constant.LOG, k + " " + l + " " + m );
                    mJsonReader.endObject();
                }


                WholesalePrice wholesalePrice = null;
                if(mJsonReader.hasNext() && mJsonReader.nextName().equals(mContext.getString(R.string.wholesale_price))){
                    wholesalePrice = new WholesalePrice();
                    mJsonReader.beginArray();
                    while(mJsonReader.hasNext()){
                        mJsonReader.beginObject();
                        int n = 0, o = 0, p = 0;
                        WholesalePrice.Price price = new WholesalePrice.Price();
                        if (mJsonReader.nextName().equals(mContext.getString(R.string.count_min)))
                            n = mJsonReader.nextInt();
                        if (mJsonReader.nextName().equals(mContext.getString(R.string.count_max)))
                            o = mJsonReader.nextInt();
                        String q = null;
                        if (mJsonReader.nextName().equals(mContext.getString(R.string.price)))
                            q = mJsonReader.nextString();

                        price.setCount_min(n);
                        price.setCount_max(o);
                        price.setPrice(q);
                        List<WholesalePrice.Price> prices = wholesalePrice.getPrices();
                        prices.add(price);
                        wholesalePrice.setPrices(prices);

                        Log.d(Constant.LOG, n+" "+o+" "+p);
                        mJsonReader.endObject();
                    }
                    mJsonReader.endArray();
                }
                innerData.setWholesalePrice(wholesalePrice);
                mJsonReader.endObject();
                List<Data.InnerData> tmp = data.getDatas();
                tmp.add(innerData);
                data.setDatas(tmp);
            }
            mJsonReader.endArray();
        }
        rets[1] = data;
        if(mJsonReader.nextName().equals(mContext.getString(R.string.header))){
            mJsonReader.beginObject();
            int n = 0, o = 0;
            if (mJsonReader.nextName().equals(mContext.getString(R.string.total_data)))
                n = mJsonReader.nextInt();
            if (mJsonReader.nextName().equals(mContext.getString(R.string.total_data_no_category)))
                o = mJsonReader.nextInt();
            Log.d(Constant.LOG, " masuk sini !!! " + n + " " + o);
            mJsonReader.endObject();
        }
        if(mJsonReader.nextName().equals(mContext.getString(R.string.category))){
            mJsonReader.beginObject();
            if(mJsonReader.nextName().equals(mContext.getString(R.string.data))){
                mJsonReader.beginObject();
                while(mJsonReader.hasNext()&&mJsonReader.nextName()!=null){
                    mJsonReader.beginObject();
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.id)))
                        mJsonReader.nextString();
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.name)))
                        mJsonReader.nextString();
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.total_data)))
                        mJsonReader.nextString();
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.parent_id)))
                        mJsonReader.nextInt();
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.child_id))) {
                        if(mJsonReader.peek() != JsonToken.NULL ){
                            mJsonReader.beginArray();
                            while(mJsonReader.hasNext()){
                                mJsonReader.nextInt();
                            }
                            mJsonReader.endArray();
                        }else{
                            mJsonReader.skipValue();
                        }

                    }
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.level)))
                        mJsonReader.nextInt();
                    mJsonReader.endObject();
                }
                mJsonReader.endObject();
                if (mJsonReader.nextName().equals(mContext.getString(R.string.selected_id)))
                    mJsonReader.nextString();
            }
            mJsonReader.endObject();
        }
        if(mJsonReader.nextName().equals(mContext.getString(R.string.filter))){
            mJsonReader.beginObject();
            if(mJsonReader.nextName().equals(mContext.getString(R.string.q))){
                mJsonReader.nextString();
            }
            mJsonReader.endObject();
        }
        mJsonReader.endObject();
        mJsonReader.close();
        return rets;
    }


}
