
// Created for CS1331 at Georgia Tech
// By Allison Sawyer
// Spring 2019



/**
 * An Inventory.
 * @author jdierberger3 and asawyer8
 * @version 1331.
 */
public class Inventory {

    // TODO : Add an Item[] instance variable called contents.
    private Item[] contents = new Item[4];
    // Initialize it at declaration; give it a default length of 4.

    // Merge two inventorys, merging other into this inventory.
    public void merge(Inventory other) {
        // TODO : Compute contents.length + other.contents.length
        int newSize = contents.length + other.contents.length;
        // TODO : Create a new Item[] of the size above
        Item[] newContents = new Item[newSize];

        for (int j = 0; j < contents.length; j++) {
        // TODO : Copy all the elements from contents into the new Item[]
            newContents[j] = contents[j];
        }
        for (int j = contents.length; j < newSize; j++) {
            newContents[j] = other.contents[j - contents.length];
        }
        // TODO : Copy all the elements from other.contents into the new Item[]

        contents = newContents;
    // TODO : Set contents to be the new Item[] we just filled
    }

    // Get the i-th item, where the 0th item is the first.
    // Return null if the index is invalid.
    public Item getItem(int i) {
        // TODO : Check if "i" is a bad index into our array
            // If so, return null
        if (i < 0 || i > contents.length - 1) {
            return null;
        } else {
            return contents[i];
        }
        // TODO : Otherwise, return the element at index i

    }

    // Put an item at the i-th position, where the 0th item is the first.
    // Return false if the index is invalid. Otherwise return true.
    public boolean putItem(int i, Item item) {
        // TODO : Check if "i" is a bad index into our array
        // If so, return false
        // TODO : Otherwise, set the element at index i to item
        if (i < 0 || i > contents.length - 1) {
            return false;
        } else {
            contents[i] = item;
            return true;
        }
    }

    // Get the length of contents.
    public int getContentsLength() {
        // TODO : Return the length of contents.
        return contents.length;
    }

}
