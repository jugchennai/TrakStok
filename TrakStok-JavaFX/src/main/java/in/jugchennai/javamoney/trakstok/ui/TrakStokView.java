package in.jugchennai.javamoney.trakstok.ui;

import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.BorderPane;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.AbstractView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>TrakStokView</strong>.
 * 
 * @author
 */
public class TrakStokView extends AbstractView<TrakStokModel, BorderPane, TrakStokController> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TrakStokView.class);

    /**
     * Default Constructor.
     * 
     * @param model the controls view model
     * 
     * @throws CoreException if build fails
     */
    public TrakStokView(final TrakStokModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeComponents() {
        getRootNode().setCenter(
                LabelBuilder.create()
                        .text("JRebirth Sample")
                        .build()
                );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doStart() {
        // Custom code to process when the view is displayed the first time
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doReload() {
        // Custom code to process when the view is displayed the 1+n time
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doHide() {
        // Custom code to process when the view is hidden
    }

}