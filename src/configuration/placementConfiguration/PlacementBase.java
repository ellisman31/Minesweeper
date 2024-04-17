package configuration.placementConfiguration;

import configuration.Configuration;
import configuration.gameConfiguration.GameLogic;

public abstract class PlacementBase implements PlacementInterface {

    private final Configuration configuration;
    private final GameLogic gameLogic;
    private boolean isValidInput;
    private final String rowPlacementName;
    private final String colPlacementName;
    private int rowPlacement;
    private int colPlacement;
    private String placementName;
    private boolean isValidPlacementName;
    protected static final int PLACEMENT_CORRECTOR = 1;

    public PlacementBase(Configuration configuration) {
        this.configuration = configuration;
        this.gameLogic = new GameLogic(configuration);
        this.isValidInput = false;
        this.rowPlacementName = "row";
        this.colPlacementName = "col";
        this.isValidPlacementName = false;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }

    public boolean isValidInput() {
        return isValidInput;
    }

    public void setValidInput(boolean validInput) {
        isValidInput = validInput;
    }

    public String getRowPlacementName() {
        return rowPlacementName;
    }

    public String getColPlacementName() {
        return colPlacementName;
    }

    public int getRowPlacement() {
        return rowPlacement;
    }

    public void setRowPlacement(int rowPlacement) {
        this.rowPlacement = rowPlacement;
    }

    public int getColPlacement() {
        return colPlacement;
    }

    public void setColPlacement(int colPlacement) {
        this.colPlacement = colPlacement;
    }

    public String getPlacementName() {
        return placementName;
    }

    public void setPlacementName(String placementName) {
        this.placementName = placementName;
    }

    public boolean isValidPlacementName() {
        return isValidPlacementName;
    }

    public void setValidPlacementName(boolean validPlacementName) {
        isValidPlacementName = validPlacementName;
    }
}
