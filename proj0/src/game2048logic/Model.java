package game2048logic;

import game2048rendering.Board;
import game2048rendering.Side;
import game2048rendering.Tile;

import java.util.Formatter;

/** The state of a game of 2048.
 *  This class maintains the state of the board, the score,
 *  and the logic for moving and merging tiles.
 *  @作者 P. N. Hilfinger + Josh Hug
 */
public class Model {
    /** Current contents of the board. */
    private final Board board;
    /** Current score. */
    private int score;
    /** Merged state of tiles during a tilt. */
    private boolean[][] merged;

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. Initializes an empty board with the given size.
     */
    public Model(int size) {
        board = new Board(size);
        score = 0;
        merged = new boolean[size][size];
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     *  (0 if null). VALUES is indexed by (x, y) with (0, 0) corresponding
     *  to the bottom-left corner. Used for testing purposes. Initializes the board
     *  with the given raw values and sets the score.
     */
    public Model(int[][] rawValues, int score) {
        board = new Board(rawValues);
        this.score = score;
        merged = new boolean[board.size()][board.size()];
    }

    /** Return the current Tile at (x, y), where 0 <= x < size(),
     *  0 <= y < size(). Returns null if there is no tile there.
     */
    public Tile tile(int x, int y) {
        return board.tile(x, y);
    }

    /** Return the number of squares on one side of the board. */
    public int size() {
        return board.size();
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        board.clear();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        return maxTileExists() || !atLeastOneMoveExists();
    }

    /** Returns this Model's board. */
    public Board getBoard() {
        return board;
    }

    /** Returns true if at least one space on the board is empty.
     *  Empty spaces are stored as null. */
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

    /** Returns true if any tile is equal to the maximum valid value.
     *  Maximum valid value is given by MAX_PIECE. */
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

    /** Returns true if there are any valid moves on the board.
     *  There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value. */
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

    // Helper method to check if a tile can merge with any of its neighbors
    private boolean canMergeWithNeighbor(int x, int y, int value) {
        return (x > 0 && board.tile(x - 1, y) != null && board.tile(x - 1, y).value() == value)
                || (x < board.size() - 1 && board.tile(x + 1, y) != null && board.tile(x + 1, y).value() == value)
                || (y > 0 && board.tile(x, y - 1) != null && board.tile(x, y - 1).value() == value)
                || (y < board.size() - 1 && board.tile(x, y + 1) != null && board.tile(x, y + 1).value() == value);
    }

    // Moves a tile up as far as possible without merging
    public void moveTileUpAsFarAsPossible(int x, int y) {
        Tile t = board.tile(x, y);
        if (t == null) {
            return;
        }

        int targetY = y;
        while (targetY < board.size() - 1 && board.tile(x, targetY + 1) == null) {
            targetY++;
        }

        if (targetY != y) {
            board.move(x, targetY, t);
        }
    }

    // Moves a tile up and handles merging
    public void moveTileUpAsFarAsPossibleWithMerging(int x, int y) {
        Tile t = board.tile(x, y);
        if (t == null) {
            return;
        }

        int targetY = y;
        while (targetY < board.size() - 1 && board.tile(x, targetY + 1) == null) {
            targetY++;
        }

        // Check for possible merge
        if (targetY < board.size() - 1) {
            Tile aboveTile = board.tile(x, targetY + 1);
            if (aboveTile != null && aboveTile.value() == t.value() && !merged[x][targetY + 1]) {
                board.move(x, targetY + 1, t);  // Move tile to the merge position
                score += 2 * t.value();  // Update score
                merged[x][targetY + 1] = true;  // Mark the new tile as merged
                return;  // Exit to avoid moving the merged tile again
            }
        }

        // Only move if the target position is different from the current position
        if (targetY != y) {
            board.move(x, targetY, t);
        }
    }

    // Tilts an entire column up
    public void tiltColumn(int x) {
        for (int y = board.size() - 2; y >= 0; y--) {
            moveTileUpAsFarAsPossibleWithMerging(x, y);
        }
    }

    // Tilts the entire board up
    public void tilt(Side side) {
        board.setViewingPerspective(side);
        merged = new boolean[board.size()][board.size()];  // Reset merged state
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
