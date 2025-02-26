package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private CartRepository cartRepository;

    public List<Product> getProducts() {
        return (List<Product>) productRepository.findAll();
    }

    public Optional<Product> getProductById(final Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(final Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(final Long productId) {
        productRepository.deleteById(productId);
    }

    public Product updateProduct(final Long productId, final ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setStockQuantity(productDto.getStockQuantity());
            product.setGroup(groupRepository.findById(productDto.getGroupId()).get());

            List<Cart> carts = productDto.getCartsId().stream()
                    .map(cartId -> cartRepository.findById(cartId))
                    .map(Optional::get)
                    .toList();

            product.setCarts(carts);

            return productRepository.save(product);

        } else throw new RuntimeException("Product not found");
    }

}
