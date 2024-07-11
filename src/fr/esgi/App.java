package fr.esgi;

import fr.esgi.fete_de_la_musique.maxence.business.*;
import fr.esgi.fete_de_la_musique.maxence.util.GroupeComparator;

import java.util.*;
import java.io.*;
import java.time.LocalTime;
import java.net.*;

public class App {
    private List<Lieu> lieux;
    private List<Genre> genres;
    private List<Groupe> groupes;
    private List<Concert> concerts;

    public App() {
        this.lieux = new ArrayList<>();
        this.genres = new ArrayList<>();
        this.groupes = new ArrayList<>();
        this.concerts = new ArrayList<>();
    }

    public void run() {
        initializeData();
        importGroupesCSV("https://www.clelia.fr/groupes.csv");
        displayMenu();
    }

    private void initializeData() {
        lieux.add(new Lieu(1, "Péniche Le Sonic"));
        lieux.add(new Lieu(2, "Pentes de la Croix-Rousse"));

        genres.add(new Genre(1, "electro"));
        genres.add(new Genre(2, "pop"));
        genres.add(new Genre(3, "rock"));
        genres.add(new Genre(4, "heavy metal"));
        genres.add(new Genre(5, "rap"));
        genres.add(new Genre(6, "reggae"));
    }

    private void importGroupesCSV(String urlString) {
        try {
            URL url = URI.create(urlString).toURL();
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String[] data = parseCSVLine(inputLine);
                if (data.length < 2) {
                    System.err.println("Ligne incorrecte (pas assez de colonnes) : " + inputLine);
                    continue;
                }

                try {
                    String nom = data[0];
                    List<Genre> groupeGenres = new ArrayList<>();
                    for (int i = 1; i < data.length; i++) {
                        String genreName = data[i].trim();
                        Genre genre = findGenreByName(genreName);
                        if (genre != null) {
                            groupeGenres.add(genre);
                        } else {
                            System.err.println("Genre introuvable : " + genreName);
                        }
                    }
                    groupes.add(new Groupe(nom, groupeGenres));
                } catch (NumberFormatException e) {
                    System.err.println("Erreur de format de nombre pour la ligne : " + inputLine);
                    e.printStackTrace();
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] parseCSVLine(String line) {
        // Suppression des guillemets et découpage
        return line.replaceAll("^\"|\"$", "").split("\",\"");
    }

    private Genre findGenreByName(String name) {
        for (Genre genre : genres) {
            if (genre.getNom().equals(name)) {
                return genre;
            }
        }
        return null;
    }

    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Bienvenue sur Fête de la musique !");
            System.out.println("MENU PRINCIPAL");
            System.out.println("1 : ajouter un groupe");
            System.out.println("2 : voir les groupes triés alphabétiquement");
            System.out.println("3 : ajouter un concert");
            System.out.println("4 : voir tous les concerts");
            System.out.println("5 : quitter");
            System.out.print("Entrez votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1:
                    ajouterGroupe(scanner);
                    break;
                case 2:
                    voirGroupesTries();
                    break;
                case 3:
                    ajouterConcert(scanner);
                    break;
                case 4:
                    voirConcerts();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private void ajouterGroupe(Scanner scanner) {
        System.out.println("Ajout d’un groupe");
        System.out.print("Entrez le nom de l’adhérent : ");
        String nom = scanner.nextLine();
        System.out.print("Entrez le(s) genre(s) du groupe (séparés par des virgules) : ");
        String[] genreNoms = scanner.nextLine().split(",");
        List<Genre> groupeGenres = new ArrayList<>();
        for (String genreName : genreNoms) {
            groupeGenres.add(findGenreByName(genreName.trim()));
        }
        Groupe groupe = new Groupe(nom, groupeGenres);
        groupes.add(groupe);
        System.out.println("Le groupe a bien été ajouté, il porte l’id " + groupe.getId());
    }

    private void voirGroupesTries() {
        groupes.sort(new GroupeComparator());
        for (Groupe groupe : groupes) {
            System.out.println(groupe.getNom());
        }
    }

    private void ajouterConcert(Scanner scanner) {
        System.out.println("Ajout d’un concert");
        System.out.println("Choisissez le lieu du concert :");
        for (int i = 0; i < lieux.size(); i++) {
            System.out.println((i + 1) + ". " + lieux.get(i).getNom());
        }
        System.out.print("Entrez votre choix : ");
        int lieuChoix = scanner.nextInt();
        scanner.nextLine();
        Lieu lieu = lieux.get(lieuChoix - 1);

        System.out.println("Choisissez le groupe :");
        for (int i = 0; i < groupes.size(); i++) {
            System.out.println((i + 1) + ". " + groupes.get(i).getNom());
        }
        System.out.print("Entrez votre choix : ");
        int groupeChoix = scanner.nextInt();
        scanner.nextLine();
        Groupe groupe = groupes.get(groupeChoix - 1);

        System.out.print("Entrez l’heure de début (HH:mm) : ");
        String heureDebutStr = scanner.nextLine();
        LocalTime heureDebut = LocalTime.parse(heureDebutStr);

        System.out.print("Entrez l’heure de fin (HH:mm) : ");
        String heureFinStr = scanner.nextLine();
        LocalTime heureFin = LocalTime.parse(heureFinStr);

        Concert concert = new Concert(lieu, groupe, heureDebut, heureFin);
        concerts.add(concert);
        System.out.println("Le concert a bien été ajouté, il porte l'id " + concert.getId() + ". " + groupe.getNom() +
                " va jouer à " + lieu.getNom() + " de " + heureDebut + " à " + heureFin);
    }

    private void voirConcerts() {
        for (Concert concert : concerts) {
            System.out.println(concert.getGroupe().getNom() + " va jouer à " + concert.getLieu().getNom() +
                    " de " + concert.getHeureDebut() + " à " + concert.getHeureFin());
        }
    }

    public static void main(String[] args) {
        new App().run();
    }
}
