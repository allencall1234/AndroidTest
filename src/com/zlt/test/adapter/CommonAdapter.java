package com.zlt.test.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 自定义多用适配器
 * @author Administrator
 *
 * @param <T>
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

	private LayoutInflater mInflater;
	private Context mContext;
	private List<T> mList;
	
	private final int mItemLayoutId;
	
	public CommonAdapter(Context context,List<T> list,int itemLayoutId) {
		super();
		// TODO Auto-generated constructor stub
		mContext = context;
		mList = list;
		mInflater = LayoutInflater.from(mContext);
		mItemLayoutId = itemLayoutId;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder viewHolder = getViewHolder(position,convertView,parent);
		
		convert(viewHolder,(T) getItem(position));
		 
		return viewHolder.getConvertView();
	}

	public abstract void convert(ViewHolder viewHolder,T item);

	private ViewHolder getViewHolder(int position, View convertView,
			ViewGroup parent) {
		// TODO Auto-generated method stub
		return ViewHolder.get(mContext, convertView, parent, mItemLayoutId, position);
	}
}
