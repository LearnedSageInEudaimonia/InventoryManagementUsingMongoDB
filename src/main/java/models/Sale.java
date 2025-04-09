package models;

import java.time.LocalDateTime;

public class Sale {
    public String saleId;
    public String productId;
    public int quantitySold;
    public LocalDateTime timeStamp;

    public Sale(String saleId, String productId, int quantitySold, LocalDateTime timeStamp) {
        this.saleId = saleId;
        this.productId = productId;
        this.quantitySold = quantitySold;
        this.timeStamp = timeStamp;
    }
}
