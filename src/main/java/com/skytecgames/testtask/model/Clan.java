package com.skytecgames.testtask.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Clan {
    private final long id;
    private final String name;
    private final AtomicInteger gold;

    public Clan(long id, String name, AtomicInteger gold) {
        this.id = id;
        this.name = name;
        this.gold = gold;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AtomicInteger getGold() {
        return gold;
    }
}
