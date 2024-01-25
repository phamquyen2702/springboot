package com.example.demo2.common;


import net.bytebuddy.utility.RandomString;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;


public class RandomId implements IdentifierGenerator {


    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        RandomString randomString = new RandomString(10);
        return randomString.nextString();
    }
}
