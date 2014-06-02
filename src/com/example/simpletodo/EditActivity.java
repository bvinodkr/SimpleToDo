package com.example.simpletodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

	

public class EditActivity extends Activity {

	EditText editText;
	int editPosition = -1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		String itemText = getIntent().getStringExtra("itemText");
		editPosition = getIntent ().getIntExtra("pos", -1);
		editText = (EditText)findViewById (R.id.editText);
		editText.setText(itemText);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}

	public void saveTodoItem(View v) {
		EditText etNewItem = (EditText) findViewById (R.id.editText);
		String newText = etNewItem.getText ().toString();
		
		Intent data = new Intent ();
		data.putExtra("editItem", newText);
		data.putExtra("pos", editPosition);
		setResult (RESULT_OK, data);
		finish ();
	}

}
