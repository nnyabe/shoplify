package com.shoplify.shoplify.models;

import jakarta.persistence.*;

@Entity
@Table(name="order_quantity")
public class OrderQuantity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id",unique = true, nullable=false)
    private Long id;

    @Column(name="quantity", nullable = false)
    private Integer quantity;

    @ManyToOne(optional=false)
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

    @ManyToOne(optional=false)
    @JoinColumn(name="order_id", nullable=false)
    private Orders order;



    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
