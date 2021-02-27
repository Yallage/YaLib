package com.rabbitown.yalib.module.locale;

import com.rabbitown.yalib.YaLibCentral;
import com.rabbitown.yalib.module.locale.impl.SimpleLocale;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Represents an I18NPlugin.
 *
 * @author Milkory
 */
public interface I18NPlugin extends Plugin {

    /** When registering plugin to {@link YaLibCentral},
     * this method will be invoked and create a new {@link ILocale} to be stored. */
    @NotNull
    default ILocale getNewLocale() {
        return new SimpleLocale.Builder(this).build();
    }

    /** Returns the locale instance of this plugin.<p>
     *  You are <strong>not supported</strong> to override this method. */
    @NotNull
    default ILocale getLocale() {
        return Objects.requireNonNull(LocaleManager.INSTANCE.getLocale(this));
    }

}
