package com.Innopolis.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 08.06.2017.
 */
public class Lesson {
    private Long id;
    private String name;
    private Date dateBegin;
    private Date dateEnd;
    private String auditorium;
    private String description;
    private Long subjectId;
    private String lector;
    private List<Long> groupId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(String auditorium) {
        this.auditorium = auditorium;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getLector() {
        return lector;
    }

    public void setLector(String lector) {
        this.lector = lector;
    }

    public Long getId() {
        return id;
    }

   /* public List<Long> getGroupId() {
        return groupId;
    }

    public void setGroupId(List<Long> groupId) {
        this.groupId = groupId;
    }
    */

    public Lesson(Long id, String name, Date dateBegin, Date dateEnd, String auditorium, String description, Long subjectId, String lector, List<Long> groupId) {
        this.id=System.currentTimeMillis();
        this.name = name;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.auditorium = auditorium;
        this.description = description;
        this.subjectId = subjectId;
        this.lector = lector;
        this.groupId = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        return (int) (21+id*42);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Lesson)) return false;
        if (this.id != ((Lesson) obj).getId()) return false;
        return true;
    }

}
