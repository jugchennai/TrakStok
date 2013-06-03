package in.jugchennai.javamoney.trakstok;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.jrebirth.core.application.AbstractApplication;
import org.jrebirth.core.resource.font.FontItem;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.Wave;
import in.jugchennai.javamoney.trakstok.ui.TrakStokModel;

/**
 * The class <strong>TrakStokApplication</strong>.
 * 
 * @author
 */
public final class TrakStokApplication extends AbstractApplication<StackPane> {

    /**
     * Application launcher.
     * 
     * @param args the command line arguments
     */
    public static void main(final String... args) {
        Application.launch(TrakStokApplication.class, args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Model> getFirstModelClass() {
        return TrakStokModel.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getApplicationTitle() {
        return "TrakStok";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customizeStage(final Stage stage) {
        stage.setFullScreen(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customizeScene(final Scene scene) {
       //System.out.println ("*************SORUCE URL "+this.getClass().getResource("style/sample.css").toString());
        scene.getStylesheets().add(this.getClass().getResource("css/trakStokFx.css").toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FontItem> getFontToPreload() {
        return Arrays.asList(new FontItem[] {
                TrakStokFonts.SPLASH
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Wave> getPreBootWaveList() {
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Wave> getPostBootWaveList() {
        return Collections.emptyList();
    }
    
}
