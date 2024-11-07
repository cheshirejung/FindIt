package com.FindIt.FindIt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class BoardImgEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardImgId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    private BoardEntity boardId;
    private String storePath;
}
