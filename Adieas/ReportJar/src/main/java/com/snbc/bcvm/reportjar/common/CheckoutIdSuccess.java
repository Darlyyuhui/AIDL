package com.snbc.bcvm.reportjar.common;


import com.snbc.bcvm.reportjar.entity.ResponseEntity;

/**
 * Create by QinHaonan on 2019/1/22
 */
public interface CheckoutIdSuccess<T> {
    String  onSuccess(ResponseEntity<T> responseEntity);
}
