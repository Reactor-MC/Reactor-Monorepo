# Project
## Requirements
1) Java +23 (I recommend use [graalvm](https://www.graalvm.org/downloads/) or [azul jdk](https://www.azul.com/downloads/?package=jdk#zulu))
2) [Git](https://git-scm.com/downloads)

## How is organized?
1) Features – Contains all plugins.
2) API – Acts as the bridge between libraries, the server, and the protocol.
3) Protocol – Handles all packets (inbound and outbound).
4) Libs – Contains libraries, some of which may depend on the API and protocol.
5) Server – Implements the API, starts the server, and establishes a protocol connector.

# Recommended code styles
## Install editorconfig extension (if you editor don't support)

## Split method parameters
If a method exceeds 140 characters in width, split the parameters into multiple lines.

```java
public void idk(final @NonNull String text, final int number1, final Random randomInstance, final Collection<String> test, final double decimalNumber) {    // Execute the code
    // Execute the code
}
```

Will need write with this format:

```java
public void idk(
    final @NonNull String text,
    final int number1,
    final Random randomInstance,
    final Collection<String> test,
    final double decimalNumber
) {
    // Execute the code
}
```

## Limit abstraction capes
The maximum allowed abstraction layers is 3. If you need more, reconsider your design.
Example (Entity System Hierarchy):

```EntityZombie -> EntityMob -> EntityCreature -> EntityLiving -> Entity```

## Use star imports if you really need
If a class frequently uses constants from another class, star imports can improve readability.

### Option 1 (Explicit Imports):
```java
final ExpectedSizeBuffer buffer = new ExpectedSizeBuffer(
    DataSize.varInt(randomInt1) +
    // Header
    DataSize.INT +
    DataSize.LONG +
    DataSize.DOUBLE +
    // Content
    DataSize.FLOAT +
    DataSize.FLOAT +
    DataSize.string(text)
);
```
### Option 2 (Star Imports for Clarity):
```java
final ExpectedSizeBuffer buffer = new ExpectedSizeBuffer(
    varInt(randomInt1) +
    // Header
    INT + LONG + DOUBLE +
    // Content
    FLOAT + FLOAT +
    string(text)
);
```
Both styles are acceptable—choose what best suits readability.

### Another Example (Switch Case):
### Option 1 (Explicit Imports):
```java
switch (id) {
    // Basic types: String, int, double, float, boolean, enums, etc
    case ItemComponent.MAX_STACK_SIZE, ItemComponent.MAX_DAMAGE, ItemComponent.DAMAGE, ItemComponent.REPAIR_COST:
        return buffer.readVarInt();
    case ItemComponent.UNBREAKABLE, ItemComponent.ENCHANTMENT_GLINT_OVERRIDE, ItemComponent.ENCHANTABLE:
        return buffer.readBoolean();
    case ItemComponent.ITEM_MODEL:
        return buffer.readString();
    case ItemComponent.RARITY:
        return Rarity.byId(buffer.readVarInt());
    ...
}
```

### Option 2 (Star Imports for Brevity):
```java
switch (id) {
    // Basic types: String, int, double, float, boolean, enums, etc
    case MAX_STACK_SIZE, MAX_DAMAGE, DAMAGE, REPAIR_COST:
        return buffer.readVarInt();
    case UNBREAKABLE, ENCHANTMENT_GLINT_OVERRIDE, ENCHANTABLE:
        return buffer.readBoolean();
    case ITEM_MODEL:
        return buffer.readString();
    case RARITY:
        return Rarity.byId(buffer.readVarInt());
    ...
}
```

That's all!. Feel free to send your pull request or commit
