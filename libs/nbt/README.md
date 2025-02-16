# Reactor - Atom NBT

## Faster nbt alternative to adventure 

## Features:
1) DON'T USE GSON! Faster json serialization
2) Decoder (file and byte[])
3) Encoder (Json, simple string, file)
4) Support Compression (gzip and zlib)
5) Keys can be anything (not only strings)
6) Faster Serialization (Use custom buffer)
7) Support all tags
8) Use fastutil and have 2 types of nbt (NBTGeneral and NBTFastAdd)

Example of usage:
```java
    final NBT nbt = new NBTGeneral();
    nbt.addString("key", "value");
    nbt.addStringList("listKey", List.of("hi", "world", ":v"));
    nbt.addString(30, "test xd"); // Keys not only can be strings
    nbt.addString(Map.of("hi", "world"), "test map");
    
    final byte[] cachedKey = "cachedKey".getBytes();
    nbt.addString(cachedKey, "test");

    final NBT innerNBT = new NBTFastAdd();
    innerNBT.addString("hi", "world"); 

    nbt.addCompound("inner", innerNBT);

    try {
        // Compression types: none, gzip and zlib
        NBTStreamWriter.writeFile(Path.of("test.nbt"), nbt, CompressionType.GZIP);

        final NBT decodedNBT = NBTFileDecoder.decode(new File("test.nbt"));
        System.out.println(NBTStringWriter.toJson(decodedNBT));
        System.out.println(decodedNBT.toString());
    } catch (IOException e) {
        Logger.error(e);
    }

    System.out.println(nbt.toString());
```