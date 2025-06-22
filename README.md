# JBrasserie

Projet de fin d'étude du DEUST IOSI du CNAM - Année 2025 

**UE NFA019 - Projet systèmes d'informations : mise en pratique avec Java**

## Présentation

**JBrasserie** est une application de gestion de magasin de bières, réalisée dans le cadre du projet de fin d'étude du DEUST IOSI (UE NFA019) au CNAM pour l'année 2025.

L’application propose :
- Une interface client pour consulter le stock de bières et passer commande
- Une interface magasin pour gérer le stock, le fichier client, et consulter les commandes

## Fonctionnalités principales

- **Interface Client** : 
  - Edition du profil
  - Affichage du stock de bières
  - Passage de commandes

- **Interface Magasin** :
  - Gestion du stock
  - Gestion des clients
  - Consultation des commandes passées

## Organisation du projet

À la racine du dépôt, on trouve notamment :
- `src/` : Code source principal de l’application (Java)
- `beers.sql` : Script SQL pour la base de données des bières
- `pom.xml` : Fichier de configuration Maven
- `doc/` : Documentation technique et utilisateur

## Installation & Lancement

1. Clonez le dépôt :
   ```bash
   git clone https://github.com/slan012/JBrasserie.git
   ```
2. Importez le projet dans votre IDE Java favori (Eclipse, IntelliJ…)
3. Importez la base de données à partir de `beers.sql` (export de la base de données)
3. Renseignez les informations de la base de données dans `config.properties` dans le répertoire `config/`
4. Compilez et exécutez le projet via Maven ou directement depuis l’IDE

## Auteur

Projet réalisé par Etienne LARROUMETS dans le cadre de l'UE NFA019 du DEUST IOSI - CNAM 2025.
