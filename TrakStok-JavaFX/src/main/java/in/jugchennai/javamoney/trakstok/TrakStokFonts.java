package in.jugchennai.javamoney.trakstok;

import javafx.scene.text.Font;

import org.jrebirth.core.resource.ResourceBuilders;
import org.jrebirth.core.resource.font.FontBuilder;
import org.jrebirth.core.resource.font.FontItem;
import org.jrebirth.core.resource.font.FontParams;
import org.jrebirth.core.resource.font.RealFont;

/**
 * The class <strong>TrakStokFonts</strong>.
 * 
 * @author
 */
public enum TrakStokFonts implements FontItem {

    /** The splash font. */
    SPLASH(new RealFont(TrakStokFontsLoader.DINk, 24));

    /**
     * Default Constructor.
     * 
     * @param fontParams the font size
     */
    TrakStokFonts(final FontParams fontParams) {
        builder().storeParams(this, fontParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Font get() {
        return builder().get(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FontBuilder builder() {
        return ResourceBuilders.FONT_BUILDER;
    }

}
