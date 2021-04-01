package ru.mindb8.spatial;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class SpatialTree<A extends SpatialArea<A>> {
    private final SpatialAreaFactory<A> factory;
    private final SpatialTreeNode<A> rootNode;
    @Getter
    private final SpatialTreeNodeStrategy<A> strategy;

    public SpatialTree(SpatialAreaFactory<A> factory, SpatialTreeNodeStrategy<A> strategy) {
        this.factory = factory;
        this.rootNode = new SpatialTreeNode<A>(this, factory.universal());
        this.strategy = strategy;
    }

    public Collection<A> find(A request) {
        val result = new ArrayList<A>();
        val queue = new ArrayDeque<SpatialTreeNode<A>>();
        queue.push(rootNode);
        while (!queue.isEmpty()) {
            val node = queue.poll();
            if(node.getArea().intersects(request)) {
                if(node.isData()) {
                    result.add(node.getArea());
                }
                node.getChildren().forEach(queue::push);
            }
        }
        return result;
    }

    public SpatialTreeNode<A> add(A area) {
        return rootNode.add(area);
    }
}
