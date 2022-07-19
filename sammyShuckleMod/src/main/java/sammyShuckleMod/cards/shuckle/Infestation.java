package sammyShuckleMod.cards.shuckle;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import sammyShuckleMod.DefaultMod;
import sammyShuckleMod.cards.AbstractDynamicCard;
import sammyShuckleMod.characters.Shuckle;

import static sammyShuckleMod.DefaultMod.makeCardPath;

public class Infestation extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Infestation.class.getSimpleName());
    public static final String IMG = makeCardPath("toxic.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Shuckle.Enums.COLOR_YELLOW;

    private static final int COST = 1;

    private static final int WEAK_AMOUNT = 1;
    private static final int VULNERABLE_AMOUNT = 1;
    private static final int UPGRADE_PLUS_WEAK = 1;
    private static final int UPGRADE_PLUS_VULNERABLE = 1;

    // /STAT DECLARATION/

    public Infestation() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        magicNumber = baseMagicNumber = WEAK_AMOUNT;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = VULNERABLE_AMOUNT;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.defaultSecondMagicNumber));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_WEAK);
            this.upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_VULNERABLE);
            this.initializeDescription();
        }
    }
}