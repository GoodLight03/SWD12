package com.swd.productservice.DTO;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDTO {
    @Column(name = "sTenSP", length = 100, nullable = false)
    private String tenSanPham;

    @Column(name = "sDonViTinh", length = 10)
    private String donViTinh;

    @Column(name = "sHanDung", length = 10)
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
