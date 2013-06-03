package in.jugchennai.javamoney.trakstok.ui;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>TrakStokController</strong>.
 * 
 * @author
 */
public class TrakStokController extends AbstractController<TrakStokModel, TrakStokView> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TrakStokController.class);

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public TrakStokController(final TrakStokView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeEventAdapters() throws CoreException {
        // Attach event adapters
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeEventHandlers() throws CoreException {
        // Listen events
    }

}