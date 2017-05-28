package com.app.rachid.apppradeo;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button boutonJeux;
    public Button boutonAutre;
    public static BDDApp bddApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bddApp = new BDDApp(this);

        //On ouvre la base de données pour écrire dedans
        bddApp.open();

        //On rempli la BDD avec des applis de type Jeu et Autre
        for(int i=0;i<=20;i++)
        {
            App jeu = new App("JEU"+i, "Jeu",1.0);
            App appli = new App("Appli"+i, "Autre",1.0);

            bddApp.insertApp(jeu);
            bddApp.insertApp(appli);
        }

        //Message d'information BDD Chargée
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_main);
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "Base de données chargée", Snackbar.LENGTH_LONG);

        snackbar.show();

        //Gestion des boutons
        boutonJeux = (Button) findViewById(R.id.boutonJeux);
        boutonAutre = (Button) findViewById(R.id.boutonAutre);

        //Bouton Jeux lance l'activité ListeJeuxActivity
        boutonJeux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // Lien vers l'activité pour s'enregister
                Intent listeJeuxActivity = new Intent(MainActivity.this, ListeJeuxActivity.class);
                startActivity(listeJeuxActivity);
            }
        });


        //Bouton Autre lance l'activité ListeApplisActivity
        boutonAutre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // Lien vers l'activité pour s'enregister
                Intent listeApplisActivity = new Intent(MainActivity.this, ListeApplisActivity.class);
                startActivity(listeApplisActivity);
            }
        });




    }

}
