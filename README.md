> [!WARNING]
> I deprecated this project because it had major design flaws and was written in a poorly scalable and unoptimized way.
> At the same time, it was the first time I had worked on such a large project, and I didn't know how to structure it properly.
> To see the new code, go to: https://github.com/Reactor-MC/Reactor

# Old description:

<img src=".github/banner.png">

# Reactor
An open-source Minecraft server written in Java.

## How does it work?
You don’t need to compile your own server (like Minestom) or deal with unnecessary features that come with other servers (such as Paper, Spigot, or the vanilla Minecraft server).

With Reactor, you decide which features to add to your server. This makes it more lightweight and scalable. For example, if you need a lobby, you only add chat, NPCs, and a lobby core plugin—without extra features like combat, entity AI, world generation, and others.

Every feature is provided as an external plugin—chat, NPCs, entity AI, world generation, combat, etc.
However, unlike Minestom, these plugins are already coded for you, so you don’t have to develop every feature yourself—just add the plugin.

## Why Reactor?
Spigot servers contain poorly written code, full of memory leaks and bad practices.
What about Minestom? It’s not a bad alternative, but you have to code everything yourself.

These are the main reasons for creating Reactor: a well-structured, high-performance server focused on a modular system based on plugins and events.

**Server Compliance Notice:**
- This server software is **reverse-engineered** and not official.
- Mojang's [EULA](https://www.minecraft.net/eula) applies to all Minecraft servers.
- Server owners are responsible for complying with Mojang’s terms.
