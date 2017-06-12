package com.Innopolis.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 08.06.2017.
 */

public class Group {
    private Long id;
    private String groupName;
    private List<Student> groupList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

   /* public List<Student> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Student> groupList) {
        this.groupList = groupList;
    }*/

    public Group(Integer id, String groupName, List<Student> groupList) {
        this.id=System.currentTimeMillis();
        this.groupName = groupName;
        this.groupList = new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Group)) return false;
        if (this.id != ((Group) obj).getId()) return false;
        return true;

    }

    @Override
    public int hashCode() {
        return (21+groupName.hashCode()*41)+(21+id.hashCode()*41);
    }
}

