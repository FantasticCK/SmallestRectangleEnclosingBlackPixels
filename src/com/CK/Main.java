package com.CK;

public class Main {

    public static void main(String[] args) {
        char[] a = new char[]{'0', '0', '1', '0'};
        char[] b = new char[]{'0', '1', '1', '0'};
        char[] c = new char[]{'0', '1', '0', '0'};

        char[][] image = new char[3][4];
        image[0] = a;
        image[1] = b;
        image[2] = c;

        Solution solution = new Solution();
        System.out.println(solution.minArea(image, 0, 2));
    }
}

class Solution {
    public int minArea(char[][] image, int x, int y) {
        if (image.length == 0 || image[0].length == 0) return 0;
        boolean vertical = true;
        int top, bottom, left, right;
        int[] origin = new int[]{x, y};
        top = binarySearchFirstBound(image, "UP", origin);
        bottom = binarySearchFirstBound(image, "DOWN", origin);
        left = binarySearchFirstBound(image, "LEFT", origin);
        right = binarySearchFirstBound(image, "RIGHT", origin);
        return (bottom - top +1) * (right - left +1);
    }

    private boolean IfWhiteLine(char[][] matrix, boolean vertical, int[] index) {
        int row = index[0];
        int col = index[1];
        int lo = 0, hi = vertical ? matrix.length - 1 : matrix[0].length - 1;
        for (int i = lo; i <= hi; i++) {
            if (vertical) {
                if (matrix[i][col] == '1') return false;
            } else {
                if (matrix[row][i] == '1') return false;
            }
        }
        return true;
    }

    private int binarySearchFirstBound(char[][] matrix, String direction, int[] origin) {
        int row = origin[0], col = origin[1];
        boolean midWhiteLine;
        if (direction.equals("UP") || direction.equals("DOWN")) {
            int lo = direction.equals("UP") ? 0 : row, hi = direction.equals("UP") ? row : matrix.length - 1, mid;
            while (lo + 1 < hi) {
                mid = (lo + hi) / 2;
                midWhiteLine = this.IfWhiteLine(matrix, false, new int[]{mid, col});
                if ((midWhiteLine && direction.equals("UP") || (!midWhiteLine) && direction.equals("DOWN"))) {
                    lo = mid;
                } else {
                    hi = mid;
                }
            }
            if (direction.equals("UP")) {
                return !this.IfWhiteLine(matrix, false, new int[]{lo, col}) ? lo : hi;
            } else {
                return !this.IfWhiteLine(matrix, false, new int[]{hi, col}) ? hi : lo;
            }
        } else if (direction.equals("LEFT") || direction.equals("RIGHT")) {
            int lo = direction.equals("LEFT") ? 0 : col, hi = direction.equals("LEFT") ? col : matrix[0].length - 1, mid;
            while (lo + 1 < hi) {
                mid = (lo + hi) / 2;
                midWhiteLine = this.IfWhiteLine(matrix, true, new int[]{row, mid});
                if ((midWhiteLine && direction.equals("LEFT") || (!midWhiteLine) && direction.equals("RIGHT"))) {
                    lo = mid;
                } else {
                    hi = mid;
                }
            }
            if (direction.equals("LEFT")) {
                return !this.IfWhiteLine(matrix, true, new int[]{row, lo}) ? lo : hi;
            } else {
                return !this.IfWhiteLine(matrix, true, new int[]{row, hi}) ? hi : lo;
            }
        }
        return -1;
    }
}
