package byowTools.TileEngine;

import edu.princeton.cs.algs4.StdDraw;
import java.awt.Color;
import java.awt.Font;

/**
 * Utility class for rendering tiles. You do not need to modify this file. You're welcome
 * to, but be careful. We strongly recommend getting everything else working before
 * messing with this renderer, unless you're trying to do something fancy like
 * allowing scrolling of the screen or tracking the avatar or something similar.
 */
public class TERenderer {
    private static final int TILE_SIZE = 16;  // The size of each tile in pixels
    private int width;  // The width of the window in tiles
    private int height;  // The height of the window in tiles
    private int xOffset;  // The horizontal offset for rendering tiles
    private int yOffset;  // The vertical offset for rendering tiles

    /**
     * Initializes the tile rendering engine with a window of size w by h tiles,
     * and sets the x and y offsets for rendering.
     *
     * @param w the width of the window in tiles
     * @param h the height of the window in tiles
     * @param xOff the horizontal offset for rendering
     * @param yOff the vertical offset for rendering
     */
    public void initialize(int w, int h, int xOff, int yOff) {
        this.width = w;
        this.height = h;
        this.xOffset = xOff;
        this.yOffset = yOff;
        StdDraw.setCanvasSize(width * TILE_SIZE, height * TILE_SIZE);
        Font font = new Font("Monaco", Font.BOLD, TILE_SIZE - 2);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        StdDraw.clear(new Color(0, 0, 0));

        StdDraw.enableDoubleBuffering();
        StdDraw.show();
    }

    /**
     * Initializes the tile rendering engine with a window of size w by h tiles,
     * without any offsets for rendering.
     *
     * @param w the width of the window in tiles
     * @param h the height of the window in tiles
     */
    public void initialize(int w, int h) {
        initialize(w, h, 0, 0);
    }

    /**
     * Renders the given 2D array of TETile objects to the screen, starting from the
     * specified x and y offsets.
     *
     * @param world the 2D array of TETile objects to render
     */
    public void renderFrame(TETile[][] world) {
        int numXTiles = world.length;
        int numYTiles = world[0].length;
        StdDraw.clear(new Color(0, 0, 0));
        for (int x = 0; x < numXTiles; x++) {
            for (int y = 0; y < numYTiles; y++) {
                if (world[x][y] == null) {
                    throw new IllegalArgumentException("Tile at position x=" + x + ", y=" + y
                            + " is null.");
                }
                world[x][y].draw(x + xOffset, y + yOffset);
            }
        }
        StdDraw.show();
    }
}
