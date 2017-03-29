# chess

## Setup

Make sure you have Node.js and NPM installed. Then follows these

```
mkdir chess && cd chess 
PROJ_DIR=`pwd`  # this is not necessary of course, just for clarity in what follows

git clone <repo> .

./gradlew run  # run engine / server; from $PROJ_DIR; will install Gradle if you don't have it yet

cd ui
npm install  # from $PROJ_DIR/ui; run once to install Node deps
npm start    # start the front end; run every time you start the front end
```

## Development

IntelliJ IDEA is recommended. When you import the project to IDEA, make sure to point to the `build.gradle` file 
to import as a Gradle project.

Good luck!
