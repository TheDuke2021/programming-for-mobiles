package com.example.lab16;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonDel, buttonClear, buttonShow, buttonAdd;
    EditText ET_name, ET_phone, ET_address;
    TextView text;
    DatabaseHelper DatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(this);
        buttonShow = (Button) findViewById(R.id.buttonShow);
        buttonShow.setOnClickListener(this);
        buttonDel = (Button) findViewById(R.id.buttonDel);
        buttonDel.setOnClickListener(this);
        buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(this);
        ET_name = (EditText) findViewById(R.id.ET_name);
        ET_phone = (EditText) findViewById(R.id.ET_phone);
        ET_address = (EditText) findViewById(R.id.ET_address);
        text = (TextView) findViewById(R.id.text);
        DatabaseHelper = new DatabaseHelper(this);
    }

    @Override
    public void onClick(View v) {
        String input_name = ET_name.getText().toString();
        String input_phone = ET_phone.getText().toString();
        String input_address = ET_address.getText().toString();
        SQLiteDatabase db = DatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        switch (v.getId()) {
            case R.id.buttonShow:
                text.setText("");
                String[] projection = {
                        DBContract.DBEntry.COLUMN_NAME_NAME,
                        DBContract.DBEntry.COLUMN_NAME_PHONE,
                        DBContract.DBEntry.COLUMN_NAME_ADDRESS
                };
                Cursor cursor = db.query(
                        DBContract.DBEntry.TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        null
                );
                int index_name =
                        cursor.getColumnIndex(DBContract.DBEntry.COLUMN_NAME_NAME);
                int index_phone =
                        cursor.getColumnIndex(DBContract.DBEntry.COLUMN_NAME_PHONE);
                int index_address = cursor.getColumnIndex(DBContract.DBEntry.COLUMN_NAME_ADDRESS);
                while (cursor.moveToNext()) {
                    String value_name = cursor.getString(index_name);
                    String value_phone = cursor.getString(index_phone);
                    String value_address = cursor.getString(index_address);
                    text.append("\n" + value_name + " \n " + value_phone + " \n" + value_address + "\n");
                }
                cursor.close();
                break;
            case R.id.buttonAdd:
                values.put(DBContract.DBEntry.COLUMN_NAME_NAME, input_name);
                values.put(DBContract.DBEntry.COLUMN_NAME_PHONE, input_phone);
                values.put(DBContract.DBEntry.COLUMN_NAME_ADDRESS, input_address);
                db.insert(DBContract.DBEntry.TABLE_NAME, null, values);
                buttonShow.callOnClick();
                break;
            case R.id.buttonDel:
                String selection = DBContract.DBEntry.COLUMN_NAME_NAME + "=?";
                String[] selectionArgs = {input_name};
                db.delete(DBContract.DBEntry.TABLE_NAME, selection,
                        selectionArgs);
                buttonShow.callOnClick();
                break;
            case R.id.buttonClear:
                db.delete(DBContract.DBEntry.TABLE_NAME, null, null);
                buttonShow.callOnClick();
                break;
        }
        DatabaseHelper.close();
    }
}