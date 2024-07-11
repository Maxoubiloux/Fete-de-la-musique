package fr.esgi.fete_de_la_musique.maxence.business;

import java.util.List;

public class Groupe {

    private Long id = 0L;
    private String nom;
    private List<Genre> genres;
    private static Long compteur = 0L;

    public Groupe() {
        id = ++compteur;
    }

    public Groupe(String nom, List<Genre> genres) {
        this();
        this.nom = nom;
        this.genres = genres;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
