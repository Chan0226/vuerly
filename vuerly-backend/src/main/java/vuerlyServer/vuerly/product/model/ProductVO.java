package vuerlyServer.vuerly.product.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class  ProductVO {
        private int num;
        private String name;
        private String subtitle;
        private int price;
        private int stock;
        private String desc_image;
        private String detail_image;
        private int sale;
        private String thumbnail;
        private int readcount;
        private String category;
        private boolean shipping_option;
        private Timestamp created_at;
        private int cost;

    }
