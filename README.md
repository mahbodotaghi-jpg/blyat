# Nitro Client (Fabric 1.21.4)

This is a **starter client-side Fabric mod project** that recreates the supplied dark panel / neon-green GUI and module names.

## Requirements
- Minecraft Java Edition 1.21.4
- Java 21
- Fabric Loader and Fabric API
- Gradle (or open the folder in IntelliJ IDEA and let Gradle import it)

## Build
Run `gradle build` in this folder. The jar will be created under `build/libs/`.

## Use
1. Put the built `nitro-client-1.0.0.jar` in your Minecraft `mods` folder alongside Fabric API.
2. Launch Minecraft 1.21.4 with Fabric.
3. Press **Right Shift** to open/close the menu.

## Important
The GUI and module toggles are implemented. The listed combat/ESP/freecam/ore modules are currently **UI placeholders**; toggling them changes their enabled state but does not perform the cheat behavior yet. This starter is intended for singleplayer or a private world/server where you have permission. Don't use it to disrupt public servers or other players.
