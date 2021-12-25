package com.company.jmixtesr.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "DEPART")
@Entity
public class Depart {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "DEPART_NAME", nullable = false)
    private String depart_name;

    @JoinTable(name = "DEPART_ZAYAVKI_LINK",
            joinColumns = @JoinColumn(name = "DEPART_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ZAYAVKI_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<Zayavki> list_of_zayavki;

    public List<Zayavki> getList_of_zayavki() {
        return list_of_zayavki;
    }

    public void setList_of_zayavki(List<Zayavki> list_of_zayavki) {
        this.list_of_zayavki = list_of_zayavki;
    }

    public String getDepart_name() {
        return depart_name;
    }

    public void setDepart_name(String depart_name) {
        this.depart_name = depart_name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}