package ua.tonkoshkur.tennis.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {

    private Integer id;
    private String name;

    public PlayerDto(String name) {
        this.name = name;
    }
}