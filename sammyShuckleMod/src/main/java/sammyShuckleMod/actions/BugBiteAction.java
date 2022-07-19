package sammyShuckleMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class BugBiteAction extends AbstractGameAction {
    private int increaseStrengthAmount;
    private DamageInfo info;
    private static final float DURATION = 0.1F;

    public BugBiteAction(AbstractCreature target, AbstractCreature owner, DamageInfo info, int increaseStrengthAmount) {

        this.info = info;
        this.setValues(target, owner);
        this.actionType = ActionType.DAMAGE;
        this.duration = 0.1F;
        this.increaseStrengthAmount = increaseStrengthAmount;
    }

    public void update() {
        if (this.duration == 0.1F && this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.NONE));
            this.target.damage(this.info);
            if ((((AbstractMonster)this.target).lastDamageTaken > 0)) {
                AbstractDungeon.actionManager.addToTop(
                        new ApplyPowerAction(source, source, new StrengthPower(source, increaseStrengthAmount)));
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }

        this.tickDuration();
    }
}