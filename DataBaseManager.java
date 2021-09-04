package com.example.vacina;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class DataBaseManager extends SQLiteOpenHelper {
    public DataBaseManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PACIENTE");

        sqLiteDatabase.execSQL("CREATE TABLE PACIENTE(\n" +
                "\tID INT NOT NULL,\n" +
                "\tVACINA VARCHAR(50) NOT NULL,\n" +
                "\tDATA_VAC VARCHAR(8),\n" +
                "\tUNIDADE VARCHAR(50) NOT NULL,\n" +
                "\tDOSE VARCHAR(50) NOT NULL,\n" +
                "\tPRIMARY KEY (ID)\n"+
                ");");

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PACIENTE");
    }

    //INSERT
    public void inserirPaciente(Integer id, String vacina, String data_vac, String unidade, String dose){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("ID",id);
        cv.put("VACINA",vacina);
        cv.put("DATA_VAC",data_vac);
        cv.put("UNIDADE",unidade);
        cv.put("DOSE",dose);
        db.insert("PACIENTE","ID",cv);

    }
    //SELECT
    Cursor listaPacientes(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cur = db.rawQuery("SELECT ID,VACINA,DATA_VAC,UNIDADE,DOSE FROM PACIENTE ORDER BY ID",null);
        return cur;
    }

    //UPDATE
    public void atualizarPaciente(Integer id, String vacina, String data_vac, String unidade, String dose){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("ID",id);
        cv.put("VACINA",vacina);
        cv.put("DATA_VAC",data_vac);
        cv.put("UNIDADE",unidade);
        cv.put("DOSE",dose);
        db.update("PACIENTE",cv, "ID", new String[]{id.toString()});

    }

    //DELETE
    public void deletarPaciente(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("PACIENTE","ID=?",new String[]{id});
    }
}
