package com.sh1penfire.cri_christmas.util.interfaces;

public interface Prov<T, E, P> {
    default P get(T object, E object2){
        return null;
    }
}
