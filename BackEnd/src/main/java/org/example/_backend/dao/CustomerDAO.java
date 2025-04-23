package org.example._backend.dao;


import org.example._backend.dto.impl.CustomerDTO;
import org.example._backend.entity.Impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;



public interface CustomerDAO extends JpaRepository<UserEntity,String> {
//    @Query("SELECT u, " +
//            "(SELECT ud.fullName FROM UserDetailsEntity ud WHERE ud.email = u.email), " +
//            "(SELECT ud.accountType FROM UserDetailsEntity ud WHERE ud.email = u.email), " +
//            "(SELECT ud.ncc FROM UserDetailsEntity ud WHERE ud.email = u.email), " +
//            "(SELECT ud.accountNumber FROM UserDetailsEntity ud WHERE ud.email = u.email) " +
//            "FROM UserEntity u " +
//            "WHERE u.email IN (SELECT ud.email FROM UserDetailsEntity ud) " +
//            "AND u.role = 'CUSTOMER'")
//    List<Object[]> findAllCustomerDetails();
@Query("SELECT new org.example._backend.dto.impl.CustomerDTO(u.user_id, u.email, " +
        "ud.fullName, ud.accountType, ud.accountNumber, ud.ncc) " +
        "FROM UserEntity u " +
        "JOIN UserDetailsEntity ud ON u.email = ud.email " +
        "WHERE u.role = 'CUSTOMER'")
List<CustomerDTO> findAllCustomerDetails();


    @Query(value = "SELECT COUNT(*) FROM user WHERE role = 'CUSTOMER'", nativeQuery = true)
    int countAllCustomers();


}
