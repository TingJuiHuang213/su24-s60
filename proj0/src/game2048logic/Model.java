package game2048logic;

import game2048rendering.Board;
import game2048rendering.Side;
import game2048rendering.Tile;

import java.util.Formatter;

public class Model {
    private final Board board;
    private int score;
    private boolean[][] merged;

    public static final int MAX_PIECE = 2048;

    public Model(int size) {
        board = new Board(size);
        score = 0;
        merged = new boolean[size][size];
    }

    public Model(int[][] rawValues, int score) {
        board = new Board(rawValues);
        this.score = score;
        merged = new boolean[board.size()][board.size()];
    }

    public Tile tile(int x, int y) {
        return board.tile(x, y);
    }

    public int size() {
        return board.size();
    }

    public int score() {
        return score;
    }

    public void clear() {
        score = 0;
        board.clear();
    }

    public void addTile(Tile tile) {
        board.addTile(tile);
    }

    public boolean gameOver() {
        return maxTileExists() || !atLeastOneMoveExists();
    }

    public Board getBoard() {
        return board;
    }

    public boolean emptySpaceExists() {
        for (int x = 0; x < board.size(); x++) {
            for (int y = 0; y < board.size(); y++) {
                if (board.tile(x, y) == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean maxTileExists() {
        for (int x = 0; x < board.size(); x++) {
            for (int y = 0; y < board.size(); y++) {
                Tile t = board.tile(x, y);
                if (t != null && t.value() == MAX_PIECE) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean atLeastOneMoveExists() {
        if (emptySpaceExists()) {
            return true;
        }
        for (int x = 0; x < board.size(); x++) {
            for (int y = 0; y < board.size(); y++) {
                Tile t = board.tile(x, y);
                if (t != null && hasMergeableNeighbor(x, y, t)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasMergeableNeighbor(int x, int y, Tile t) {
        return (x > 0 && canMerge(t, board.tile(x - 1, y))) ||
                (x < board.size() - 1 && canMerge(t, board.tile(x + 1, y))) ||
                (y > 0 && canMerge(t, board.tile(x, y - 1))) ||
                (y < board.size() - 1 && canMerge(t, board.tile(x, y + 1)));
    }

    private boolean canMerge(Tile t1, Tile t2) {
        return t2 != null && t1.value() == t2.value();
    }

    public void moveTileUpAsFarAsPossible(int x, int y) {
        Tile t = board.tile(x, y);
        if (t == null) {
            return;
        }

        int targetY = findFurthestY(x, y);

        if (targetY != y) {
            board.move(x, targetY, t);
        }
    }

    private int findFurthestY(int x, int y) {
        int targetY = y;
        while (targetY < board.size() - 1 && board.tile(x, targetY + 1) == null) {
            targetY++;
        }
        return targetY;
    }

    public void moveTileUpAsFarAsPossibleWithMerging(int x, int y) {
        Tile t = board.tile(x, y);
        if (t == null) {
            return;
        }

        int targetY = findFurthestY(x, y);

        if (targetY < board.size() - 1 && canMergeWithAbove(x, targetY, t)) {
            mergeWithAbove(x, targetY, t);
        } else if (targetY != y) {
            board.move(x, targetY, t);
        }
    }

    private boolean canMergeWithAbove(int x, int targetY, Tile t) {
        Tile aboveTile = board.tile(x, targetY + 1);
        return aboveTile != null && aboveTile.value() == t.value() && !merged[x][targetY + 1];
    }

    private void mergeWithAbove(int x, int targetY, Tile t) {
        board.move(x, targetY + 1, t);
        score += 2 * t.value();
        merged[x][targetY + 1] = true;
    }

    public void tiltColumn(int x) {
        for (int y = board.size() - 2; y >= 0; y--) {
            moveTileUpAsFarAsPossibleWithMerging(x, y);
        }
    }

    public void tilt(Side side) {
        board.setViewingPerspective(side);
        merged = new boolean[board.size()][board.size()];
        for (int x = 0; x < board.size(); x++) {
            tiltColumn(x);
        }
        board.setViewingPerspective(Side.NORTH);
    }

    public void tiltWrapper(Side side) {
        tilt(side);
    }

    @Override
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int y = size() - 1; y >= 0; y -= 1) {
            for (int x = 0; x < size(); x += 1) {
                if (tile(x, y) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(x, y).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (game is %s) %n", score(), over);
        return out.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Model) {
            return this.toString().equals(o.toString());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
