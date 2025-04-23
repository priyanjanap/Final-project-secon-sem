package org.example._backend.util;

import org.example._backend.dao.LoanDAO;
import org.example._backend.dao.PaymentDAO;
import org.example._backend.dao.UserDAO;
import org.example._backend.entity.Impl.LoanEntity;
import org.example._backend.entity.Impl.PaymentEntity;
import org.example._backend.entity.Impl.UserEntity;

public class GenerateID {
    public static String generateId(Object dao, String prefix) {
        int number = 0;
        Object lastRowNative = extractEntityObject(dao);
        if (lastRowNative != null) {
            String idValue = extractIdValue(lastRowNative);
            if (idValue != null) {
                String[] parts = idValue.split("-");
                number = Integer.parseInt(parts[1]);
            }
        }

        return prefix + "-" + ++number;
    }

    public static Object extractEntityObject(Object dao) {
        Object lastRowNative = null;
        if (dao instanceof UserDAO) {
            lastRowNative = ((UserDAO) dao).findLastRowNative();

        } else if (dao instanceof LoanDAO) {
            lastRowNative = ((LoanDAO) dao).findLastRowNative();
            
        } else if (dao instanceof PaymentDAO) {
            lastRowNative = ((PaymentDAO) dao).findLastRowNative();

        }
        return lastRowNative;
    }

    public static String extractIdValue(Object lastRowNative) {
        return switch (lastRowNative) {
            case UserEntity userEntity -> userEntity.getUser_id();
            case LoanEntity loanEntity -> loanEntity.getLoanId();
            case PaymentEntity paymentEntity -> paymentEntity.getPaymentId();
            default -> throw new IllegalStateException("Unexpected value: " + lastRowNative);
        };
    }
}
