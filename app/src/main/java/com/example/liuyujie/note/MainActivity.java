package com.example.liuyujie.note;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static com.example.liuyujie.note.R.layout.activity_main;


public class MainActivity extends Activity
{
    MyDatabaseHelper dbHelper;
    Button search = null;
    Button xinjian= null;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        search = (Button) findViewById(R.id.search);
        xinjian = (Button) findViewById(R.id.xinjian);
        xinjian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,newactivity.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View source)
            {
                String key = ((EditText) findViewById(R.id.key)).getText().toString();
                Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
                        "select * from dict where title like ? or detail like ?",
                        new String[] { "%" + key + "%", "%" + key + "%" });
                Bundle data = new Bundle();
                data.putSerializable("data", converCursorToList(cursor));
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtras(data);
                startActivity(intent);
            }
        });
    }
    protected ArrayList<Map<String, String>>
    converCursorToList(Cursor cursor)
    {
        ArrayList<Map<String, String>> result = new ArrayList<>();
        while (cursor.moveToNext())
        {
            Map<String, String> map = new HashMap<>();
            map.put("title", cursor.getString(1));
            map.put("detail", cursor.getString(2));
            result.add(map);
        }
        return result;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if (dbHelper != null)
        {
            dbHelper.close();
        }
    }
}

