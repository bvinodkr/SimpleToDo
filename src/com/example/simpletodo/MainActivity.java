package com.example.simpletodo;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;	
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


public class MainActivity extends 	Activity {

	ArrayList<String> items;
	ArrayAdapter<String> itemsAdapter;	
	ListView IvItems;	
	private final int REQUEST_CODE = 20;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		IvItems = (ListView)findViewById(R.id.IvItems);
		items = new ArrayList<String> ();
		itemsAdapter = new ArrayAdapter<String> (this,
				android.R.layout.simple_list_item_1, items);
		IvItems.setAdapter(itemsAdapter);
		items.add("First Item");
		items.add("Second");
		setupListViewListener ();
	}

	private void setupListViewListener() {
		IvItems.setOnItemLongClickListener(new OnItemLongClickListener () {
			@Override
			public boolean onItemLongClick (AdapterView<?> parent, View view, int position, long rowId) {
				items.remove(position);
				itemsAdapter.notifyDataSetChanged();
				return true;
			}
		}
				);
		IvItems.setOnItemClickListener(new OnItemClickListener () {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent (MainActivity.this, EditActivity.class);
				String itemText = items.get(position);
				i.putExtra("itemText", itemText);
				i.putExtra ("pos", position);
				startActivityForResult(i, REQUEST_CODE);
				
			}}
		);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void addTodoItem(View v) {
		EditText etNewItem = (EditText) findViewById (R.id.etNewItem);	
		itemsAdapter.add (etNewItem.getText().toString ());
		etNewItem.setText ("");
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
			String name = data.getExtras ().getString("editItem");
			int editPos = data.getExtras ().getInt("pos");
			if (editPos != -1)
			{
				items.set(editPos, name);
				itemsAdapter.notifyDataSetChanged();				
			}
		}
	}

}
