package ru.mindb8.spatial.box;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ru.mindb8.spatial.SpatialArea;
import ru.mindb8.spatial.UniversalArea;

import java.util.Arrays;

/**
 * Immutable spatial box
 */
public class IntSpatialBox implements SpatialArea<IntSpatialBox> {
    private final int[] left;
    private final int[] right;

    @Getter
    @RequiredArgsConstructor
    public enum Universal {
        UNIVERSAL(new UniversalArea());
        private final UniversalArea x;
    }

    public IntSpatialBox(int[] left, int[] right) {
        int sz = Math.min(left.length, right.length);
        this.left = Arrays.copyOf(left, sz);
        this.right = Arrays.copyOf(right, sz);
    }

    private IntSpatialBox() {
        left = null;
        right = null;
    }

    private IntSpatialBox(int[] left, int[] right, boolean placeholder) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IntSpatialBox encompass(IntSpatialBox area) {
        boolean changed = false;
        int[] resultLeft = Arrays.copyOf(left, left.length);
        int[] resultRight = Arrays.copyOf(left, right.length);
        for(int i = 0; i < left.length; i++) {
            if(area.left[i] < left[i]) {
                changed = true;
                resultLeft[i] = area.left[i];
            }
        }
        for(int i = 0; i < right.length; i++) {
            if(area.right[i] > right[i]) {
                changed = true;
                resultRight[i] = area.right[i];
            }
        }
        if(changed) {
            return new IntSpatialBox(resultLeft, resultRight, true);
        } else {
            return this;
        }
    }

    @Override
    public boolean intersects(IntSpatialBox area) {
        boolean result = true;
        for(int i = 0; i < left.length; i++) {
            if(area.right[i] < left[i] || area.left[i] > right[i]) {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public IntSpatialBox universal() {
        return Universal.UNIVERSAL.getX();
    }

    @NoArgsConstructor
    public static class UniversalArea extends IntSpatialBox {

        @Override
        public IntSpatialBox encompass(IntSpatialBox area) {
            return this;
        }

        @Override
        public boolean intersects(IntSpatialBox area) {
            return true;
        }

        @Override
        public IntSpatialBox universal() {
            return this;
        }

    }
}
