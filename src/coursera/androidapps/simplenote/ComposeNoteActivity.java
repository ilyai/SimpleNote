package coursera.androidapps.simplenote;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class ComposeNoteActivity extends Activity {

	public static final String NOTE = "note";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose_note);
		
		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose_note, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		SharedPreferences sharedPrefs = getSharedPreferences(
				getString(R.string.preference_file_key), MODE_PRIVATE);
		String note = sharedPrefs.getString(ComposeNoteActivity.NOTE, "");
		EditText editorView = (EditText) findViewById(R.id.note_editor);
		editorView.setText(note);
	}
	
	/**
	 * Save click event handler
	 */
	public void saveNote(View view) {
		EditText editorView = (EditText) findViewById(R.id.note_editor);
		String note = editorView.getText().toString();
		SharedPreferences sharedPrefs = getSharedPreferences(
				getString(R.string.preference_file_key), MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPrefs.edit();
		editor.putString(NOTE, note);
		editor.commit();
		finish();
	}
	
	/**
	 * Share click event handler
	 */
	public void shareNote(View view) {
		EditText editorView = (EditText) findViewById(R.id.note_editor);
		String note = editorView.getText().toString();
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SEND);
		intent.putExtra(Intent.EXTRA_TEXT, note);
		intent.setType("text/plain");
		startActivity(intent);
	}

}
