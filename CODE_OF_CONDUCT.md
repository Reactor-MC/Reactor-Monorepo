# Code styles
If you don't follow this styles, your pull request will not accept

## General
1) Use indentation in 4 spaces
2) Always use "final" in method parameters

## Split method parameters
If the method width is more than 140 characters, you need split the method parameters. Example:

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
Maximum capes of abstraction are 3. If you need more, please check again your code.
Example with entity system:

```EntityZombie -> EntityMob -> EntityCreature -> EntityLiving -> Entity```

## Use star imports if you really need
Imagine I will create a packet and I need set the size of the buffer. But the packet contains a lot of variables
### Example of normal implementation (Option 1):
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
### Implementation with star imports (Option 2):
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
These implementations are good. You can use whatever you want.

### Another example (Option 1):
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

### Option 2:
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