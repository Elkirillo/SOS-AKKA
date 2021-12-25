package com.company.jmixtesr.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.ui.component.SupportsCaptionPosition;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "ZAYAVKI")
@Entity
public class Zayavki {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @InstanceName
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @JoinTable(name = "ZAYAVKI_USER_LINK",
            joinColumns = @JoinColumn(name = "ZAYAVKI_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<User> user_id;

    @JoinTable(name = "DEPART_ZAYAVKI_LINK",
            joinColumns = @JoinColumn(name = "ZAYAVKI_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "DEPART_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<Depart> departs;

    public void setUser_id(List<User> user_id) {
        this.user_id = user_id;
    }

    public List<User> getUser_id() {
        return user_id;
    }

    public List<Depart> getDeparts() {
        return departs;
    }

    public void setDeparts(List<Depart> departs) {
        this.departs = departs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(SupportsCaptionPosition.CaptionPosition status) {
        this.status = status == null ? null : status.getId();
    }

    public SupportsCaptionPosition.CaptionPosition getStatus() {
        return status == null ? null : SupportsCaptionPosition.CaptionPosition.fromId(status);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}