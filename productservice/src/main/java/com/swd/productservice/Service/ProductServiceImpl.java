package com.swd.productservice.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swd.productservice.DTO.ProductDTO;
import com.swd.productservice.Entity.Product;
import com.swd.productservice.Repository.ProductRepository;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean createProduct(ProductDTO productDTO) {
        if (!validProduct(productDTO, "CREATE"))
            return false;

        Product product = new Product();
        // productDTO.getMaSP(),
        product.setTenSanPham(productDTO.getTenSanPham());
        product.setDonViTinh(productDTO.getDonViTinh());
        product.setHanSuDung(productDTO.getHanSuDung());
        product.setSoLuong(productDTO.getSoLuong());
        product.setDonGiaBan(productDTO.getDonGiaBan());
        product.setMaLoai(productDTO.getMaLoai());
        product.setMaNCC(productDTO.getMaNCC());

        productRepository.save(product);
        storeLog("Thêm sản phẩm thành công: " + productDTO.getTenSanPham());
        return true;
    }

    @Override
    public ProductDTO productDetail(Integer id) {
        Optional<Product> Product = productRepository.findById(id);
        if (Product.isPresent()) {
            Product sp = Product.get();
            ProductDTO product = new ProductDTO();
            // productDTO.getMaSP(),
            product.setTenSanPham(sp.getTenSanPham());
            product.setDonViTinh(sp.getDonViTinh());
            product.setHanSuDung(sp.getHanSuDung());
            product.setSoLuong(sp.getSoLuong());
            product.setDonGiaBan(sp.getDonGiaBan());
            product.setMaLoai(sp.getMaLoai());
            product.setMaNCC(sp.getMaNCC());
            return product;
        }
        return null;
    }

    @Override
    public boolean updateProduct(Integer id, ProductDTO productDTO) {
        if (!validProduct(productDTO, "UPDATE"))
            return false;

         Optional<Product> existingProduct = productRepository.findById(id);
        if (!existingProduct.isPresent()) {
            storeLog("Cập nhật thất bại: Không tìm thấy sản phẩm " + id);
            return false;
        }

        Product Product = existingProduct.get();
        Product.setTenSanPham(productDTO.getTenSanPham());
        Product.setDonViTinh(productDTO.getDonViTinh());
        Product.setHanSuDung(productDTO.getHanSuDung());
        Product.setSoLuong(productDTO.getSoLuong());
        Product.setDonGiaBan(productDTO.getDonGiaBan());
        Product.setMaLoai(productDTO.getMaLoai());
        Product.setMaNCC(productDTO.getMaNCC());

        productRepository.save(Product);
        storeLog("Cập nhật thành công: " + productDTO.getTenSanPham());
        return true;
    }

    @Override
    public boolean deleteProduct(Integer id) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (!existingProduct.isPresent()) {
            storeLog("Xóa thất bại: Không tìm thấy sản phẩm " + id);
            return false;
        }

        productRepository.deleteById(id);
        storeLog("Xóa thành công: " + id);
        return true;
    }

    @Override
    public boolean validProduct(ProductDTO productDTO, String action) {
        // 1️ Kiểm tra trường dữ liệu rỗng
        // if (isEmpty(productDTO.getMaSP())) {
             storeLog("action" + productDTO.toString());
        //     return false;
        // }
        if (isEmpty(productDTO.getTenSanPham())) {
            storeLog(action + " thất bại: Tên sản phẩm không được để trống");
            return false;
        }
        if (isEmpty(productDTO.getDonViTinh())) {
            storeLog(action + " thất bại: Đơn vị tính không được để trống");
            return false;
        }

        // if (isEmpty(productDTO.getHanSuDung())) {
        //     storeLog(action + " thất bại: Hạn sử dụng không được để trống");
        //     return false;
        // }

        // 2️ Kiểm tra số lượng & đơn giá phải >= 0
        if (productDTO.getSoLuong() < 0) {
            storeLog(action + " thất bại: Số lượng không hợp lệ");
            return false;
        }
        if (productDTO.getDonGiaBan() < 0) {
            storeLog(action + " thất bại: Đơn giá bán không hợp lệ");
            return false;
        }

        storeLog(productDTO.getHanSuDung().toString());
        // 3️ Kiểm tra hạn sử dụng phải lớn hơn ngày hiện tại

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
            LocalDate expirationDate = LocalDate.parse(productDTO.getHanSuDung().toString(), formatter);

        if (!isValidExpirationDate(expirationDate)) {
            storeLog(action + " thất bại: Hạn sử dụng phải lớn hơn ngày hiện tại");
            return false;
        }

        // 4️ Nếu là CREATE, kiểm tra mã sản phẩm đã tồn tại chưa
        if ("CREATE".equalsIgnoreCase(action)) {
            Optional<Product> existingProduct = productRepository.findByNameName(productDTO.getTenSanPham());
            //storeLog("action"+existingProduct.get());
            if (existingProduct.isPresent()) {
                storeLog(productDTO.toString());
                storeLog("CREATE thất bại: Tên sản phẩm " + productDTO.getTenSanPham() + " đã tồn tại");
                return false;
            }
        }

        //  Hợp lệ
        storeLog(action + " thành công: Dữ liệu hợp lệ");
        return true;
    }

    /**
     * Kiểm tra chuỗi có rỗng hoặc null không.
     */
    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    /**
     * Kiểm tra hạn sử dụng có hợp lệ không (phải lớn hơn ngày hiện tại).
     */
    private boolean isValidExpirationDate(LocalDate expirationDate) {
        try {
            
            return expirationDate.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            storeLog("Lỗi định dạng ngày: " + expirationDate + " (Định dạng hợp lệ: yyyy-MM-dd)");
            return false;
        }
    }

    @Override
    public void storeLog(String status) {
        System.out.println("[LOG] " + status);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}
