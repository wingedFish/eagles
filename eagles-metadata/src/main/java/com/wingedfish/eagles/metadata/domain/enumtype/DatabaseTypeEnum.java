package com.wingedfish.eagles.metadata.domain.enumtype;

/**
 * Created by wingedfish on 2017/2/23.
 */
public enum DatabaseTypeEnum {
    MYSQL("MySQL", 1);
    private String name;
    private int type;

    DatabaseTypeEnum(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }
}
