# Realistic Water Era Music

A lightweight, client-side Fabric mod for **Minecraft 26.3**. It replaces Minecraft's normal background soundtrack with the track bundled in this mod and loops it continuously.

## What it does

- Starts the soundtrack as soon as the client begins ticking after startup (no vanilla music wait timer).
- Repeats the same track until Minecraft closes.
- Keeps the soundtrack going across world joins and dimension changes; if the sound engine reloads, the mod starts the track again.
- Suppresses vanilla background-music scheduling while enabled, so another Minecraft music track cannot take over.
- Adds a one-button **ON/OFF** settings screen through Mod Menu.
- Streams the OGG file through Minecraft's sound engine, avoiding an external MP3 decoder or extra audio library.
- Does not replace ordinary sound effects. Music discs/jukeboxes remain separate Minecraft sounds.

## Track included

**Gaiety in the Golden Age — Aaron Kenny** (provided by the user and converted from their MP3 to OGG for Minecraft's sound system).

## Requirements

- Minecraft Java **26.3**
- Fabric Loader **0.20.1 or newer**
- Fabric API **0.166.2+26.3 or newer for 26.3**
- Mod Menu **21.0.1 or newer** for the in-game settings button (the music player itself does not require Mod Menu)
- Java **25** to build the project

## Install after building

1. Build the project using the steps below.
2. Copy `build/libs/realistic-water-era-music-1.0.0.jar` into your Minecraft `mods` folder.
3. Launch Minecraft 26.3 with Fabric.
4. Open **Mods → Realistic Water Era Music → Configure** to turn the soundtrack OFF or ON.

The mod creates `config/realistic-water-era-music.properties` and remembers the ON/OFF setting between launches. It is enabled by default.

## Build on Windows

Install a JDK 25 and make sure `java -version` reports 25. Run `build.bat` from this folder; it downloads Gradle 9.7.1 automatically and then fetches the Fabric/Minecraft/Mod Menu dependencies (internet required).

You can also import the folder as a Gradle project in IntelliJ IDEA and run the `build` task.

## Build in GitHub Actions

Push the project to a GitHub repository and run the **Build mod** workflow from the Actions tab. It uploads `realistic-water-era-music-jar` as an artifact.

## Notes

- This delivery is a source project ZIP, not a precompiled JAR. The build container available for this session only has Java 21 and no Gradle installation; Minecraft 26.3's current Fabric toolchain requires Java 25. The source and asset are packaged, but I could not honestly mark a JAR as compiled or tested here.
- The supplied audio is included for your personal instance. Check the track's usage rights before redistributing the compiled mod or audio.
