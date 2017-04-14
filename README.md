# chess

## UPDATE: running front end with Docker

1. Install Docker Engine and Docker Compose (included in Docker Toolbox on Windows and Mac)
2. Run Docker
3. Do `docker-compose up`. Your UI should install and run on port 3000. The server will run on port 9090.

To reload changes in the server

```
./gradlew distDocker && docker-compose up -d --build server
```

To remote debug the Java server running in a Docker container, in IDEA go to
`Run configuration > Add + > Remote`. Select port 8000. Now you can set breakpoints
and start debugging remotely.

## Setup

Make sure you have Node.js and NPM installed. Then follows these

```sh
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

If developing on Windows 10, it is worth trying out the [Bash shell for Windows 10](https://www.howtogeek.com/249966/how-to-install-and-use-the-linux-bash-shell-on-windows-10/), maybe even with the [ComEmu emulator](https://conemu.github.io/)

Bash for Windows comes with a number of nice Unix utils:

```sh
# Unix filesystem commands
ls  # not dir!

# install through apt-get
sudo apt-get install npm

# seamless git integration
git clone .

# built-in ssh, which will come in handy if you ever remote log in to the cloud or virtual machines
ssh marcin-komputer  # we've got your private key hehehe
```

Good luck!
