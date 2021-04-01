package ru.mindb8.spatial;

public interface SpatialArea<T extends SpatialArea<T>> {
    T encompass(T area);
    boolean intersects(T area);
    T universal();
}
