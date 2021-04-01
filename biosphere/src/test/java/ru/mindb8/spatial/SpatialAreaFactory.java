package ru.mindb8.spatial;

public interface SpatialAreaFactory<A extends SpatialArea<?>> {
    A create();
    A universal();
}
