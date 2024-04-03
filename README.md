# ArchitectureAcrobat

This is a simple platformer game where you control a character that can jump and move left and right. The goal is to
avoid obstacles and reach the end of the level. The game is made with the libGDX framework.

## Getting Started

### Local setup

1. Clone the repository.
2. Open the project in your favorite IDE.
3. Execute the `lwjgl3:run` Gradle task to start the game.

### How to play

- Use the arrow keys, or WASD, to move the character.
- You can also use spacebar to jump

## Resources

- [Animated Pixel Adventurer](https://rvros.itch.io/animated-pixel-hero) by rvros
- [Modern City Backgrounds Pixel Art](https://free-game-assets.itch.io/free-city-backgrounds-pixel-art) by Free Game
  Assets
- [Industrial Zone Tileset](https://free-game-assets.itch.io/free-industrial-zone-tileset-pixel-art) by Free Game Assets
- [Rocky Roads Asset Pack](https://essssam.itch.io/rocky-roads) by Essssam
- Flight Risk (Soundtrack) by [Joel Steudler Music](https://www.joelsteudlermusic.com/)
- [Success Sound](https://pixabay.com/sound-effects/success-02-68338/) by Pixabay
- [Concrete Footsteps](https://pixabay.com/sound-effects/concrete-footsteps-6752/) by Pixabay
- [City Ambience](https://pixabay.com/sound-effects/sounds-of-the-gloomy-city-of-the-future-126442/) by stereocode on
  Pixabay
- [Button Click](https://pixabay.com/sound-effects/click-button-140881/) by UNIVERSFIELD on Pixabay

## Development

The project was generated with [gdx-liftoff](https://github.com/libgdx/gdx-liftoff), using a template including simple
application launchers and an `ApplicationAdapter` extension that draws libGDX logo.

### Requirements

- Java 7 or higher

### Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3.
- `html`: Web platform using GWT and WebGL. Supports only Java projects.

### Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `html:dist`: compiles GWT sources. The compiled application can be found at `html/build/dist`: you can use any HTTP
  server to deploy it.
- `html:superDev`: compiles GWT sources and runs the application in SuperDev mode. It will be available
  at [localhost:8080/html](http://localhost:8080/html). Use only during development.
- `idea`: generates IntelliJ project data.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/lib`.
- `lwjgl3:run`: starts the application.
- `test`: runs unit tests (if any).

Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should
be replaced with the ID of a specific project.
For example, `core:clean` removes `build` folder only from the `core` project.

### Deployment

- `lwjgl3:jar` task builds a runnable jar for desktop platforms. You can find it in the `lwjgl3/build/libs` directory.
    - The build-distributable.bat script builds a .exe file for Windows from the runnable jar, which can then be found
      in the `lwjgl3/build/libs/Architecture Acrobat` directory.
- `html:dist` task compiles the GWT sources and creates a deployable web application in the `html/build/dist` directory.
    - You can deploy the contents of the `html/build/dist` directory to itch.io as a zip file. 
