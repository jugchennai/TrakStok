package in.jugchennai.javamoney.trakstok.ui;

import org.jrebirth.core.ui.AbstractModel;
import org.jrebirth.core.wave.Wave;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>TrakStokModel</strong>.
 * 
 * @author
 */
public class TrakStokModel extends AbstractModel<TrakStokModel, TrakStokView> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TrakStokModel.class);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitialize() {
        // Put the code to initialize your model here
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeInnerModels() {
        // Put the code to initialize inner models here (if any)
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processAction(final Wave wave) {
        // Process a wave action, you must listen the wave type before
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customShowView() {
        // Custom code to process when the view is displayed
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customHideView() {
        // Custom code to process when the view is hidden
    }

}
