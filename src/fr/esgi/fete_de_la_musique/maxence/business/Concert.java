package fr.esgi.fete_de_la_musique.maxence.business;

import java.time.LocalTime;

public class Concert {
    private Long id = 0L;
    private Lieu lieu;
    private Groupe groupe;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private static Long compteur = 0L;

    public Concert() {
        id = ++compteur;
    }

    public Concert(Lieu lieu, Groupe groupe, LocalTime heureDebut, LocalTime heureFin) {
        this();
        this.lieu = lieu;
        this.groupe = groupe;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    public Long getId() {
        return id;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }
}
