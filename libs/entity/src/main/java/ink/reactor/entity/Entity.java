package ink.reactor.entity;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class Entity {
    public static final AtomicInteger ENTITY_ID_COUNTER = new AtomicInteger();

    private final int id = ENTITY_ID_COUNTER.incrementAndGet();
    private final EntityType type;
    private final Object data;
}