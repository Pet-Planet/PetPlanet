package com.example.pet.domain.board;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Board {

    @Id
    @Column(name = "post_id")
    private String postId;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String category;

    @Column
    private URL imageUrl1;

}
