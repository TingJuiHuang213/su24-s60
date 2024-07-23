package PlusWorld;

import byowTools.TileEngine.TERenderer;
import byowTools.TileEngine.TETile;
import byowTools.TileEngine.Tileset;

/**
 * Draws a world that contains a plus-shaped structure.
 *
 * This class demonstrates how to draw a plus shape in a 2D world using the TERenderer.
 */
public class PlusWorld {
    private static final int WIDTH = 60; // Width of the world
    private static final int HEIGHT = 30; // Height of the world

    public static void main(String[] args) {
        // Initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // Initialize the world with empty tiles
        TETile[][] world = createEmptyWorld(WIDTH, HEIGHT);

        // Draw a plus shape at position (30, 15) with size 3
        drawPlus(world, 30, 15, 3, Tileset.WALL);

        // Render the world
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
     * Draws a plus shape in the world with the specified size and tile.
     *
     * @param world The world to be modified.
     * @param centerX The x-coordinate of the center of the plus.
     * @param centerY The y-coordinate of the center of the plus.
     * @param size The size of the plus.
     * @param tile The tile to draw the plus with.
     */
    private static void drawPlus(TETile[][] world, int centerX, int centerY, int size, TETile tile) {
        drawHorizontalLine(world, centerX, centerY, size, tile);
        drawVerticalLine(world, centerX, centerY, size, tile);
    }

    /**
     * Draws a horizontal line centered at (centerX, centerY) with the specified size and tile.
     *
     * @param world The world to be modified.
     * @param centerX The x-coordinate of the center of the line.
     * @param centerY The y-coordinate of the center of the line.
     * @param size The size of the line.
     * @param tile The tile to draw the line with.
     */
    private static void drawHorizontalLine(TETile[][] world, int centerX, int centerY, int size, TETile tile) {
        for (int x = centerX - size; x <= centerX + size; x++) {
            world[x][centerY] = tile;
        }
    }

    /**
     * Draws a vertical line centered at (centerX, centerY) with the specified size and tile.
     *
     * @param world The world to be modified.
     * @param centerX The x-coordinate of the center of the line.
     * @param centerY The y-coordinate of the center of the line.
     * @param size The size of the line.
     * @param tile The tile to draw the line with.
     */
    private static void drawVerticalLine(TETile[][] world, int centerX, int centerY, int size, TETile tile) {
        for (int y = centerY - size; y <= centerY + size; y++) {
            world[centerX][y] = tile;
        }
    }
}
