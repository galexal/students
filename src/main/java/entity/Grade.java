package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Grade {
    private int id;
    private int value;
    private Student student;
    private Term term;
    private Discipline discipline;
}
