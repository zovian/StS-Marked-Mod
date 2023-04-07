package MarkedModModded.patches.vfx.stance;

import MarkedModModded.stances.DanceOfDeathStance;
import MarkedModModded.vfx.stance.DanceOfDeathStanceChangeParticle;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;

import java.lang.reflect.Field;


@SuppressWarnings("unused")
public class StanceChangeParticleGeneratorPatch {
    @SpirePatch(clz = StanceChangeParticleGenerator.class,
                method = "update")
    public static class update {

        public static SpireReturn<?> Prefix(StanceChangeParticleGenerator inst) {

            try {
                Field stanceIdField = inst.getClass().getDeclaredField("stanceId");
                stanceIdField.setAccessible(true);

                if (stanceIdField.get(inst).toString().equals(DanceOfDeathStance.STANCE_ID)) {

                    Field xField = inst.getClass().getDeclaredField("x");
                    xField.setAccessible(true);

                    for (int i = 0; i < 10; ++i) {
                        AbstractDungeon.effectsQueue.add(new DanceOfDeathStanceChangeParticle((Float) xField.get(inst)));
                    }

                    inst.isDone = true;

                    return SpireReturn.Return(null);

                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

            return SpireReturn.Continue();
        }
    }
}