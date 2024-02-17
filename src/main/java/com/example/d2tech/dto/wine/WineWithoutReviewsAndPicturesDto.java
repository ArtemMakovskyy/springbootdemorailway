package com.example.d2tech.dto.wine;


import com.example.d2tech.model.WineColor;
import com.example.d2tech.model.WineType;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class WineWithoutReviewsAndPicturesDto {
        private Long id;
        private String vendorCode;
        private String name;
        private String shortName;
        private BigDecimal averageRatingScore;
        private BigDecimal price;
        private String grape;
        private Boolean decantation;
        private WineType wineType;
        private BigDecimal strengthFrom;
        private BigDecimal strengthTo;
        private WineColor wineColor;
        private String colorDescribing;
        private String taste;
        private String aroma;
        private String gastronomy;
        private String description;
        private String pictureLink;
}