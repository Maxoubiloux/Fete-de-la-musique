package fr.esgi.fete_de_la_musique.maxence.util;

import fr.esgi.fete_de_la_musique.maxence.business.Groupe;
import java.util.Comparator;

public class GroupeComparator implements Comparator<Groupe> {
    @Override
    public int compare(Groupe g1, Groupe g2) {
        return g1.getNom().compareTo(g2.getNom());
    }
}
