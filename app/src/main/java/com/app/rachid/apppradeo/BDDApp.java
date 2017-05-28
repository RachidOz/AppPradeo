package com.app.rachid.apppradeo;

/**
 * Created by Rachid on 27/05/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class BDDApp {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "app.db";

    private static final String TABLE_APPLIS = "TABLE_APPLIS";
    private static final String COL_VERSION = "VERSION";
    private static final int NUM_COL_VERSION = 0;
    private static final String COL_NOM = "Nom";
    private static final int NUM_COL_NOM = 1;
    private static final String COL_TYPE = "Type";
    private static final int NUM_COL_TYPE = 2;

    private SQLiteDatabase bdd;

    private MySQLiteHelper maBaseSQLite;

    public BDDApp(Context context){
        //On crée la BDD et sa table
        maBaseSQLite = new MySQLiteHelper(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }


    public void insertApp(App appli){
        if(!isAlreadyIn(appli)) {
            //Création d'un ContentValues (fonctionne comme une HashMap)
            ContentValues values = new ContentValues();
            //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
            values.put(COL_NOM, appli.getNom());
            values.put(COL_TYPE, appli.getType());
            values.put(COL_VERSION,appli.getVersion());
            //on insère l'objet dans la BDD via le ContentValues
            bdd.insert(TABLE_APPLIS, null, values);
        }

    }

    private boolean isAlreadyIn(App appli) {


        Cursor cursor = bdd.rawQuery("SELECT * FROM TABLE_APPLIS",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            if(appli.getNom().equals(cursor.getString(1))){
                Log.d("AlreadyIn","TRUEEEE");
                return true;
            }
            cursor.moveToNext();
        }
        cursor.close();
        return false;
    }




    public List<String> getApplis(String type){
        List<String> list = new ArrayList<>();
        Cursor cursor = bdd.rawQuery("SELECT * FROM TABLE_APPLIS",null);
        cursor.moveToFirst();
        Log.d("Test GetJeux", " Nom cursor : " + cursor.getString(2));
        while(!cursor.isAfterLast())
        {
            if(cursor.getString(2).equals(type)){
                String appli = "Vers. : " + cursor.getString(0)+" | Nom : "+cursor.getString(1)+" | Type : "+cursor.getString(2);
                list.add(appli);
            }

            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
