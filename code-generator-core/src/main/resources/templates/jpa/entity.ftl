package org.lotus.carp.generator.core.sample.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created  by carp-code-generator
 *
 * @author: carp-code-generator
 * Date: 7/17/2018
 * Time: 4:33 PM
 */
@Entity
@Table(name = "account_address")
@Data
public class AccountAddressEntity implements Serializable {
    private static final long serialVersionUID = 5000089111291759450L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "coin_id")
    private Long coinId;


    @Column(name = "address")
    private String address;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "amount")
    private BigDecimal amount;

}
