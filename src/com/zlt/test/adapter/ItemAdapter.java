package com.zlt.test.adapter;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.PSource;

import com.zlt.test.R;

import android.R.string;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter<String> implements Filterable {

	private Context mContext;
	private List<String> mList;
	private LayoutInflater mInflater;
	private PersonFilter filter;
	private String preStr = "";

	public ItemAdapter(Context context, int textViewResourceId,
			List<String> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		mContext = context;
		mInflater = LayoutInflater.from(mContext);
		mList = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = new ViewHolder();
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item, null);
			viewHolder.textView = (TextView) convertView
					.findViewById(R.id.text_view);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		if (mList.get(position).toLowerCase().toString().contains(preStr)
				) {
			if (position < mList.size()) {
				viewHolder.textView.setText(mList.get(position).toString());
			}
		}

		return convertView;
	}
	
	public void setFilterString(String str) {
		// TODO Auto-generated method stub
		this.preStr = str;
		notifyDataSetChanged();
	}
	
	@Override
	public Filter getFilter() {
		Log.i("zlt", "getFilter() ");
		if (filter == null) {
			return new PersonFilter(mList);
		}
		return filter;
	}

	class ViewHolder {
		TextView textView;
	}

	class PersonFilter extends Filter {

		private List<String> original;

		public PersonFilter(List<String> list) {
			this.original = list;
		}

		@SuppressLint("DefaultLocale")
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			Log.i("zlt", "constraint = " + constraint);
			FilterResults results = new FilterResults();
			if (constraint == null || constraint.length() == 0) {
				results.values = original;
				results.count = original.size();
			} else {
				List<String> tempList = new ArrayList<String>();
				for (String p : original) {
					if (p.toUpperCase().startsWith(
							constraint.toString().toUpperCase())) {
						tempList.add(p);
					}
				}
				results.values = tempList;
				results.count = tempList.size();
			}
			return results;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			mList = (List<String>) results.values;
			Log.i("zlt", "publishResults : mList.size = " + mList.size());
			notifyDataSetChanged();
		}

	}
}
