package com.api.search.norman.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by m.normansyah on 05/10/2015.
 */
public class Category {
    public static final String TAG = Category.class.getSimpleName();
    List<Data> datas;
    String selectedId;

    public String getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(String selectedId) {
        this.selectedId = selectedId;
    }

    public Category(){
        datas = new ArrayList<Data>();
    }

    public List<Data> getDatas() {
        return datas;
    }

    public void setDatas(List<Data> datas) {
        this.datas = datas;
    }

    public void addData(Data data){
        this.datas.add(data);
    }

    public static class Data{
        public static final String TAG = Data.class.getSimpleName();
        String id;// 1
        String name ;// 2
        String totalData ;// 3
        int parentId;// 4
        int level;// 5
        List<Integer> childId;// 6

        public Data(){
            childId = new ArrayList<>();
        }

        public Data(String id, String name, String totalData, int parentId, int level, List<Integer> childId) {
            this.id = id;
            this.name = name;
            this.totalData = totalData;
            this.parentId = parentId;
            this.level = level;
            this.childId = childId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTotalData() {
            return totalData;
        }

        public void setTotalData(String totalData) {
            this.totalData = totalData;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public List<Integer> getChildId() {
            return childId;
        }

        public void setChildId(List<Integer> childId) {
            this.childId = childId;
        }
    }
}
