import configuration.Configuration;
import configuration.gameConfiguration.GameInterface;
import configuration.placementConfiguration.Placement;

public class Game implements GameInterface {

    private final Placement placement;
    private final Configuration configuration;

    public Game() {
        this.configuration = new Configuration();
        this.placement = new Placement(getConfiguration());
    }

    @Override
    public void startGame() {
        while (!getConfiguration().isGameOver()) {
            getConfiguration().printTable();
            getPlacement().askForPlacement();
            getConfiguration().setGameOver(getPlacement().getGameLogic().isGameOver());
        }
        getConfiguration().printTable();
    }

    public Placement getPlacement() {
        return placement;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

}
