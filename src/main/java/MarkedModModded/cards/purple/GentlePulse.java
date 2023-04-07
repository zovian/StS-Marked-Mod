package MarkedModModded.cards.purple;

import MarkedModModded.MarkedModModded;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import MarkedModModded.abstracts.AbstractMarkedCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static MarkedModModded.MarkedModModded.makeCardPath;

// ZOEY: Retains by default, cost 1; upgrade reduces cost to 0
// ZOEY: OR retains by default, cost 0; upgrade draws a card

public class GentlePulse
        extends AbstractMarkedCard {

    public static final String ID = MarkedModModded.makeID(GentlePulse.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = GentlePulse.class.getSimpleName();


    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;

    private static final int COST = 0;


    public GentlePulse() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.exhaust = true;
        this.selfRetain = true;
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();

            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    @Override
    public void use(AbstractPlayer player, AbstractMonster monster) {
        triggerMarks();

        if (upgraded) {
            this.addToBot(new DrawCardAction(player, 1));
        }
    }
}
