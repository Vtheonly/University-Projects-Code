-- Table: Utilisateur
CREATE TABLE Utilisateur (
                             id_utilisateur INT AUTO_INCREMENT PRIMARY KEY,
                             nom VARCHAR(255) NOT NULL,
                             prenom VARCHAR(255) NOT NULL,
                             email VARCHAR(255) UNIQUE NOT NULL,
                             mot_de_passe VARCHAR(255) NOT NULL,
                             role VARCHAR(50) NOT NULL
);

-- Table: Vehicule
CREATE TABLE Vehicule (
                          id_vehicule INT AUTO_INCREMENT PRIMARY KEY,

                          marque VARCHAR(255) NOT NULL,
                                modele VARCHAR(255) NOT NULL,
                          annee INT NOT NULL,
                          type VARCHAR(50) NOT NULL,
                          carburant VARCHAR(50) NOT NULL,
                          prix_location_jour DECIMAL(10, 2) NOT NULL,
                          etat VARCHAR(50) NOT NULL
);

-- Table: Client
CREATE TABLE Client (
                        id_client INT AUTO_INCREMENT PRIMARY KEY,
                        nom VARCHAR(255) NOT NULL,
                        prenom VARCHAR(255) NOT NULL,
                        telephone VARCHAR(20) NOT NULL,
                        email VARCHAR(255) UNIQUE NOT NULL,
                        numero_permis VARCHAR(50) NOT NULL
);

-- Table: Reservation
CREATE TABLE Reservation (
                             id_reservation INT AUTO_INCREMENT PRIMARY KEY,
                             id_vehicule INT NOT NULL,
                             id_client INT NOT NULL,
                             date_debut DATE NOT NULL,
                             date_fin DATE NOT NULL,
                             montant_total DECIMAL(10, 2) NOT NULL,
                             statut VARCHAR(50) NOT NULL,
                             FOREIGN KEY (id_vehicule) REFERENCES Vehicule(id_vehicule),
                             FOREIGN KEY (id_client) REFERENCES Client(id_client)
);

-- Table: Retour
CREATE TABLE Retour (
                        id_retour INT AUTO_INCREMENT PRIMARY KEY,
                        id_reservation INT NOT NULL,
                        date_retour DATE NOT NULL,
                        etat_retour VARCHAR(255) NOT NULL,
                        frais_supplementaires DECIMAL(10, 2),
                        FOREIGN KEY (id_reservation) REFERENCES Reservation(id_reservation)
);

-- Table: Paiement (optional)
CREATE TABLE Paiement (
                          id_paiement INT AUTO_INCREMENT PRIMARY KEY,
                          id_reservation INT NOT NULL,
                          montant DECIMAL(10, 2) NOT NULL,
                          date_paiement DATE NOT NULL,
                          mode_paiement VARCHAR(50) NOT NULL,
                          FOREIGN KEY (id_reservation) REFERENCES Reservation(id_reservation)
);
