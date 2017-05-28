package com.app.rachid.apppradeo;

/**
 * Created by Rachid on 27/05/2017.
 */

public class App {

    private double version;
    private String nom;
    private String type;

    public App(){}

    public App(String nom, String type, Double version){
        this.nom = nom;
        this.type = type;
        this.version = version;
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString(){
        return "Version : "+version+"\nnom : "+nom+"\ntype : "+type;
    }

}
