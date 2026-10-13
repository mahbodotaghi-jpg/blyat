package com.nitroclient;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class NitroClient implements ClientModInitializer {
    public static final ModuleManager MODULES = new ModuleManager();
    private KeyBinding openMenuKey;

    @Override public void onInitializeClient() {
        MODULES.registerDefaults();
        openMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.nitroclient.open_menu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT,
            KeyBinding.Category.MISC));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openMenuKey.wasPressed()) client.setScreen(new NitroScreen());
        });
    }
}
