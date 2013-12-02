package com.cs365.uclick;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandibleListAdapter extends BaseExpandableListAdapter {
	private Activity context;
	private Map<String, List<String>> quizCollections;
	private List<String> details;

	public ExpandibleListAdapter(Activity context, List<String> details,
			Map<String, List<String>> quizCollections) {
		this.context = context;
		this.quizCollections = quizCollections;
		this.details = details;
	}

	public Object getChild(int groupPosition, int childPosition) {
		return quizCollections.get(details.get(groupPosition)).get(
				childPosition);
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final String detail = (String) getChild(groupPosition, childPosition);
		LayoutInflater inflater = context.getLayoutInflater();

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.sub_item, null);
		}

		TextView item = (TextView) convertView.findViewById(R.id.q_date);

		item.setText(detail);
		return convertView;
	}

	public int getChildrenCount(int groupPosition) {
		return quizCollections.get(details.get(groupPosition)).size();
	}

	public Object getGroup(int groupPosition) {
		return details.get(groupPosition);
	}

	public int getGroupCount() {
		return details.size();
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String quizid = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.group_item, null);
		}
		TextView item = (TextView) convertView.findViewById(R.id.qnumber);
		item.setText(quizid);
		return convertView;
	}

	public boolean hasStableIds() {
		return true;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
