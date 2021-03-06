package com.tobot.disinfect.module.deploy.edit;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.slamtec.slamware.geometry.PointF;
import com.tobot.common.base.BaseRecyclerAdapter;
import com.tobot.common.view.ItemSplitLineDecoration;
import com.tobot.disinfect.R;
import com.tobot.slam.data.LocationBean;

import java.util.List;

/**
 * 添加虚拟轨道
 *
 * @author houdeming
 * @date 2020/5/9
 */
public class AddLineView extends LinearLayout implements BaseRecyclerAdapter.OnItemClickListener<LocationBean> {
    private PointAdapter mPointAdapter;
    private OnEditListener mOnEditListener;
    private List<LocationBean> mLocationList;

    public AddLineView(Context context) {
        this(context, null);
    }

    public AddLineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.view_line_add, this);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_num);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new ItemSplitLineDecoration(context, ItemSplitLineDecoration.VERTICAL, true));
        mPointAdapter = new PointAdapter(context, R.layout.recycler_item_num);
        mPointAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mPointAdapter);
    }

    @Override
    public void onItemClick(int position, LocationBean data) {
        if (data != null && mOnEditListener != null) {
            PointF pointF = new PointF(data.getX(), data.getY());
            mOnEditListener.onAddLine(pointF);
        }
    }

    public void init(List<LocationBean> data, OnEditListener listener) {
        mLocationList = data;
        mOnEditListener = listener;
        setVisibility(VISIBLE);
        mPointAdapter.setData(mLocationList);
    }

    public void remove() {
        setVisibility(GONE);
        if (mLocationList != null && !mLocationList.isEmpty()) {
            mLocationList.clear();
        }
    }
}
