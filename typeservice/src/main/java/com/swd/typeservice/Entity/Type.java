package com.swd.typeservice.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_LoaiSanPham")
@Getter
@Setter
 @NoArgsConstructor
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_sMaLoai")
    private Integer id;

    @Column(name = "sTenLoai", length = 100, nullable = false,unique = true)
    private String sTenLoai;
}
