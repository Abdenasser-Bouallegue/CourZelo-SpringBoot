package io.sarra;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Forum implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idForum;
    @NotBlank(message = "Body can't be empty")
    @Column(nullable = false, length = 3000)
    private String body;
    @NotBlank(message = "Title can't be empty.")
    @Size(min = 3, message = "A title must be at least 3 characters.")
    @Column(nullable = false)
    private String title;

    private int likes;
    private int dislikes;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    @Lob
    @Column(length = 209715200)
    private byte[] image;






}
