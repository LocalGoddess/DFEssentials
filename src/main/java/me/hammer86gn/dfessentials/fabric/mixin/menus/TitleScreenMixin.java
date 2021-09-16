package me.hammer86gn.dfessentials.fabric.mixin.menus;

import me.hammer86gn.dfessentials.DFE;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {

    @Shadow @Final private boolean doBackgroundFade;

    @Shadow private long backgroundFadeStart;

    private int textWidth;

    private int posX;
    private int posY;

    private String versionText = "DF Essentials: Version " + DFE.getInstance().getModVersion().toString();

    private MatrixStack matrices = new MatrixStack();

    private boolean inited = false;

    protected TitleScreenMixin() {
        super(new TranslatableText(""));

//        textWidth = this.textRenderer.getWidth(versionText);
//        posX = this.width - textWidth - 2;
//        posY = this.height;
//
//        matrices.push();
//        matrices.push();
    }

    @Inject(method = "render", at = @At("TAIL"), cancellable = false)
    public void render(CallbackInfo ci) {
        if (!inited) {

            textWidth = this.textRenderer.getWidth(versionText);
            posX = this.width - textWidth - 2;
            posY = this.height - 220;

            matrices.push();
            matrices.push();

            inited = true;
        }

        float fadeClamp = this.doBackgroundFade ? (float)(Util.getMeasuringTimeMs() - this.backgroundFadeStart) / 1000.0F : 1.0F;
        float backgroundFadeAmount = this.doBackgroundFade ? MathHelper.clamp(fadeClamp - 1.0F, 0.0F, 1.0F) : 1.0F;
        int textColorModifier = MathHelper.ceil(backgroundFadeAmount * 255.0F) << 24;

        if (matrices.isEmpty()) {
            DFE.getInstance().getModLogger().warn("Yeah its empty");
        }

        drawStringWithShadow(this.matrices, this.textRenderer, this.versionText,
                2, this.height - 19, 16777215 | textColorModifier);

        DFE.getInstance().getModLogger().info(
                "[DFE] [TitleScreen] Drawing Text:\n" +
                    "Matrices: " + matrices.peek().getModel().toString() + "\n" +
                    "Empty: " + matrices.isEmpty() + "\n" +
                    "PosX: " + this.posX + "\n" +
                    "PosY: " + this.posY + "\n" +
                    "Text: " + this.versionText + "\n" +
                    "Font Height: " + this.textRenderer.fontHeight
        );
    }
}
