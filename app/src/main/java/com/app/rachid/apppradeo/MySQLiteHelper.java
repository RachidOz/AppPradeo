package com.app.rachid.apppradeo;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * Created by Rachid on 27/05/2017.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String TABLE_APPLIS = "TABLE_APPLIS";
    private static final String COL_VERSION = "VERSION";
    private static final String COL_NOM = "NOM";
    private static final String COL_TYPE = "Type";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_APPLIS + " ("
            + COL_VERSION + " TEXT NOT NULL, " + COL_NOM + " TEXT NOT NULL, "
            + COL_TYPE + " TEXT NOT NULL);";

    public MySQLiteHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on crée la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut faire ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        db.execSQL("DROP TABLE " + TABLE_APPLIS + ";");
        onCreate(db);
    }

}


