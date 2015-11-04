package com.example.liuyujie.note;


import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class newactivity extends Activity {
    Button insert=null;
    MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newone);
        dbHelper = new MyDatabaseHelper(this, "Note.db3", 1);
        insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = ((EditText) findViewById(R.id.title)).getText().toString();
                String detail = ((EditText) findViewById(R.id.detail)).getText().toString();
                insertData(dbHelper.getReadableDatabase(), title, detail);
                Toast.makeText(newactivity.this, "保存成功！", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void insertData(SQLiteDatabase db, String title, String detail)
    {
        db.execSQL("insert into dict values(null , ? , ?)", new String[]{title, detail});
    }
}
