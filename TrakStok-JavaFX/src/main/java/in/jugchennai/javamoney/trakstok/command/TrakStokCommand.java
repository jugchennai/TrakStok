package in.jugchennai.javamoney.trakstok.command;

import in.jugchennai.javamoney.trakstok.beans.Page;
import in.jugchennai.javamoney.trakstok.ui.TSWaves;
import org.jrebirth.core.command.DefaultCommand;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>TrakStokCommand</strong> used to process short action into
 * the JRebirth Internal Thread.
 *
 * @author
 */
public final class TrakStokCommand extends DefaultCommand {

    /**
     * The class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TrakStokCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {
        // You must put your initialization code here (if any)
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void execute(final Wave wave) {
        // You must put your processing code here
        LOGGER.info("TrakStokCommand.execute()");         
        sendWave(TSWaves.SHOW_PAGE, WaveData.build(TSWaves.PAGE, Page.LOGIN));
    }
}
