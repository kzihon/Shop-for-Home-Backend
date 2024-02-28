package com.cogent.ShopforHome.entity.HttpData;

public class FavoriteRequest {
    private long customer_id;
    private long property_id;

    public FavoriteRequest(long customer_id, long property_id) {
        this.customer_id = customer_id;
        this.property_id = property_id;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public long getProperty_id() {
        return property_id;
    }

    public void setProperty_id(long property_id) {
        this.property_id = property_id;
    }
}
