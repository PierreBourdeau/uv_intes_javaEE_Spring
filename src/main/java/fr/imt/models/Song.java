package fr.imt.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "songs")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Song {

    @Id
    private String guid;

    @NotNull
    @NotBlank
    @Size(max = 80)
    private String genre;

    @NotNull
    @NotBlank
    @Size(max = 80)
    private String title;

    @NotNull
    @Min(0)
    private Integer length;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String artist;

    public Song() {
        guid = UUID.randomUUID().toString();
    }
}
