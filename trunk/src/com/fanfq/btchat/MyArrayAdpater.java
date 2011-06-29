package com.fanfq.btchat;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyArrayAdpater extends BaseAdapter {
	
	private List<HashMap<String,String>> list;
	private Context context;
	private int[] type;
	
	public MyArrayAdpater(Context context,List<HashMap<String,String>> list,  int[] type){
		this.context = context;
		this.list = list;
		this.type = type;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater mInflater = LayoutInflater.from(context);
		View view = null;
		//根据type不同的数据类型构造不同的View,也可以根据1,2,3天数构造不同的样式  
		if(type[position] == 0){
			view = mInflater.inflate(R.layout.message, null);
			TextView mTextView = (TextView)view.findViewById(R.id.msg);
			mTextView.setText(list.get(position).get("data"));
		}else{
			view = mInflater.inflate(R.layout.sent_message, null);
			TextView mTextView = (TextView)view.findViewById(R.id.msg);
			mTextView.setText(list.get(position).get("data"));
		}
		
		return view;
	}
	
}