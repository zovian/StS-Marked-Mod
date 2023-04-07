package MarkedModModded.powers.watcher;

import MarkedModModded.MarkedModModded;
import MarkedModModded.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import com.megacrit.cardcrawl.vfx.combat.PressurePointEffect;

import static MarkedModModded.MarkedModModded.makePowerPath;


public class ApplyMarkOnAttackedPower
        extends AbstractPower implements CloneablePowerInterface {
    public static final String POWER_ID = MarkedModModded.makeID(ApplyMarkOnAttackedPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("yang_84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("yang_32.png"));


    public ApplyMarkOnAttackedPower(AbstractCreature owner, int newAmount) {

        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = newAmount;

        this.type = PowerType.BUFF;
        this.isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }


    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        AbstractCreature source = info.owner;

        if (source != this.owner && info.type == DamageInfo.DamageType.NORMAL)
        {

            this.flash();
            this.addToTop(new VFXAction(new PressurePointEffect(source.hb.cX, source.hb.cY)));
            this.addToTop(new ApplyPowerAction(source, this.owner, new MarkPower(source, this.amount), this.amount));
        }

        return damageAmount;
    }


    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }


    @Override
    public AbstractPower makeCopy() {
        return new ApplyMarkOnAttackedPower(this.owner, this.amount);
    }
}
