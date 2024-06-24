package game2048logic;

import game2048rendering.Board;
import game2048rendering.Side;
import game2048rendering.Tile;

import java.util.Formatter;

/** 2048 游戏的状态。
 *  这个类维护棋盘的状态、分数，以及移动和合并方块的逻辑。
 *  @作者 P. N. Hilfinger + Josh Hug
 */
public class Model {
    /** 当前棋盘的内容。 */
    private final Board board;
    /** 当前分数。 */
    private int score;
    /** 在倾斜过程中合并方块的状态。 */
    private boolean[][] merged;

    /** 最大的方块值。 */
    public static final int MAX_PIECE = 2048;

    /** 初始化一个新的 2048 游戏，棋盘大小为 SIZE，没有方块，分数为 0。 */
    public Model(int size) {
        board = new Board(size);
        score = 0;
        merged = new boolean[size][size];
    }

    /** 初始化一个新的 2048 游戏，其中 RAWVALUES 包含方块的值（如果为 null 则为 0）。
     *  VALUES 按 (x, y) 索引，其中 (0, 0) 对应于左下角。用于测试目的。
     *  使用给定的原始值初始化棋盘并设置分数。
     */
    public Model(int[][] rawValues, int score) {
        board = new Board(rawValues);
        this.score = score;
        merged = new boolean[board.size()][board.size()];
    }

    /** 返回当前 (x, y) 位置的方块，其中 0 <= x < size()，0 <= y < size()。如果没有方块，则返回 null。 */
    public Tile tile(int x, int y) {
        return board.tile(x, y);
    }

    /** 返回棋盘的一边的方块数量。 */
    public int size() {
        return board.size();
    }

    /** 返回当前分数。 */
    public int score() {
        return score;
    }

    /** 清空棋盘并重置分数。 */
    public void clear() {
        score = 0;
        board.clear();
        resetMerged();
    }

    /** 将 TILE 添加到棋盘上。当前位置不能有方块。 */
    public void addTile(Tile tile) {
        board.addTile(tile);
    }

    /** 如果游戏结束（没有移动，或者棋盘上有一个值为 2048 的方块），则返回 true。 */
    public boolean gameOver() {
        return maxTileExists() || !atLeastOneMoveExists();
    }

    /** 返回此 Model 的棋盘。 */
    public Board getBoard() {
        return board;
    }

    /** 如果棋盘上至少有一个空格，则返回 true。空格用 null 表示。 */
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

    /** 如果有任何方块的值等于最大有效值，则返回 true。最大有效值由 MAX_PIECE 给出。 */
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

    /** 如果棋盘上有任何有效移动，则返回 true。有效移动有两种情况：
     * 1. 棋盘上至少有一个空格。
     * 2. 有两个相邻的方块具有相同的值。
     */
    public boolean atLeastOneMoveExists() {
        if (emptySpaceExists()) {
            return true;
        }
        for (int x = 0; x < board.size(); x++) {
            for (int y = 0; y < board.size(); y++) {
                Tile t = board.tile(x, y);
                if (t != null) {
                    if (canMergeWithNeighbor(x, y, t.value())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // 检查一个方块是否可以与任何相邻的方块合并
    private boolean canMergeWithNeighbor(int x, int y, int value) {
        return (x > 0 && canMerge(x - 1, y, value))
                || (x < board.size() - 1 && canMerge(x + 1, y, value))
                || (y > 0 && canMerge(x, y - 1, value))
                || (y < board.size() - 1 && canMerge(x, y + 1, value));
    }

    private boolean canMerge(int x, int y, int value) {
        Tile neighbor = board.tile(x, y);
        return neighbor != null && neighbor.value() == value;
    }

    /** 向上移动一个方块并处理合并。 */
    public void moveTileUpAsFarAsPossible(int x, int y) {
        Tile tile = board.tile(x, y);
        if (tile == null) {
            return;
        }

        int targetY = y;
        while (targetY < board.size() - 1 && board.tile(x, targetY + 1) == null) {
            targetY++;
        }

        if (targetY < board.size() - 1 && canMerge(x, targetY + 1, tile.value()) && !merged[x][targetY + 1]) {
            board.move(x, targetY + 1, tile);
            score += tile.value() * 2;
            merged[x][targetY + 1] = true;
        } else if (targetY != y) {
            board.move(x, targetY, tile);
        }
    }

    /** 添加 moveTileUpAsFarAsPossibleWithMerging 方法，调用 moveTileUpAsFarAsPossible。 */
    public void moveTileUpAsFarAsPossibleWithMerging(int x, int y) {
        moveTileUpAsFarAsPossible(x, y);
    }

    /** 向上倾斜整列。 */
    public void tiltColumn(int x) {
        int y = board.size() - 2;
        while (y >= 0) {
            moveTileUpAsFarAsPossibleWithMerging(x, y);
            y--;
        }
    }

    /** 向上倾斜整个棋盘。 */
    public void tilt(Side side) {
        board.setViewingPerspective(side);
        resetMerged();
        for (int x = 0; x < board.size(); x++) {
            tiltColumn(x);
        }
        board.setViewingPerspective(Side.NORTH);
    }

    /** 重置所有方块的合并状态。 */
    private void resetMerged() {
        int size = merged.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                merged[i][j] = false;
            }
        }
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
