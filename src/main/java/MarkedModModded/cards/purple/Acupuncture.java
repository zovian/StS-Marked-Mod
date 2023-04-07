package MarkedModModded.cards.purple;

import MarkedModModded.MarkedModModded;
import MarkedModModded.abstracts.AbstractMarkedCard;
import MarkedModModded.cards.colorless.Needle;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MarkedModModded.MarkedModModded.makeCardPath;

// ZOEY: DISABLED. WILL NOT APPEAR IN GAME. <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

// ZOEY: Rework or remove
// ZOEY: Mimic Cloak and Dagger?

public class Acupuncture extends AbstractMarkedCard {

    public static final String ID = MarkedModModded.makeID(Acupuncture.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = Acupuncture.class.getSimpleName();


    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int COST = 1;
    private static final int DAMAGE = 3;
    private static final int UPGRADE_PLUS_DMG = 2;


    public Acupuncture() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        baseDamage = DAMAGE;
        this.cardsToPreview = new Needle();
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }


    @Override
    public void use(AbstractPlayer player, AbstractMonster monster) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(monster,
                                 new DamageInfo(player, this.damage, damageTypeForTurn),
                                 AbstractGameAction.AttackEffect.LIGHTNING));

        this.addToBot(new MakeTempCardInHandAction(new Needle(), 1));
    }
}
