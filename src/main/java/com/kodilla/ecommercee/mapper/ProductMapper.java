package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMapper {

    @Autowired
    private GroupRepository groupRepository;

    public ProductDto mapToProductDto(final Product product) {

        List<Long> cartsId = product.getCarts().stream()
                .map(Cart::getId)
                .toList();

        return new ProductDto(product.getId(), product.getName(), product.getPrice(), product.getDescription(), product.getStockQuantity(), product.getGroup().getId(), cartsId);
    }
    public Product mapToProduct(final ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getName(), productDto.getPrice(), productDto.getDescription(), productDto.getStockQuantity(), groupRepository.findById(productDto.getGroupId()).orElse(null));
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> products) {
        return products.stream().map(this::mapToProductDto).toList();
    }
}