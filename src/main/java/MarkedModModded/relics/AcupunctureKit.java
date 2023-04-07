package MarkedModModded.relics;

import MarkedModModded.MarkedModModded;
import MarkedModModded.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;

import static MarkedModModded.MarkedModModded.makeRelicOutlinePath;
import static MarkedModModded.MarkedModModded.makeRelicPath;


public class AcupunctureKit
        extends CustomRelic {
    public static final String ID = MarkedModModded.makeID(AcupunctureKit.class.getSimpleName());
    public static final RelicTier TIER = RelicTier.COMMON;
    public static final LandingSound SFX = LandingSound.CLINK;
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath(AcupunctureKit.class.getSimpleName() + ".png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath(AcupunctureKit.class.getSimpleName() + ".png"));


    public AcupunctureKit() {
        super(ID, IMG, OUTLINE, TIER, SFX);
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
