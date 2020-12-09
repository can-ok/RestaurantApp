package com.resturant.restapi.builder;

public abstract class Builder<T>{
    public abstract T build();

    public abstract T id(int id);
}
