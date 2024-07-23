package PlusWorld;

import byowTools.TileEngine.TERenderer;
import byowTools.TileEngine.TETile;
import byowTools.TileEngine.Tileset;

/**
 * Draws a world that contains a plus-shaped structure.
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

        // Draw a plus shape at position (30, 15) with size 5
        addPlus(world, 30, 15, 5);

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
     * Adds a plus shape to the world at the specified position.
     *
     * @param world The world to draw in.
     * @param centerX The x-coordinate of the center of the plus.
     * @param centerY The y-coordinate of the center of the plus.
     * @param size The size of the plus (length of each arm).
     */
    public static void addPlus(TETile[][] world, int centerX, int centerY, int size) {
        TETile tile = Tileset.FLOWER;

        // Draw the center part
        for (int x = centerX - size / 2; x <= centerX + size / 2; x++) {
            for (int y = centerY - size / 2; y <= centerY + size / 2; y++) {
                world[x][y] = tile;
            }
        }

        // Draw the vertical arms
        for (int y = centerY - size; y < centerY - size / 2; y++) {
            for (int x = centerX - size / 2; x <= centerX + size / 2; x++) {
                world[x][y] = tile;
            }
        }
        for (int y = centerY + size / 2 + 1; y <= centerY + size; y++) {
            for (int x = centerX - size / 2; x <= centerX + size / 2; x++) {
                world[x][y] = tile;
            }
        }

        // Draw the horizontal arms
        for (int x = centerX - size; x < centerX - size / 2; x++) {
            for (int y = centerY - size / 2; y <= centerY + size / 2; y++) {
                world[x][y] = tile;
            }
        }
        for (int x = centerX + size / 2 + 1; x <= centerX + size; x++) {
            for (int y = centerY - size / 2; y <= centerY + size / 2; y++) {
                world[x][y] = tile;
            }
        }
    }
}
