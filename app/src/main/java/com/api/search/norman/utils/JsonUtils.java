package com.api.search.norman.utils;

import android.content.Context;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import com.api.search.norman.R;
import com.api.search.norman.models.Category;
import com.api.search.norman.models.Data;
import com.api.search.norman.models.Filter;
import com.api.search.norman.models.Header;
import com.api.search.norman.models.Rating;
import com.api.search.norman.models.Shop;
import com.api.search.norman.models.Status;
import com.api.search.norman.models.WholesalePrice;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by m.normansyah on 02/10/2015.
 */
public class JsonUtils implements  Constant.JsonUtilsConstant{
    public static final String TAG = JsonUtils.class.getSimpleName();

    public static Object[] getJsonDataFromAssets(Context mContext) throws IOException{
        JsonReader mJsonReader = FactoryHelper.onJsonReaderCreate(FactoryHelper.getJsonExample(mContext));
//        mJsonReader.nextName();
        Object[] rets = new Object[LENGTH];

        mJsonReader.beginObject();
        Status status = new Status();
        if(mJsonReader.nextName().equals(mContext.getString(R.string.status))){
            mJsonReader.beginObject();
            String message = null;
            int errorCode = 0;
            if(mJsonReader.nextName().equals(mContext.getString(R.string.error_code)))
                errorCode = mJsonReader.nextInt();
            if(mJsonReader.nextName().equals(mContext.getString(R.string.message)))
                message = mJsonReader.nextString();

            status.setError_code(errorCode);
            status.setMessage(message);

            Log.d(Constant.LOG, " masuk sini !!! " + errorCode + " " + message);
            mJsonReader.endObject();
            rets[STATUS] = status;
        }

        Data data = new Data();
        if(mJsonReader.nextName().equals(mContext.getString(R.string.data))){
            Log.d(Constant.LOG+separator+Data.TAG, "start process "+Data.TAG);

            mJsonReader.beginArray();
            while(mJsonReader.hasNext()) {
                Log.d(Constant.LOG+separator+Data.InnerData.TAG, "start process "+Data.InnerData.TAG);
                Data.InnerData innerData = new Data.InnerData();

                mJsonReader.beginObject();
                int id = 0;
                String name = null,url= null,image_uri= null, price=null;
                if (mJsonReader.nextName().equals(mContext.getString(R.string.id)))
                    id=mJsonReader.nextInt();
                if (mJsonReader.nextName().equals(mContext.getString(R.string.name)))
                    name=mJsonReader.nextString();
                if (mJsonReader.nextName().equals(mContext.getString(R.string.uri)))
                    url=mJsonReader.nextString();
                if (mJsonReader.nextName().equals(mContext.getString(R.string.image_uri)))
                    image_uri=mJsonReader.nextString();
                if (mJsonReader.nextName().equals(mContext.getString(R.string.price)))
                    price=mJsonReader.nextString();

                innerData.setId(id);
                innerData.setName(name);
                innerData.setUri(url);
                innerData.setImageUri(image_uri);
                innerData.setPrice(price);

                Log.d(Constant.LOG+separator+Data.TAG, id + " " + name + " " + url + " " + image_uri + " " + price);

                if (mJsonReader.nextName().equals(mContext.getString(R.string.shop))) {
                    Log.d(Constant.LOG+separator+Shop.TAG, "start creating "+ Shop.TAG);
                    int speed = 0, accuracy= 0, service=0, reputationScore=0;
                    Shop shop = new Shop();
                    mJsonReader.beginObject();
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.id)))
                        id=mJsonReader.nextInt();
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.name)))
                        name=mJsonReader.nextString();
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.uri)))
                        url=mJsonReader.nextString();
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.is_gold)))
                        speed = mJsonReader.nextInt();

                    shop.setId(id);
                    shop.setName(name);
                    shop.setUri(url);
                    shop.setIs_gold(speed);

                    if(mJsonReader.nextName().equals(mContext.getString(R.string.rating))){
                        mJsonReader.beginObject();
                        Log.d(Constant.LOG + separator + Rating.TAG, "start creating " + Rating.TAG);
                        Rating rating = new Rating();
                        if (mJsonReader.nextName().equals(mContext.getString(R.string.speed)))
                            speed = mJsonReader.nextInt();
                        if (mJsonReader.nextName().equals(mContext.getString(R.string.accuracy)))
                            accuracy = mJsonReader.nextInt();
                        if (mJsonReader.nextName().equals(mContext.getString(R.string.service)))
                            service = mJsonReader.nextInt();
                        if (mJsonReader.nextName().equals(mContext.getString(R.string.reputation_score)))
                            reputationScore = mJsonReader.nextInt();

                        rating.setSpeed(speed);
                        rating.setAccuracy(accuracy);
                        rating.setService(service);
                        rating.setReputation_score(reputationScore);
                        shop.setRating(rating);

                        mJsonReader.endObject();
                        Log.d(Constant.LOG + separator + Rating.TAG, "end creating " + Rating.TAG);
                    }
                    Log.d(Constant.LOG + separator + Rating.TAG, speed + " " + accuracy + " " + service + " " + reputationScore);
                    String location=null,reputationImageUri=null,shopLucky=null;
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.location)))
                        location=mJsonReader.nextString();
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.reputation_image_uri)))
                        reputationImageUri=mJsonReader.nextString();
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.shop_lucky)))
                        shopLucky=mJsonReader.nextString();

                    shop.setLocation(location);
                    shop.setReputationImageUri(reputationImageUri);
                    shop.setShopLucky(shopLucky);

                    innerData.setShop(shop);

                    Log.d(Constant.LOG+separator+Shop.TAG, location + " " + reputationImageUri + " " + shopLucky);
                    Log.d(Constant.LOG+separator+Shop.TAG, "end creating "+ Shop.TAG);
                    mJsonReader.endObject();
                }


                WholesalePrice wholesalePrice = null;
                if(mJsonReader.hasNext() && mJsonReader.nextName().equals(mContext.getString(R.string.wholesale_price))){
                    Log.d(Constant.LOG+separator+WholesalePrice.TAG, "start creating "+ WholesalePrice.TAG);
                    wholesalePrice = new WholesalePrice();
                    mJsonReader.beginArray();
                    while(mJsonReader.hasNext()){
                        mJsonReader.beginObject();
                        int countMin = 0, countMax = 0, p = 0;
                        WholesalePrice.Price aPrice = new WholesalePrice.Price();
                        if (mJsonReader.nextName().equals(mContext.getString(R.string.count_min)))
                            countMin = mJsonReader.nextInt();
                        if (mJsonReader.nextName().equals(mContext.getString(R.string.count_max)))
                            countMax = mJsonReader.nextInt();
                        String anoprice = null;
                        if (mJsonReader.nextName().equals(mContext.getString(R.string.price)))
                            anoprice = mJsonReader.nextString();

                        aPrice.setCount_min(countMin);
                        aPrice.setCount_max(countMax);
                        aPrice.setPrice(anoprice);

                        // TODO remove this if WholesalePrice.Price works
//                        List<WholesalePrice.Price> prices = wholesalePrice.getPrices();
//                        prices.add(aPrice);
//                        wholesalePrice.setPrices(prices);

                        wholesalePrice.addPrice(aPrice);

                        Log.d(Constant.LOG + separator + WholesalePrice.TAG, countMin+" "+countMax+" "+p);
                        mJsonReader.endObject();
                    }
                    mJsonReader.endArray();
                    Log.d(Constant.LOG + separator + WholesalePrice.TAG, "end creating " + WholesalePrice.TAG);
                }
                innerData.setWholesalePrice(wholesalePrice);
                mJsonReader.endObject();

                // TODO remove this if works
