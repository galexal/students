package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Term {
    private int id;
    private String name;
    private String duration;
    private List<Discipline> disciplines = new ArrayList<>();

    public Term(int id, String name, String duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    public void addDiscipline(Discipline discipline) {
        disciplines.add(discipline);
    }
}
