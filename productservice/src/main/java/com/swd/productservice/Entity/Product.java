package com.swd.productservice.Entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_SanPham")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_sMaSP")
    private Integer id;

    @Column(name = "sTenSP", length = 100, nullable = false,unique = true)
    private String tenSanPham;

    @Column(name = "sDonViTinh", length = 10)
    private String donViTinh;

    @Column(name = "sHanDung")
    private Date  hanSuDung;

    @Column(name = "iSL")
    private int soLuong;

    @Column(name = "fDonGiaBan")
    private float donGiaBan;

    @Column(name = "FK_sMaLoai", length = 10)
    private Integer maLoai;

    @Column(name = "FK_sMaNCC", length = 10)
    private Integer maNCC;
}
