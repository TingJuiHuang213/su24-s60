package byowTools.TileEngine;

import java.awt.Color;

/**
 * Contains constant tile objects, to avoid having to remake the same tiles in different parts of
 * the code.
 *
 * You are free to (and encouraged to) create and add your own tiles to this file. This file will
 * be turned in with the rest of your code.
 *
 * Example usage:
 *      world[x][y] = Tileset.FLOOR;
 *
 * The style checker may crash when you try to style check this file due to use of unicode
 * characters. This is OK.
 */
public class Tileset {
    // Tile representing the player's avatar
    public static final TETile AVATAR = createTile('@', Color.white, Color.black, "you");

    // Tile representing a wall
    public static final TETile WALL = createTile('#', new Color(216, 128, 128), Color.darkGray, "wall");

    // Tile representing a floor
    public static final TETile FLOOR = createTile('·', new Color(128, 192, 128), Color.black, "floor");

    // Tile representing empty space
    public static final TETile NOTHING = createTile(' ', Color.black, Color.black, "nothing");

    // Tile representing grass
    public static final TETile GRASS = createTile('"', Color.green, Color.black, "grass");

    // Tile representing water
    public static final TETile WATER = createTile('≈', Color.blue, Color.black, "water");

    // Tile representing a flower
    public static final TETile FLOWER = createTile('❀', Color.magenta, Color.pink, "flower");

    // Tile representing a locked door
    public static final TETile LOCKED_DOOR = createTile('█', Color.orange, Color.black, "locked door");

    // Tile representing an unlocked door
    public static final TETile UNLOCKED_DOOR = createTile('▢', Color.orange, Color.black, "unlocked door");

    // Tile representing sand
    public static final TETile SAND = createTile('▒', Color.yellow, Color.black, "sand");

    // Tile representing a mountain
    public static final TETile MOUNTAIN = createTile('▲', Color.gray, Color.black, "mountain");

    // Tile representing a tree
    public static final TETile TREE = createTile('♠', Color.green, Color.black, "tree");

    /**
     * Helper method to create a TETile object with the specified parameters.
     *
     * @param character The character displayed on the screen.
     * @param textColor The color of the character itself.
     * @param backgroundColor The color drawn behind the character.
     * @param description The description of the tile, shown in the GUI on hovering over the tile.
     * @return A new TETile object with the specified parameters.
     */
    private static TETile createTile(char character, Color textColor, Color backgroundColor, String description) {
        return new TETile(character, textColor, backgroundColor, description);
    }
}
