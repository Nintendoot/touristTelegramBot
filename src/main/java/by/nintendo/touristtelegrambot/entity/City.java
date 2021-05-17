package by.nintendo.touristtelegrambot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private long id;
    @Column(name = "name")
    @NotBlank
    @Size(min = 3, max = 60, message = "Name can be 3 to 60 characters long")
    private String name;
    @Column(name = "description")
    @Size(min = 3, max = 60, message = "description can be 3 to 60 characters long")
    private String description;

    @Override
    public String toString() {
        return  name + " - " + description;
    }
}
