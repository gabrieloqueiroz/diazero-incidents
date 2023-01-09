package br.com.diazero.incidents.spring.mother;

import br.com.diazero.incidents.spring.domain.entity.Comments;
import br.com.diazero.incidents.spring.domain.entity.Incidents;

public class CommentMother {

    public static Comments getComment(String comment, Incidents incident){
        return new Comments(comment, incident);
    }
}
