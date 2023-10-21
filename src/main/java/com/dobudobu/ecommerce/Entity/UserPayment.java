package com.dobudobu.ecommerce.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Data
@Table(name = "tb_user_payment")
@AllArgsConstructor
@NoArgsConstructor
public class UserPayment {

    @Id
    @GenericGenerator(strategy = "uuid2", name = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @OneToMany
    private List<Provider> providers;

}
