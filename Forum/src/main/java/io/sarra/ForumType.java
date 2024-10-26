package io.sarra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForumType {
   public Date date ;
   public long count;
    public ForumType(Long count, Date date) {
        this.count = count;
        this.date = date;
    }
}
