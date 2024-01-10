package com.tobeto.pair9.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tobeto.pair9.entities.absracts.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name="brands")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand extends BaseEntity {

    @Column(name = "name")
    private String name;
    @Column(name= "logo_path")
    private String logoPath;

    @OneToMany(mappedBy = "brand")
    @JsonIgnore
    private List<Model>models;


}
