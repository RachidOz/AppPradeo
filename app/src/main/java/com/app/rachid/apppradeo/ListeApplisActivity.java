package com.app.rachid.apppradeo;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import static com.app.rachid.apppradeo.MainActivity.bddApp;

public class ListeApplisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listeapplis);
        setTitle("Liste d'applis");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarApplis);
        setSupportActionBar(toolbar);

        //On ouvre la BDD pour récuperer les applis
        bddApp.open();

        //Stockage des applis dans une liste
        ListView listViewApplis = (ListView) findViewById(R.id.listViewApplis);
        List<String> autres = bddApp.getApplis("Autre");
        bddApp.close();

        //Mise en forme de la liste au travers d'un adaptateur
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, autres);
        listViewApplis.setAdapter(adapter);

        //Message d'information description de la liste
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_applis);
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "Liste d'applications présentes dans la BDD", Snackbar.LENGTH_LONG);
        snackbar.show();

        //Gestion apppui sur Bouton flottant qui permet de retourner au menu
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Gestion de l'affichage du bouton flottant en fonction du défilement, disparition sur défilement vers le bas/apparition sur défilement vers le haut
        listViewApplis.setOnScrollListener(new AbsListView.OnScrollListener(){
            private int mLastFirstVisibleItem;
            int fab_initPosY=fab.getScrollY();

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_FLING) {
                    fab.animate().cancel();
                    fab.animate().translationYBy(300);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if(mLastFirstVisibleItem>firstVisibleItem)
                {
                    fab.animate().cancel();
                    fab.animate().translationY(fab_initPosY);
                }
                mLastFirstVisibleItem=firstVisibleItem;
            }
        });


    }
}
