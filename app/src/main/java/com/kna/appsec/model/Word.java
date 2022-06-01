package com.kna.appsec.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.tsolution.base.BaseModel;

@IgnoreExtraProperties
public class Word extends BaseModel {
    private String name;
    private String linkimage;
    private String meaning;
    private String pronounce;
    private String wordtype;

    public Word() {
    }

    public Word(String name, String linkimage, String meaning, String pronounce, String wordtype) {
        this.name = name;
        this.linkimage = linkimage;
        this.meaning = meaning;
        this.pronounce = pronounce;
        this.wordtype = wordtype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkimage() {
        return linkimage;
    }

    public void setLinkimage(String linkimage) {
        this.linkimage = linkimage;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getPronounce() {
        return pronounce;
    }

    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
    }

    public String getWordtype() {
        return wordtype;
    }

    public void setWordtype(String wordtype) {
        this.wordtype = wordtype;
    }

    @Override
    public String toString() {
        return "Word{" +
                "name='" + name + '\'' +
                ", linkimage='" + linkimage + '\'' +
                ", meaning='" + meaning + '\'' +
                ", pronounce='" + pronounce + '\'' +
                ", wordtype='" + wordtype + '\'' +
                '}';
    }
}
