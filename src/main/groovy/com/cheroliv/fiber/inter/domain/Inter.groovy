package com.cheroliv.fiber.inter.domain

import com.cheroliv.fiber.inter.domain.enumeration.ContractEnum
import com.cheroliv.fiber.inter.domain.enumeration.TypeInterEnum
import groovy.transform.ToString
import groovy.transform.TypeChecked

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import java.time.LocalDateTime

@Entity
@ToString
@TypeChecked
@Table(name = "`inter`", indexes = [
        @Index(name = "`uniq_idx_inter_nd_type`", columnList = "`nd`,`type_inter`", unique = true),
        @Index(name = "`idx_inter_type`", columnList = "`type_inter`"),
        @Index(name = "`idx_inter_contract`", columnList = "`contract`"),
        //TODO rendre cette index unique et corriger
        // le jeux de données pour qu'il soit coherent avec l'index
        @Index(name = "`idx_inter_date_time_inter`", columnList = "`date_time_inter`"/*, unique = true*/),
        @Index(name = "`idx_inter_first_name_client`", columnList = "`first_name_client`"),
        @Index(name = "`idx_inter_last_name_client`", columnList = "`last_name_client`")])
class Inter implements Serializable {

    static final long serialVersionUID = 1L
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "`id`")
    Long id
    @Column(name = "`nd`",
            length = 10)
    @NotNull(message = InterConstants.ND_NOTNULL_CSTRT_TPL_MSG)
    @Size(min = 10, max = 10,
            message = InterConstants.ND_SIZE_CSTRT_TPL_MSG)
    String nd
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "`type_inter`",
            nullable = false,
            length = 4)
    TypeInterEnum typeInter
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "`contract`",
            nullable = false,
            length = 16)
    ContractEnum contract
    @NotNull
    @Column(name = "`date_time_inter`")
    LocalDateTime dateTimeInter
    @Size(max = 100)
    @Column(name = "`first_name_client`",
            length = 100)
    String firstNameClient
    @Size(max = 100)
    @Column(name = "`last_name_client`",
            length = 100)
    String lastNameClient


//    String[] toArrayString() {
//        String[] strings =
//                [nd, type, contrat,
//                 heure.hour < 10 ? "0${heure.hour}" : heure.hour,
//                 date.format(ofPattern("dd/MM/yyyy")),
//                 nom.toLowerCase() == "null" || nom == null ? "" : nom,
//                 prenom.toLowerCase() == "null" || prenom == null ? "" : prenom]
//        strings
//    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (this.class != o.class) return false

        Inter inter = o as Inter

        if (contract != inter.contract) return false
        if (dateTimeInter != inter.dateTimeInter) return false
        if (firstNameClient != inter.firstNameClient) return false
        if (lastNameClient != inter.lastNameClient) return false
        if (nd != inter.nd) return false
        if (typeInter != inter.typeInter) return false

        return true
    }

    int hashCode() {
        int result
        result = (nd != null ? nd.hashCode() : 0)
        result = 31 * result + (typeInter != null ? typeInter.hashCode() : 0)
        result = 31 * result + (contract != null ? contract.hashCode() : 0)
        result = 31 * result + (dateTimeInter != null ? dateTimeInter.hashCode() : 0)
        result = 31 * result + (firstNameClient != null ? firstNameClient.hashCode() : 0)
        result = 31 * result + (lastNameClient != null ? lastNameClient.hashCode() : 0)
        return result
    }
}