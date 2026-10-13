package com.nitroclient;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import java.util.*;

public class NitroScreen extends Screen {
    private static final int PANEL = 0xEE0B0E12, BORDER = 0xFFE8E8E8, GREEN = 0xFF37FF88;
    private final Map<String, Integer> scroll = new HashMap<>();
    private int mouseX, mouseY;

    public NitroScreen() { super(Text.literal("Nitro Client")); }

    @Override public void render(DrawContext ctx, int mx, int my, float delta) {
        this.mouseX = mx; this.mouseY = my;
        renderBackground(ctx, mx, my, delta);
        int totalW = 510, x0 = (width-totalW)/2, y = Math.max(20, (height-168)/2);
        ctx.fill(x0-12, y-16, x0+totalW+12, y+167, 0xEE080B10);
        panel(ctx, x0, y, 177, "COMBAT", "COMBAT");
        panel(ctx, x0+188, y, 177, "RENDER", "RENDER");
        panel(ctx, x0+376, y, 135, "CLIENT", "CLIENT");
        ctx.drawText(textRenderer, "NITRO CLIENT", x0, y-12, GREEN, false);
        ctx.drawText(textRenderer, "RIGHT SHIFT: CLOSE / OPEN", x0+350, y-12, 0xFF9AA3AE, false);
        super.render(ctx, mx, my, delta);
    }

    private void panel(DrawContext ctx, int x, int y, int w, String title, String category) {
        List<ModuleManager.Mod> mods = NitroClient.MODULES.category(category);
        int h = 29 + mods.size()*27 + (category.equals("CLIENT") ? 38 : 0);
        ctx.fill(x, y, x+w, y+h, PANEL);
        ctx.fill(x, y, x+w, y+1, BORDER); ctx.fill(x, y+h-1, x+w, y+h, BORDER);
        ctx.fill(x, y, x+1, y+h, BORDER); ctx.fill(x+w-1, y, x+w, y+h, BORDER);
        ctx.drawCenteredTextWithShadow(textRenderer, title, x+w/2, y+10, GREEN);
        ctx.fill(x+1, y+28, x+w-1, y+29, 0xFF343A43);
        int rowY = y+31;
        for (ModuleManager.Mod mod : mods) {
            ctx.drawText(textRenderer, mod.name.toUpperCase(Locale.ROOT), x+10, rowY+7, 0xFFF1F4F8, false);
            int sx = x+w-30, sy = rowY+5;
            ctx.fill(sx, sy, sx+20, sy+11, mod.enabled ? 0xFF38F78A : 0xFF303641);
            ctx.fill(mod.enabled ? sx+10 : sx+2, sy+2, mod.enabled ? sx+18 : sx+10, sy+9, 0xFFF6F7F8);
            rowY += 27;
        }
        if (category.equals("CLIENT")) {
            int cy = rowY+2;
            String[] colors = {"#54D7FF", "#38F78A", "#FF62C4", "#FFB23F"};
            int[] cols = {0xFF54D7FF, 0xFF38F78A, 0xFFFF62C4, 0xFFFFB23F};
            for (int i=0;i<4;i++) {
                int cx=x+24+i*24;
                ctx.fill(cx, cy, cx+16, cy+16, cols[i]);
                ctx.fill(cx, cy, cx+16, cy+1, 0xFFFFFFFF);
            }
        }
    }

    @Override public boolean mouseClicked(double mx, double my, int button) {
        int totalW=510, x0=(width-totalW)/2, y=Math.max(20,(height-168)/2);
        if (button==0) {
            if (clickPanel(mx,my,x0,y,177,"COMBAT") ||
                clickPanel(mx,my,x0+188,y,177,"RENDER") ||
                clickPanel(mx,my,x0+376,y,135,"CLIENT")) return true;
        }
        return super.mouseClicked(mx,my,button);
    }

    private boolean clickPanel(double mx,double my,int x,int y,int w,String category) {
        List<ModuleManager.Mod> mods=NitroClient.MODULES.category(category);
        int rowY=y+31;
        for (ModuleManager.Mod mod:mods) {
            if (mx>=x && mx<=x+w && my>=rowY && my<rowY+27) {
                mod.enabled=!mod.enabled;
                return true;
            }
            rowY+=27;
        }
        return false;
    }

    @Override public boolean shouldPause() { return false; }
}
