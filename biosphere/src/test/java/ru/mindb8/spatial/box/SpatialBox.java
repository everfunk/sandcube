package ru.mindb8.spatial.box;

import ru.mindb8.spatial.SpatialArea;

public class SpatialBox<N extends Number> implements SpatialArea<SpatialBox> {

//    private final int dimension;
//
//    SpatialBox() {
//
//    }

    @Override
    public SpatialBox encompass(SpatialBox area) {
        return null;
    }

    @Override
    public boolean intersects(SpatialBox area) {
        return false;
    }
}
