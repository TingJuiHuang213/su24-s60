package PlusWorld;

import byowTools.TileEngine.TERenderer;
import byowTools.TileEngine.TETile;
import byowTools.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world that contains RANDOM tiles.
 *
 * This class demonstrates how to fill a 2D world with randomly chosen tiles
 * and render it using the TERenderer.
 */
public class RandomWorldDemo {
    private static final int WIDTH = 50;  // Width of the world
    private static final int HEIGHT = 50; // Height of the world

    private static final long SEED = 2873123; // Seed for random number generator
    private static final Random RANDOM = new Random(SEED); // Random number generator

    public static void main(String[] args) {
        // Initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // Initialize the world with random tiles
        TETile[][] randomTiles = createRandomWorld(WIDTH, HEIGHT);

        // Render the random world
        ter.renderFrame(randomTiles);
    }

    /**
     * Creates a world filled with randomly chosen tiles.
     *
     * @param width The width of the world.
     * @param height The height of the world.
     * @return A 2D array representing the world with random tiles.
     */
    private static TETile[][] createRandomWorld(int width, int height) {
        TETile[][] world = new TETile[width][height];
        fillWithRandomTiles(world);
        return world;
    }

    /**
     * Fills the given 2D array of tiles with random tiles.
     *
     * @param tiles The 2D array to be filled with tiles.
     */
    private static void fillWithRandomTiles(TETile[][] tiles) {
        int width = tiles.length;
        int height = tiles[0].length;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = chooseRandomTile();
            }
        }
    }

    /**
     * Chooses a random tile with equal probability of being a wall, flower, or empty space.
     *
     * @return A randomly chosen tile.
     */
    private static TETile chooseRandomTile() {
        int tileNum = RANDOM.nextInt(3);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.NOTHING;
            default: return Tileset.NOTHING;
        }
    }
}
