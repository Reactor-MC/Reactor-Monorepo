package ink.reactor.api.inventory;

public enum ClickType {
    LEFT,
    RIGHT,
    MIDDLE,
    SHIFT_LEFT,
    SHIFT_RIGHT,

    KEY_1,
    KEY_2,
    KEY_3,
    KEY_4,
    KEY_5,
    KEY_6,
    KEY_7,
    KEY_8,
    KEY_9,

    OFFHAND,
    DROP,
    CTRL_DROP,

    START_LEFT_DRAG,
    START_MIDDLE_DRAG,
    START_RIGHT_DRAG,

    END_LEFT_DRAG,
    END_MIDDLE_DRAG,
    END_RIGHT_DRAG,

    DOUBLE_CLICK,
    PICKUP;

    public boolean isNumeric() {
        final int id = ordinal();
        return id >= KEY_1.ordinal() && id <= KEY_9.ordinal();
    }
}