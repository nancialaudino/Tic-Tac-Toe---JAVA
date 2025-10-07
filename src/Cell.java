public class Cell {
    private Player owner; // null si libre

    public Cell() {
        this.owner = null;
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
