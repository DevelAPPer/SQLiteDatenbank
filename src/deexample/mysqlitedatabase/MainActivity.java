package deexample.mysqlitedatabase;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	SQLiteDatabase BeispielDatenbank;
	TextView textview,tv2;
	Button druck;
	EditText eingabe,ed1;
	String texteingabe,letztereintrag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		BeispielDatenbank = openOrCreateDatabase("Meine Datenbank", MODE_PRIVATE,null);
		BeispielDatenbank.execSQL("CREATE TABLE IF NOT EXISTS Text (texteingabedb VARCHAR);");
		
		druck = (Button)findViewById(R.id.eintraghinzufuegen);
		eingabe = (EditText)findViewById(R.id.editText1);
		eingabe.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				druck.setEnabled(s.length() > 0);
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}
	});
		druck.setEnabled(false);
}
	// Eintrag wird der Datenbank hinzugefügt execSQL: Statement ohne Rückgabewert
	public void eintraghinzufuegen (View view) {
		ed1 = (EditText)findViewById(R.id.editText1);
		Toast.makeText(getApplicationContext(),"Eintrag wurde der Datenbank hinzugefügt",Toast.LENGTH_LONG).show();
		texteingabe = ed1.getText().toString();
		BeispielDatenbank.execSQL("INSERT INTO Text VALUES('"+texteingabe+"');");
		
	}
	public void zeigverlauf (View view) {
		// rawQuery = Datenbankabrag; Cursor als Zeiger auf die Datenbankeinträge
		Cursor cursor = BeispielDatenbank.rawQuery("SELECT * from Text", null);
		cursor.moveToLast();
		letztereintrag = cursor.getString(cursor.getColumnIndex("texteingabedb"));
		setContentView(R.layout.zweiteslayout);
		tv2 = (TextView)findViewById(R.id.textView1);
		tv2.setText(letztereintrag);
		
		BeispielDatenbank.close();
		
		
	}
	public void schließen (View view) {
		System.exit(0);
	}
		
	}
