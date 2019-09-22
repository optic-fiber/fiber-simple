package com.cheroliv.fiber.repository

//import com.cheroliv.fiber.domain.Inter
//import groovy.transform.CompileStatic
//import org.springframework.data.jpa.repository.JpaRepository
//import org.springframework.data.jpa.repository.Query
//import org.springframework.data.repository.query.Param
//import org.springframework.stereotype.Repository

//@Repository
interface InterRepository
//        extends JpaRepository<Inter, Long>
{

//    @Query("from Inter i where i.nd=:nd and type=:type")
//    Optional<Inter> find(@Param("nd") String nd, @Param("type") String type)
//
//    @Query("select distinct month(i.date), year(i.date) from Inter i order by year(i.date) asc,month(i.date) asc")
//    List<List<Integer>> distinctMoisParAnnee()
//
//    @Query("select i from Inter i where month(i.date)=:mois and year(i.date)=:annee")
//    List<Inter> findAllDeMoisDansAnnee(@Param("mois") Integer mois, @Param("annee") Integer annee)
//
//    @Query("select count(i) from Inter i where month(i.date)=:mois and year(i.date)=:annee and type='PLP'")
//    Integer countPlpParMoisDansAnnee(@Param("mois") Integer mois, @Param("annee") Integer annee)
//
//    @Query("select count(i) from Inter i where month(i.date)=:mois and year(i.date)=:annee and (i.type='BAAP' or i.type='BAOC' or i.type='BAFA' or i.type='BAST') and i.contrat!='Passage de cable'")
//    Integer countRacParMoisDansAnnee(@Param("mois") Integer mois, @Param("annee") Integer annee)
//
//    @Query("select count(i) from Inter i where month(i.date)=:mois and year(i.date)=:annee")
//    Integer countInterParMoisDansAnnee(@Param("mois") Integer mois, @Param("annee") Integer annee)
//
//    @Query("select count(i) from Inter i where month(i.date)=:mois and year(i.date)=:annee and type='BAFA' and contrat!='Passage de cable'")
//    Integer countBafaParMoisDansAnnee(@Param("mois") Integer mois, @Param("annee") Integer annee)
//
//    @Query("select count(i) from Inter i where month(i.date)=:mois and year(i.date)=:annee and type='BAST' and contrat!='Passage de cable'")
//    Integer countBastParMoisDansAnnee(@Param("mois") Integer mois, @Param("annee") Integer annee)
//
//    @Query("select count(i) from Inter i where month(i.date)=:mois and year(i.date)=:annee and type='SAV'")
//    Integer countSavParMoisDansAnnee(@Param("mois") Integer mois, @Param("annee") Integer annee)
//
//    @Query("select count(i) from Inter i where month(i.date)=:mois and year(i.date)=:annee and contrat='Passage de cable'")
//    Integer countPdcParMoisDansAnnee(@Param("mois") Integer mois, @Param("annee") Integer annee)
//
//    @Query("select count(i) from Inter i where month(i.date)=:mois and year(i.date)=:annee and contrat='Passage de cable' and type='BAAP'")
//    Integer countPdcBaapParMoisDansAnnee(@Param("mois") Integer mois, @Param("annee") Integer annee)
//
//    @Query("select count(i) from Inter i where month(i.date)=:mois and year(i.date)=:annee and contrat='Passage de cable' and type='BAOC'")
//    Integer countPdcBaocParMoisDansAnnee(@Param("mois") Integer mois, @Param("annee") Integer annee)
//
//    @Query("select count(i) from Inter i where month(i.date)=:mois and year(i.date)=:annee and contrat='Passage de cable' and type='BAFA'")
//    Integer countPdcBafaParMoisDansAnnee(@Param("mois") Integer mois, @Param("annee") Integer annee)
//
//    @Query("select count(i) from Inter i where month(i.date)=:mois and year(i.date)=:annee and contrat='Passage de cable' and type='BAST'")
//    Integer countPdcBastParMoisDansAnnee(@Param("mois") Integer mois, @Param("annee") Integer annee)
//
//    @Query("select count(i) from Inter i where month(i.date)=:mois and year(i.date)=:annee and type='BAOC' and contrat!='Passage de cable'")
//    Integer countBaocParMoisDansAnnee(@Param("mois") Integer mois, @Param("annee") Integer annee)
//
//    @Query("select count(i) from Inter i where month(i.date)=:mois and year(i.date)=:annee and type='BAAP' and contrat!='Passage de cable'")
//    Integer countBaapParMoisDansAnnee(@Param("mois") Integer mois, @Param("annee") Integer annee)
//
//    @Query("select count(i) from Inter i where month(i.date)=:mois and year(i.date)=:annee and (type='BAAP' or type='BAOC') and contrat='Passage de cable'")
//    Integer countPdcBaocBaapParMoisDansAnnee(@Param("mois") Integer mois, @Param("annee") Integer annee)
}