//                List<Data.InnerData> tmp = data.getDatas();
//                tmp.add(innerData);
//                data.setDatas(tmp);

                data.addData(innerData);
            }
            Log.d(Constant.LOG+separator+Data.InnerData.TAG, "end process "+Data.InnerData.TAG);
            mJsonReader.endArray();
        }
        rets[DATA] = data;

        Header header = null;
        if(mJsonReader.nextName().equals(mContext.getString(R.string.header))){
            mJsonReader.beginObject();
            int totalData = 0, totalDataNoCategory = 0;
            if (mJsonReader.nextName().equals(mContext.getString(R.string.total_data)))
                totalData = mJsonReader.nextInt();
            if (mJsonReader.nextName().equals(mContext.getString(R.string.total_data_no_category)))
                totalDataNoCategory = mJsonReader.nextInt();
            header = new Header(totalData, totalDataNoCategory);
            Log.d(Constant.LOG+"_"+TAG+"_"+Header.TAG, header.toString());
            mJsonReader.endObject();
        }
        rets[HEADER] = header;

        Category category = null;
        if(mJsonReader.nextName().equals(mContext.getString(R.string.category))){
            mJsonReader.beginObject();
            category = new Category();
            Log.d(Constant.LOG+"_"+TAG+"_"+Category.TAG, "start creating "+Category.TAG);

            if(mJsonReader.nextName().equals(mContext.getString(R.string.data))){
                mJsonReader.beginObject();
                while(mJsonReader.hasNext()&&mJsonReader.nextName()!=null){
                    mJsonReader.beginObject();

                    // create object
                    Category.Data data1 = new Category.Data();

                    String id = null, name = null, totalData = null;
                    int parentId = 0, level = 0;
                    // use list because there is not info about the length
                    List<Integer> childId = null;
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.id)))
                        id = mJsonReader.nextString();
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.name)))
                        name = mJsonReader.nextString();
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.total_data)))
                        totalData =mJsonReader.nextString();
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.parent_id)))
                        parentId =mJsonReader.nextInt();
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.child_id))) {
                        if(mJsonReader.peek() != JsonToken.NULL ){
                            mJsonReader.beginArray();
                            // create childId object
                            childId = new ArrayList<>();
                            while(mJsonReader.hasNext()){
                                int temp = mJsonReader.nextInt();
                                childId.add(temp);// add child id
                            }
                            mJsonReader.endArray();
                        }else{
                            // skip if there is no value
                            mJsonReader.skipValue();
                        }
                    }
                    if (mJsonReader.nextName().equals(mContext.getString(R.string.level)))
                        level = mJsonReader.nextInt();

                    data1.setChildId(childId);
                    data1.setId(id);
                    data1.setLevel(level);
                    data1.setName(name);
                    data1.setParentId(parentId);
                    data1.setTotalData(totalData);

                    // add to the main container
                    category.addData(data1);

                    mJsonReader.endObject();
                }
                mJsonReader.endObject();
                String selectedId = null;
                if (mJsonReader.nextName().equals(mContext.getString(R.string.selected_id)))
                    selectedId = mJsonReader.nextString();
                category.setSelectedId(selectedId);

            }
            rets[CATEGORY] = category;
            Log.d(Constant.LOG+"_"+TAG+"_"+Category.TAG, "end creating "+Category.TAG);
            mJsonReader.endObject();
        }

        Filter filter = null;
        if(mJsonReader.nextName().equals(mContext.getString(R.string.filter))){
            mJsonReader.beginObject();
            filter = new Filter();
            if(mJsonReader.nextName().equals(mContext.getString(R.string.q))){
                String q = mJsonReader.nextString();
                filter.setQuery(q);
            }
            rets[FILTER] = filter;
            mJsonReader.endObject();
        }
        mJsonReader.endObject();
        mJsonReader.close();
        return rets;
    }


}
