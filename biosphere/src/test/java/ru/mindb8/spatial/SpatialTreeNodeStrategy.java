package ru.mindb8.spatial;

import java.util.List;

public interface SpatialTreeNodeStrategy<A extends SpatialArea<A>> {
    int maxChildren();
    SpatialTreeNode<A> chooseNodeToInsert(List<SpatialTreeNode<A>> list, SpatialArea<A> area);
}
