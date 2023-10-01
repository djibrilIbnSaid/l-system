Le projet: Générateurs de flores vidéos-ludiques (lsysteme)

## Mode d'emploie'

Les librairies fournissent du rendu 3D et la gestion des testes.

Les sources java de la librairie sont toutes dans le répertoire src/main/java, et les sources des tests
dans le répertoire src/test/java.

Les commandes principales sont : palcez vous sur la racine du projet.

- "sh scripts/run.sh" pour lancer le programme
- "sh scripts/test.sh" pour lancer les tests du programme
- "sh scripts/makejar.sh" pour créer un jar contenant tous les .class
- "sh scripts/makedoc.sh" pour générer la javadoc (le fichier principal est alors doc/index.html)
- "sh scripts/clean.sh" pour supprimer tous les fichiers générés (sauf les librairies)
- "sh scripts/compile.sh" pour compiler le programme
- "sh scripts/install.sh" pour creer les dossiers bin, doc et dist
- "sh scripts/runjar.sh" pour créer un jar contenant tous les .class et lancer le jar

Si vous utilisez ant:

- "ant run" pour lancer le programme
- "ant test" pour lancer les tests du programme
- "ant packaging" pour créer un jar contenant tous les .class
- "ant javadoc" pour générer la javadoc (le fichier principal est alors doc/index.html)
- "ant clean" pour supprimer tous les fichiers générés (sauf les librairies)
- "ant compile" pour compiler le programme
- "ant init" pour creer les dossiers bin, doc et dist


Les librairies externes utilisées sont:
```bash
-jogamp-fat.jar
-junit-4.12.jar
-hamcrest-core-1.3.jar
```
## Membre de l'equipe:
- Abdoulaye Djibril DIALLO
- Mamady             DJIGUINE
- Mamadou Alpha     DIALLO
- Elhadj Alseiny     DIALLO
