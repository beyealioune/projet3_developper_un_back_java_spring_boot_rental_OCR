# Backend

Le backend de l'application est développé avec Spring Boot. Il fournit les fonctionnalités de gestion des utilisateurs, des locations, des messages, et des images.

# Pour cloner ce projet :

Effectuer cette commande dans le terminal d'un éditeur de code :

'git clone https://github.com/beyealioune/projet3_developper_un_back_java_spring_boot_rental_OCR.git'

Ouvrer ensuite le dossier back dans IntelliJ

# Prérequis
Java 17 JDK 
Utilisation de Maven
Base de données ( MySQL)

Installer Mysql , ( phpMyAdmin ) et vérifier les informations d'identification dans le fichier properties . 

Pour installer MySQL et phpMyAdmin et vérifier les informations d'identification dans un fichier de propriétés, vous pouvez suivre les étapes suivantes :

# Installation de MySQL :
Téléchargement : Rendez-vous sur le site officiel de MySQL (https://dev.mysql.com/downloads/) et téléchargez la version compatible avec votre système d'exploitation.

Installation : Suivez les instructions d'installation fournies par le programme d'installation. Assurez-vous de noter le nom d'utilisateur et le mot de passe sont les mêmes .

# Installation de phpMyAdmin :
Téléchargement : Allez sur le site officiel de phpMyAdmin (https://www.phpmyadmin.net/downloads/) et téléchargez la dernière version.

# Configuration 

Assurez-vous que la configuration de la base de données est correctement définie dans le fichier application.properties ( mot de passe et nom d'utilisateur pour la connexion à votre base de donnée ) . 

# Exécution

Ouvrez le terminal et accédez au répertoire du projet backend.

Exécutez la commande suivante pour démarrer le serveur :

'mvn spring-boot:run'

 # Frontend
 
Le frontend de l'application est développé avec Angular. 

# Pour cloner ce projet :

Effectuer cette commande dans le terminal d'un éditeur de code :

'git clone https://github.com/beyealioune/projet3_developper_un_back_java_spring_boot_rental_OCR.git'

Ouvrir le côté front dans VScode

# Installation des Dépendances :

Avant de lancer l'application, vous devez installer les dépendances nécessaires. Naviguez vers le répertoire de votre projet en utilisant la commande :

'cd chemin/de/votre-projet'

Puis une fois dans le répertoire de votre projet exécutez la commande suivante :

'npm install'

# Lancement de l'Application

Une fois les dépendances installées, vous pouvez lancer l'application en utilisant la commande suivante :

ng serve L'application sera disponible à l'adresse "http://localhost:4200/". Ouvrez votre navigateur web et rendez-vous sur cette adresse pour voir le rendu de l'application.
