package com.cheroliv.fiber.console.domain

import com.cheroliv.fiber.console.domain.enumeration.ContractEnum
import com.cheroliv.fiber.console.domain.enumeration.TypeInterEnum
import com.cheroliv.fiber.console.domain.groups.*
import groovy.transform.ToString

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime

import static java.time.format.DateTimeFormatter.ofPattern

@ToString
@Entity
@Table(name = "`inter`", indexes = [
        @Index(name = "`uniq_idx_inter_nd_type`", columnList = "`nd`,`type_inter`", unique = true),
        @Index(name = "`idx_inter_type`", columnList = "`type_inter`"),
        @Index(name = "`idx_inter_contract`", columnList = "`contract`"),
        @Index(name = "`idx_inter_date_time_inter`", columnList = "`date_time_inter`"),
        @Index(name = "`idx_inter_first_name_client`", columnList = "`first_name_client`"),
        @Index(name = "`idx_inter_last_name_client`", columnList = "`last_name_client`")])
class Inter implements InterConstants, Serializable {

    static final long serialVersionUID = 1L
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "`id`")
//"id_inter"
    Long id
    @Column(name = "`nd`")
    @NotNull(message = InterConstants.ND_NOTNULL_CSTRT_TPL_MSG,
            groups = InterChecks)
    @Size(min = 10, max = 10,
            message = InterConstants.ND_SIZE_CSTRT_TPL_MSG,
            groups = InterChecks)
    String nd
    @NotNull
    @Pattern(regexp = "BAAP|BAOC|BAFA|BAST|PLP|SAV")
    String type
    @NotNull
    @Pattern(regexp = "LM|IQ|Passage de cable")
    String contrat
    @NotNull
    @Column(columnDefinition = "TIME")
    LocalTime heure
    @Column(columnDefinition = "DATE")
    @NotNull
    LocalDate date
    @Size(max = 100)
    String nom
    @Size(max = 100)
    String prenom
    @NotNull(groups = InterChecks)
    @Enumerated(EnumType.STRING)
    @Column(name = "`type_inter`", nullable = false)
    TypeInterEnum typeInter
    @NotNull(groups = InterChecks)
    @Enumerated(EnumType.STRING)
    @Column(name = "`contract`", nullable = false)
    ContractEnum contract
    @NotNull(groups = InterChecks)
    @Column(name = "`date_time_inter`")
    ZonedDateTime dateTimeInter
    @Size(max = 100, groups = InterChecks)
    @Column(name = "`first_name_client`")
    String firstNameClient
    @Size(max = 100, groups = InterChecks)
    @Column(name = "`last_name_client`")
    String lastNameClient


    String[] toArrayString() {
        String[] strings =
                [nd, type, contrat,
                 heure.hour < 10 ? "0${heure.hour}" : heure.hour,
                 date.format(ofPattern("dd/MM/yyyy")),
                 nom.toLowerCase() == "null" || nom == null ? "" : nom,
                 prenom.toLowerCase() == "null" || prenom == null ? "" : prenom]
        strings
    }

//    boolean equals(o) {
//        if (this.is(o)) return true
//        if (!(o instanceof Inter)) return false
//
//        Inter inter = (Inter) o
//
//        if (contrat != inter.contrat) return false
//        if (date != inter.date) return false
//        if (heure != inter.heure) return false
//        if (nd != inter.nd) return false
//        if (nom != inter.nom) return false
//        if (prenom != inter.prenom) return false
//        if (type != inter.type) return false
//        return true
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
//    int hashCode() {
//        int result
//        result = (nd != null ? nd.hashCode() : 0)
//        result = 31 * result + (type != null ? type.hashCode() : 0)
//        result = 31 * result + (contrat != null ? contrat.hashCode() : 0)
//        result = 31 * result + (heure != null ? heure.hashCode() : 0)
//        result = 31 * result + (date != null ? date.hashCode() : 0)
//        result = 31 * result + (nom != null ? nom.hashCode() : 0)
//        result = 31 * result + (prenom != null ? prenom.hashCode() : 0)
//        return result
//    }



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
/*


    @NotNull
    @Valid
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "`planning_id`")
    Planning planning






}

 */