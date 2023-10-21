package com.dobudobu.ecommerce.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@Table(name = "tb_user")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, unique = true)
    private String username;

    @Column(length = 30)
    private String password;

    @Column(name = "first_name", length = 20)
    private String firstName;

    @Column(name = "last_name", length = 20)
    private String lastName;

    @Column(name = "no_telp", length = 20)
    private String noTelp;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm")
    private Date createdAt;

    @Column(name = "modified_at")
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm")
    private Date modifiedAt;

}
