# Application de Gestion de Catalogue de Produits

Cette application Spring Boot permet de gérer un catalogue de produits. Elle offre des fonctionnalités CRUD (Créer, Lire, Mettre à jour, Supprimer) et prend en charge deux modes de stockage : une base de données MySQL et une HashMap.

## Prérequis
- **Java 21**
- **Maven**
- **MySQL** (si vous utilisez le profil db)

## Configuration

### Cloner le dépôt

```bash
git clone <URL_du_dépôt>
cd catalogue
```

### Configuration de la base de données (si profil db)
   Assurez-vous que MySQL est installé et en cours d'exécution.
   Créez une base de données nommée catalogue_db.
   Créez un utilisateur MySQL avec les privilèges nécessaires.
   Modifiez le fichier src/main/resources/application-db.properties avec vos informations de connexion à la base de données.
   Properties
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/catalogue_db
spring.datasource.username=catalogue_dev
spring.datasource.password=catalogue_dev_pwd
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### Choix du mode de stockage

L'application utilise des profils Spring pour gérer les modes de stockage. Vous pouvez choisir entre db (MySQL) et hashmap.

- **Profil db** (MySQL) : (Par défaut)
Assurez-vous que les propriétés de la base de données sont correctement configurées dans application-db.properties.
- **Profil hashmap** : Aucune configuration supplémentaire n'est requise.
Pour changer le profil actif, modifiez la propriété spring.profiles.active dans le fichier src/main/resources/application.properties ou passez l'argument -Dspring.profiles.active=hashmap lors de l'exécution de l'application.

```properties
spring.profiles.active=db # ou hashmap
```

### Construction de l'application

```bash
./mvnw clean install
```
Afin de s'assurer que tous les packages sont bien présents

### Exécution de l'application

- **Avec le profil ```db``` (MySQL)** : Ouvrir le fichier application.properties et Mettre le profile actif à **db** d'abord avant de lancer l'application

```properties
spring.profiles.active=db
```

- **Avec le profil hashmap** : Ouvrir le fichier application.properties et Mettre le profile actif à **hashmap** d'abord avant de lancer l'application

```properties
spring.profiles.active=hashmap
```

### Accès à l'application

L'application sera accessible à l'adresse http://localhost:8080/views/products.

## Composantes de l'application

### Contrôleurs

- ProductController : Gère les opérations CRUD sur les produits via des API REST.
- ViewController : Gère l'affichage des vues JSP.

### Services

- ProductMetier : Logique métier de l'application.
### DAO (Data Access Objects)

- ProductDAOD : Implémentation DAO pour la base de données MySQL.
- ProductDAOHashMap : Implémentation DAO pour la HashMap.

### Entités

- Product : Représente un produit.
### DTO (Data Transfer Objects)

- ProductDTO : Représente les données d'un produit pour les échanges avec le frontend.

### Mappers

- ProductMapper : Permet de convertir entre les entités et les DTO.
### Vues

- index.jsp : Interface utilisateur pour la gestion des produits.

## Fonctionnalités

- Liste des produits : Affiche tous les produits dans un tableau.
- Ajout de produit : Permet d'ajouter un nouveau produit.
  - Modification de produit : Permet de modifier un produit existant.
- Suppression de produit : Permet de supprimer un produit.
- Détails du produit : Affiche les détails d'un produit.
- Gestion des erreurs : Affiche les erreurs de validation côté client et serveur.

## Tests

Des tests unitaires et d'intégration peuvent être ajoutés pour assurer la qualité du code.
Déploiement
L'application peut être déployée sur un serveur d'applications Java (par exemple, Tomcat) ou exécutée directement en tant qu'application Spring Boot.
