package com.nitroclient;

import java.util.*;

public class ModuleManager {
    public static class Mod {
        public final String name, category;
        public boolean enabled;
        public Mod(String name, String category, boolean enabled) {
            this.name = name; this.category = category; this.enabled = enabled;
        }
    }
    private final List<Mod> modules = new ArrayList<>();
    public void registerDefaults() {
        modules.clear();
        modules.add(new Mod("Anchor Macro", "COMBAT", false));
        modules.add(new Mod("Crystal Optimizer", "COMBAT", false));
        modules.add(new Mod("Auto Totem", "COMBAT", false));
        modules.add(new Mod("Hover Totem", "COMBAT", false));
        modules.add(new Mod("Auto Inv Totem", "COMBAT", false));
        modules.add(new Mod("Storage ESP", "RENDER", false));
        modules.add(new Mod("Player ESP", "RENDER", false));
        modules.add(new Mod("Free Cam", "RENDER", false));
        modules.add(new Mod("OreSim", "RENDER", false));
        modules.add(new Mod("HUD", "CLIENT", true));
    }
    public List<Mod> all() { return Collections.unmodifiableList(modules); }
    public List<Mod> category(String category) {
        return modules.stream().filter(m -> m.category.equals(category)).toList();
    }
}
