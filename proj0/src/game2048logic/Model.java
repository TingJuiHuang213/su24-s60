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

    /* Coordinate System: column x, row y of the board (where x = 0,
     * y = 0 is the lower-left corner of the board) will correspond
     * to board.tile(x, y).  Be careful!
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. Initializes an empty board with the given size.
     */
    public Model(int size) {
        board = new Board(size);
        score = 0;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     *  (0 if null). VALUES is indexed by (x, y) with (0, 0) corresponding
     *  to the bottom-left corner. Used for testing purposes. Initializes the board
     *  with the given raw values and sets the score.
     */
    public Model(int[][] rawValues, int score) {
        board = new Board(rawValues);
        this.score = score;
    }

    /** Return the current Tile at (x, y), where 0 <= x < size(),
     *  0 <= y < size(). Returns null if there is no tile there.
     *  Used for testing. This method provides access to the board tiles for testing.
     */
    public Tile tile(int x, int y) {
        return board.tile(x, y);
    }

    /** Return the number of squares on one side of the board.
     *  This method returns the size of the board.
     */
    public int size() {
        return board.size();
    }

    /** Return the current score.
     *  This method returns the current game score.
     */
    public int score() {
        return score;
    }

    /** Clear the board to empty and reset the score.
     *  This method clears the board and resets the score to 0.
     */
    public void clear() {
        score = 0;
        board.clear();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. This method adds a tile to the board at its position.
     */
    public void addTile(Tile tile) {
        board.addTile(tile);
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). This method checks if the game is over
     *  by verifying if there is a tile with value 2048 or if no valid moves are left.
     */
    public boolean gameOver() {
        return maxTileExists() || !atLeastOneMoveExists();
    }

    /** Returns this Model's board.
     *  This method returns the board object.
     */
    public Board getBoard() {
        return board;
    }

    /** Returns true if at least one space on the board is empty.
     *  Empty spaces are stored as null. This method checks for any empty spaces on the board.
     */
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

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     * This method checks if any tile on the board has the maximum value of 2048.
     */
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

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     * This method checks if there are any valid moves left on the board.
     */
    public boolean atLeastOneMoveExists() {
        if (emptySpaceExists()) {
            return true;
        }
        for (int x = 0; x < board.size(); x++) {
            for (int y = 0; y < board.size(); y++) {
                Tile t = board.tile(x, y);
                if (t != null) {
                    if ((x > 0
                            && board.tile(x - 1, y) != null
                            && board.tile(x - 1, y).value() == t.value())
                            || (x < board.size() - 1
                            && board.tile(x + 1, y) != null
                            && board.tile(x + 1, y).value() == t.value())
                            || (y > 0
                            && board.tile(x, y - 1) != null
                            && board.tile(x, y - 1).value() == t.value())
                            || (y < board.size() - 1
                            && board.tile(x, y + 1) != null
                            && board.tile(x, y + 1).value() == t.value())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // 空的 tiltWrapper 方法
    public void tiltWrapper(Side side) {
        // 空實現，用於避免編譯錯誤
    }

    // 空的 moveTileUpAsFarAsPossible 方法
    public void moveTileUpAsFarAsPossible(int x, int y) {
        // 空實現，用於避免編譯錯誤
    }

    // 空的 tiltColumn 方法
    public void tiltColumn(int x) {
        // 空實現，用於避免編譯錯誤
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
        return (o instanceof Model m) && this.toString().equals(m.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}

// Add a newline at the end of the file to resolve NewlineAtEndOfFile error.