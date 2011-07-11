package com.xebia.faces.config;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SelectBucket extends ListActivity {

	List<String> names = new ArrayList<String>();
	List<String> ids = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Cursor cur = managedQuery(Media.EXTERNAL_CONTENT_URI, new String[] {
				Media._ID, Media.BUCKET_DISPLAY_NAME, Media.BUCKET_ID }, "",
				null, "");

		if (!cur.moveToFirst()) {
			setResult(RESULT_CANCELED);
			return;
		}

		int nameColumn = cur
				.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
		int idColumn = cur.getColumnIndex(MediaStore.Images.Media.BUCKET_ID);
		for (int i = 0; i < cur.getCount(); i++) {
			String name = cur.getString(nameColumn);
			if (!names.contains(name)) {
				names.add(name);
				ids.add(cur.getString(idColumn));
			}
			cur.moveToNext();
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				SelectBucket.this, android.R.layout.simple_list_item_1,
				android.R.id.text1, names);
		getListView().setAdapter(adapter);

	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		setResult(Integer.parseInt(ids.get(position)));
		finish();
		super.onListItemClick(l, v, position, id);
	}
}
