package ru.mindb8.spatial;

import lombok.Getter;
import lombok.val;

import java.util.ArrayList;
import java.util.List;



public class SpatialTreeNode<A extends SpatialArea<A>>  {
    private final SpatialTree<A> tree;
    @Getter
    private final A area;
    @Getter
    private SpatialTreeNode<A> parent;
    @Getter
    private final List<SpatialTreeNode<A>> children = new ArrayList<>();
    @Getter
    private boolean isData;



    public SpatialTreeNode(SpatialTree<A> tree, A area) {
        this.parent = null;
        this.area = area;
        this.tree = tree;
    }

    public SpatialTreeNode(SpatialTree<A> tree, SpatialTreeNode<A> parent, A area) {
        this.parent = parent;
        this.area = area;
        this.tree = tree;
    }

    public SpatialTreeNode<A> add(A area) {
        val strategy = tree.getStrategy();
        val enclosed = new ArrayList<SpatialTreeNode<A>>();
        for(val child : children) {
            val newArea = child.getArea().encompass(area);
            if(newArea.equals(child.getArea())) {
                enclosed.add(child);
            }
        }
        if(enclosed.isEmpty()) {
            // пытаемся добавить
            if(children.size() < strategy.maxChildren()) {
                val newNode = new SpatialTreeNode<A>(tree, area);
                children.add(newNode);
                return newNode;
            } else {
                // Начинаем укрупнять узлы
                val node = strategy.chooseNodeToInsert(children, area);
                return node.add(area);
            }
        } else {
            // выбираем из узлов наименьшего размера и глубины
            val node = strategy.chooseNodeToInsert(enclosed, area);
            return node.add(area);
        }

        // если принадлежит целиком одному из дочерних узлов, то добавляем к нему
        // если целиком не принадлежит ни одному, то
        //  1 если есть свободные узлы на уровне, то добавляем
        //  2 если нет свободных узлов, то выбираем "наибольший" для которого не выполняется условие переполнения

    }
}
