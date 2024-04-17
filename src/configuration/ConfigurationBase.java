package configuration;

public abstract class ConfigurationBase implements ConfigurationInterface {

    private Table table;
    private Table[][] gameTable;

    private boolean isGameOver;
    public ConfigurationBase() {
        this.table = new Table();
        this.gameTable = new Table[getTable().getRowSize()][getTable().getColSize()];
    }

    public void addTableToGameTable(Table table, int row, int col) {
        this.gameTable[row][col] = table;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Table[][] getGameTable() {
        return gameTable;
    }

    public void setGameTable(Table[][] gameTable) {
        this.gameTable = gameTable;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }
}
