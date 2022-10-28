package com.travelwithme.withtravel.UnitTest.Entity;

import com.travelwithme.withtravel.Account.Address;
import org.assertj.core.api.AssertionInfo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;


public class AddressEntityTest {

    @Test
    public void AddressMakeTest(){
        Address address = new Address("동래", "온천", "부산");
        Assert.isInstanceOf(Address.class, address);
    }

    @Test
    public void toStringTest(){
        Address address = new Address("동래", "온천", "부산");
        Assertions.assertEquals("동래(온천)/부산", address.toString());
    }
}
