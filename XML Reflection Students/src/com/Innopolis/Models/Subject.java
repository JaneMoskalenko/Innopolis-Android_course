package com.Innopolis.Models;

/**
 * Created by admin on 08.06.2017.
 */
public class Subject {
    private Long id;
    private String sublectName;

    public Long getId() {
        return id;
    }

    public String getSublectName() {
        return sublectName;
    }

    public void setSublectName(String sublectName) {
        this.sublectName = sublectName;
    }

    public Subject(Long id, String sublectName) {
        this.id=System.currentTimeMillis();
        this.sublectName = sublectName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Subject)) return false;
        if (this.id != ((Subject) obj).getId()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return (21+id.hashCode()*41);
    }

}
