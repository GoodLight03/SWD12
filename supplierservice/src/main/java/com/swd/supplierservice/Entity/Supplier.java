package com.swd.supplierservice.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_NhaCungCap")
@Getter
@Setter
 @NoArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_sMaNCC")
    private Integer id;

    @Column(name = "sTenNCC", length = 100, nullable = false,unique = true)
    private String sTenNCC;

    @Column(name = "sDiaChi", length = 100, nullable = false,unique = true)
    private String sDiaChi;

    @Column(name = "sSDT", length = 100, nullable = false,unique = true)
    private String sSDT;

    @Column(name = "sSoTK", length = 100, nullable = false,unique = true)
    private String sSoTK;
}
