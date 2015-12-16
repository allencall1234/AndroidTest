package com.zlt.test.xmlparse;

import java.io.IOException;
import java.util.List;

import com.zlt.test.R;

import com.zlt.test.adapter.CommonAdapter;
import com.zlt.test.adapter.ViewHolder;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class XmlParseAcitivity extends ListActivity {

	private List<Student> students;

	private View headView;
	private RadioGroup radioGroup = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		headView = LayoutInflater.from(this).inflate(
				R.layout.xml_parse_headerview, null);
		getListView().addHeaderView(headView);

		radioGroup = (RadioGroup) headView.findViewById(R.id.radio_group);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.by_sax:
					try {
						students = XmlParseSax.parseXml(getAssets().open(
								"student.xml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case R.id.by_pull:
					try {
						students = XmlParsePull.parseXml(getAssets().open(
								"student1.xml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case R.id.by_dom:
					try {
						students = XmlParseDom.parseXml(getAssets().open(
								"student2.xml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				default:
					break;
				}

				notifyDataChanged();
			}
		});
		radioGroup.check(R.id.by_sax);
	}

	private void notifyDataChanged() {
		setListAdapter(new CommonAdapter<Student>(this, students,
				R.layout.item_student) {

			@Override
			public void convert(ViewHolder viewHolder, Student item) {
				// TODO Auto-generated method stub
				viewHolder.setText(R.id.student_grade, item.getGrade());
				viewHolder.setText(R.id.student_class, item.getClazz());
				viewHolder.setText(R.id.student_name, item.getName());
				viewHolder.setText(R.id.student_sex, item.getSex());
			}
		});
	}
}
