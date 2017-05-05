package com.wingedfish.eagles.distributed.impl;

import com.wingedfish.eagles.distributed.DistributedTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by lixiuhai on 2017/5/5.
 */
@Service
public class DistributedTransactionManagerImpl implements DistributedTransactionManager {

    @Autowired
    private PlatformTransactionManager transactionManager;

    public TransactionTemplate getTransactionTemplate(){
        return new TransactionTemplate(transactionManager);
    }
    public boolean insertDistributedDatabase(){
       boolean result = getTransactionTemplate().execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                return true;
            }
        });
        return result;
    }
}
