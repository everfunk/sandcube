package ru.mindb8.spatial;

import java.awt.geom.Area;

public class UniversalArea implements SpatialArea<UniversalArea> {
    @Override
    public UniversalArea encompass(UniversalArea area) {
        return this;
    }

    @Override
    public boolean intersects(UniversalArea area) {
        return true;
    }

}
