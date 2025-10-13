package model;

public class Cell {
    private Player owner; // null if its free

    public Cell() {
        this.owner = null;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player player) {
        this.owner = player;
    }

    public boolean isEmpty() {
        return this.owner == null;
    }


    public String getRepresentation() {
        if (owner == null) return "   ";
        return owner.getRepresentation();
    }
}
