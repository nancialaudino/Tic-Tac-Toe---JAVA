package model;

public class Cell {
    private Player owner; // null if it's free

    public Cell() {
        this.owner = null;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player player) {
        this.owner = player;
    }

    /**
     * Method to check if the cell is empty.
     *
     */

    public boolean isEmpty() {
        return this.owner == null;
    }


    public String getRepresentation() {
        if (owner == null) return "   ";
        return owner.getRepresentation();
    }
}
