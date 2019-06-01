package com.code.mallservice.mall.entity;

public class TrackingMoreModel {
    private String tracking_number;
    private String carrier_code;
    private String destination_code;
    private String title;
    private String logistics_channel;
    private String customer_name;
    private String customer_email;
    private String customer_phone;
    private String order_id;
    private String comment;
    private String order_create_time;

    public String getTracking_number() {
        return tracking_number;
    }

    public void setTracking_number(String tracking_number) {
        this.tracking_number = tracking_number;
    }

    public String getCarrier_code() {
        return carrier_code;
    }

    public void setCarrier_code(String carrier_code) {
        this.carrier_code = carrier_code;
    }

    public String getDestination_code() {
        return destination_code;
    }

    public void setDestination_code(String destination_code) {
        this.destination_code = destination_code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogistics_channel() {
        return logistics_channel;
    }

    public void setLogistics_channel(String logistics_channel) {
        this.logistics_channel = logistics_channel;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOrder_create_time() {
        return order_create_time;
    }

    public void setOrder_create_time(String order_create_time) {
        this.order_create_time = order_create_time;
    }
}
