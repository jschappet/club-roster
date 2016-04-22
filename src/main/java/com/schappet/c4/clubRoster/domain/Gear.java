package com.schappet.c4.clubRoster.domain;

import java.util.Set;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Table;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;
import org.hibernate.annotations.*;
import javax.persistence.CascadeType;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.*;
import com.schappet.c4.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 
 * Generated by Protogen
 * @see <a href="https://github.com/ui-icts/protogen">https://github.com/ui-icts/protogen</a>
 * @since 04/21/2016 08:21:12 PM CDT
 */
@Entity( name = "com_schappet_c4_clubRoster_domain_gear" )
@Table( name = "gear", schema = "clubroster" )
@JsonIgnoreProperties( { "hibernateLazyInitializer", "handler" } )
public class Gear { 

	private static final Log log = LogFactory.getLog( Gear.class );

    private Integer gearId;
    private String gearType;
    private String modelName;
    private String makeName;
    private String description;
    private Set<MemberGear> memberGears = new HashSet<MemberGear>(0);


    @javax.persistence.SequenceGenerator(  name="gen",  sequenceName="clubroster.seqnum",allocationSize=1)
    @Id
    @GeneratedValue( strategy=GenerationType.AUTO,generator="gen")
    @Column(name = "gear_id", unique = true, nullable = false)
    public Integer getGearId(){
        return gearId;
    }

    public void setGearId(Integer gearId){
        this.gearId = gearId;
    }

    @Column(name = "gear_type")
    public String getGearType(){
        return gearType;
    }

    public void setGearType(String gearType){
        this.gearType = gearType;
    }

    @Column(name = "model_name")
    public String getModelName(){
        return modelName;
    }

    public void setModelName(String modelName){
        this.modelName = modelName;
    }

    @Column(name = "make_name")
    public String getMakeName(){
        return makeName;
    }

    public void setMakeName(String makeName){
        this.makeName = makeName;
    }

    @Column(name = "description")
    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,   mappedBy = "gear", targetEntity=MemberGear.class, cascade=CascadeType.REMOVE)
    public Set<MemberGear> getMemberGears(){
        return memberGears;
    }

    public void setMemberGears(Set<MemberGear> memberGears){
        this.memberGears = memberGears;
    }


}