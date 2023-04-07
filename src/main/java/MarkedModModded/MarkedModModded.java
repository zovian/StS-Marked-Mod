package MarkedModModded;

import MarkedModModded.cards.colorless.Needle;
import MarkedModModded.cards.purple.*;

/* ZOEY: Not using InfiniteSpire, do not import (missing dependence errors)

import MarkedModModded.crossover.InfiniteSpire;

*/ 

import MarkedModModded.potions.watcher.BlackLotusJuice;
import MarkedModModded.relics.AcupunctureKit;
import MarkedModModded.stances.DanceOfDeathStance;
import MarkedModModded.util.TextureLoader;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;

import static MarkedModModded.util.TextureLoader.loadTexture;


@SuppressWarnings("SpellCheckingInspection")
@SpireInitializer
public class MarkedModModded
        implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        PreStartGameSubscriber,
        PostInitializeSubscriber {
    public static final Logger logger = LogManager.getLogger(MarkedModModded.class.getName());

    private static final String modID = "MarkedModModded";
    private static final String MODNAME = "Marked Mod Modded";
    private static final String AUTHOR = "torrentails, ed. Zoey";
    private static final String DESCRIPTION = "Zoey went and bonked at the original Marked Mod and took a long time but maybe now she has her own version";
    public static final String BADGE_IMAGE = "MarkedModResources/images/Badge.png";

    public static boolean isInfiniteSpireLoaded = false;


    public static String checkPath(String path) {
        return checkPath(path, getModID() + "Resources/image/ui/missing_texture.png");
    }


    public static String checkPath(String path, String defaultPath) {
        String retVal = defaultPath != null ? defaultPath : getModID() + "Resources/image/ui/missing_texture.png";

        try {
            loadTexture(path);
            retVal = path;
        } catch (GdxRuntimeException e) {
            logger.info("Could not find texture: " + path);
        }

        return retVal;
    }


    public static String makeCardPath(String path) {
        return checkPath(getModID() + "Resources/images/cards/" + path, getModID() + "Resources/images/cards/beta/beta_img.png");
    }
    
    public static String makeRelicPath(String path) {
        // return getModID() + "Resources/images/relics/" + path;
        return checkPath(getModID() + "Resources/images/relics/" + path);
    }
    
    public static String makeRelicOutlinePath(String path) {
        // return getModID() + "Resources/images/relics/outline/" + path;
        return checkPath(getModID() + "Resources/images/relics/outline/" + path);
    }
    
    public static String makePowerPath(String path) {
        // return getModID() + "Resources/images/powers/" + path;
        return checkPath(getModID() + "Resources/images/powers/" + path);
    }

    
    public MarkedModModded() {
        logger.info("Subscribe to BaseMod hooks");
        BaseMod.subscribe(this);
    }


    public static String getModID() {
        return modID;
    }


    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }
    
    
    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("Initializing " + MODNAME);

        MarkedModModded markedModModded = new MarkedModModded();
        if (Loader.isModLoaded("infinitespire")) {
            MarkedModModded.isInfiniteSpireLoaded = true;
            logger.info("Infinite Spire is loaded");
        }

        logger.info(MODNAME + " Initialized");
    }

    
    @Override
    public void receivePostInitialize() {
        logger.info("Loading badge image");

        BaseMod.registerModBadge(TextureLoader.getTexture(BADGE_IMAGE), MODNAME, AUTHOR, DESCRIPTION, null);
    }

    
    public void receiveEditPotions() {
        logger.info("Adding potions");

        BaseMod.addPotion(BlackLotusJuice.class,
                          BlackLotusJuice.COLOR_GAS,
                          BlackLotusJuice.COLOR_LIQUID,
                          BlackLotusJuice.COLOR_SPOTS,
                          BlackLotusJuice.POTION_ID,
                          AbstractPlayer.PlayerClass.WATCHER);
    }

    
    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");

        BaseMod.addRelic(new AcupunctureKit(), RelicType.PURPLE);
        UnlockTracker.markRelicAsSeen(AcupunctureKit.ID);

        // I feel like this should go somewhere else but, it works so... meh ¯\_(ツ)_/¯
        receiveEditPotions();
    }

    
    @Override
    public void receiveEditCards() {
        logger.info("Adding cards");


// ZOEY: THESE CARDS DISABLED. THEY WILL NOT APPEAR IN GAME. <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
//        BaseMod.addCard(new Acupuncture());
//        BaseMod.addCard(new FirstStrike());

        BaseMod.addCard(new GentlePulse());
        BaseMod.addCard(new OneThousandNeedles());
        BaseMod.addCard(new PinPointDefense());
        BaseMod.addCard(new GracefulMovements());
        BaseMod.addCard(new NorthStar());
        BaseMod.addCard(new SlowDance());
        BaseMod.addCard(new ChiBlock());

        BaseMod.addCard(new Needle());

//        UnlockTracker.unlockCard(Acupuncture.ID);
//        UnlockTracker.unlockCard(FirstStrike.ID);

        UnlockTracker.unlockCard(GentlePulse.ID);
        UnlockTracker.unlockCard(OneThousandNeedles.ID);
        UnlockTracker.unlockCard(PinPointDefense.ID);
        UnlockTracker.unlockCard(GracefulMovements.ID);
        UnlockTracker.unlockCard(NorthStar.ID);
        UnlockTracker.unlockCard(SlowDance.ID);
        UnlockTracker.unlockCard(ChiBlock.ID);

        UnlockTracker.unlockCard(Needle.ID);
		
		/* ZOEY: InfiniteSpire not imported, so calling InfiniteSpire.anything errors
        if (MarkedModModded.isInfiniteSpireLoaded) {
            InfiniteSpire.loadWatcherBlackCards();
        }
		*/
    }

    
    @Override
    public void receiveEditStrings() {
        logger.info("Begin editing strings");
        String jsonPath = getModID() + "Resources/localization/";
        String langCode = Settings.language.toString().toLowerCase();

        switch (langCode) {
            case "eng":
            case "tha":
            case "zhs":
                jsonPath = jsonPath + langCode;
                break;

            default:
                jsonPath = jsonPath + "eng";
        }

        BaseMod.loadCustomStringsFile(
                CardStrings.class,
                jsonPath + "/Card-Strings.json");

        BaseMod.loadCustomStringsFile(
                PowerStrings.class,
                jsonPath + "/Power-Strings.json");

        BaseMod.loadCustomStringsFile(
                RelicStrings.class,
                jsonPath + "/Relic-Strings.json");

        BaseMod.loadCustomStringsFile(
                PotionStrings.class,
                jsonPath + "/Potion-Strings.json");

        BaseMod.loadCustomStringsFile(
                StanceStrings.class,
                jsonPath + "/Stance-Strings.json");
    }

    
    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String path = getModID() + "Resources/localization/" + Settings.language.toString().toLowerCase();

        String json = Gdx.files.internal(path + "/Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);
        
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                String description = keyword.DESCRIPTION;
                for (String name : keyword.NAMES) {
                    if (name.equals(DanceOfDeathStance.NAME)) {
                        description = DanceOfDeathStance.DESCRIPTIONS[0];
                        break;
                    }
                }
rt
                BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, description);
            }
        }
    }


    @Override
    public void receivePreStartGame() {
        
		/* ZOEY: InfiniteSpire not imported, so calling InfiniteSpire.anything errors
		if (MarkedModModded.isInfiniteSpireLoaded) {
            InfiniteSpire.removeWatcherBlackCards();
        }
		*/
    }
}
