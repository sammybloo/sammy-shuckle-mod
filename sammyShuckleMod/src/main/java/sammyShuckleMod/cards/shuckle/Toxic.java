package sammyShuckleMod.cards.shuckle;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import sammyShuckleMod.DefaultMod;
import sammyShuckleMod.cards.AbstractDynamicCard;
import sammyShuckleMod.characters.Shuckle;
import sammyShuckleMod.characters.TheDefault;

import static sammyShuckleMod.DefaultMod.makeCardPath;

public class Toxic extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Toxic.class.getSimpleName());
    public static final String IMG = makeCardPath("toxic.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Shuckle.Enums.COLOR_YELLOW;

    private static final int COST = 0;

    private static final int POISON = 3;
    private static final int UPGRADE_PLUS_POISON = 2;

    // /STAT DECLARATION/

    public Toxic() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        magicNumber = baseMagicNumber = POISON;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new PoisonPower(m, p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(), 1));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_POISON);
            this.initializeDescription();
        }
    }
}