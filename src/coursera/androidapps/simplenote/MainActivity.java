package coursera.androidapps.simplenote;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		// Fetch and display the note if any
		SharedPreferences sharedPrefs = getSharedPreferences(
				getString(R.string.preference_file_key), MODE_PRIVATE);
		String note = sharedPrefs.getString(ComposeNoteActivity.NOTE,
				getString(R.string.default_note));
		TextView noteView = (TextView) findViewById(R.id.note_text);
		noteView.setText(note.equals("") ? getString(R.string.default_note) : note);
	}
	
	/**
	 * Handles click event on note
	 */
	public void editNote(View view) {
		// Invoke the note composer
		Intent intent = new Intent(this, ComposeNoteActivity.class);
		startActivity(intent);
	}

}
