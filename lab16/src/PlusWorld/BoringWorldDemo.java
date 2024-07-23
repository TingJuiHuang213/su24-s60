package PlusWorld;

import byowTools.TileEngine.TERenderer;
import byowTools.TileEngine.TETile;
import byowTools.TileEngine.Tileset;

/**
 * Draws a world that is mostly empty except for a small region.
 *
 * This class demonstrates the basic usage of the TERenderer to render a
 * 2D world with specific tiles.
 */
public class BoringWorldDemo {

    private static final int WIDTH = 60; // Width of the world
    private static final int HEIGHT = 30; // Height of the world

    public static void main(String[] args) {
        // Initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // Initialize tiles
        TETile[][] world = createEmptyWorld(WIDTH, HEIGHT);

        // Fill in a block 14 tiles wide by 4 tiles tall
        fillBlock(world, 20, 5, 15, 5, Tileset.WALL);

        // Draw the world to the screen
        ter.renderFrame(world);
    }

    /**
     * Creates an empty world filled with NOTHING tiles.
     *
     * @param width The width of the world.
     * @param height The height of the world.
     * @return A 2D array representing the empty world.
     */
    private static TETile[][] createEmptyWorld(int width, int height) {
        TETile[][] world = new TETile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        return world;
    }

    /**
     * Fills a rectangular block in the world with the specified tile.
     *
     * @param world The world to be modified.
     * @param startX The x-coordinate of the starting point.
     * @param startY The y-coordinate of the starting point.
     * @param width The width of the block.
     * @param height The height of the block.
     * @param tile The tile to fill the block with.
     */
    private static void fillBlock(TETile[][] world, int startX, int startY, int width, int height, TETile tile) {
        for (int x = startX; x < startX + width; x++) {
            for (int y = startY; y < startY + height; y++) {
                world[x][y] = tile;
            }
        }
    }
}
