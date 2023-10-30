package dev.bracers.inditex.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Brand {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    public Brand(long id) {
    	this.id = id;
    }
}
