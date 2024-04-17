package configuration;

public interface ConfigurationInterface {

    void initializeTable();
    void printTable();
    void initializeRandomMineAfterTableGeneration(int mineCounter, int row, int col);

}
