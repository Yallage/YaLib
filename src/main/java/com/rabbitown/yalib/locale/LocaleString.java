package com.rabbitown.yalib.locale;

import org.bukkit.permissions.ServerOperator;

/**
 * 使用{@link Locale Locale}的可翻译文本
 */
public class LocaleString {
    public String text;
    public String[] paramList;

    /**
     * 构建一个可翻译的字符串
     *
     * @param text 可翻译字符串的Key
     * @param paramList 可翻译字符串的参数
     */
    public LocaleString(String text, String... paramList) {
        this.text = text;
        this.paramList = paramList;
    }

    /**
     * 为某名玩家获取该文本
     *
     * @param player 玩家
     * @return 翻译后的文本
     */
    public String getString(ServerOperator player) {
        return YLocale.getMessage(player,text,paramList);
    }

    /**
     * 获取某种语言下的该文本
     *
     * @param language 语言
     * @return 翻译后的文本
     */
    public String getString(String language) {
        return YLocale.getMessage(language,text,paramList);
    }
}
