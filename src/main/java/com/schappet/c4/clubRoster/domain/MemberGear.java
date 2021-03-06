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
@Entity( name = "com_schappet_c4_clubRoster_domain_memberGear" )
@Table( name = "member_gear", schema = "clubroster" )
@JsonIgnoreProperties( { "hibernateLazyInitializer", "handler" } )
public class MemberGear { 

	private static final Log log = LogFactory.getLog( MemberGear.class );

    private Integer memberGearId;
    private Gear gear;
    private ClubMember clubMember;


    @javax.persistence.SequenceGenerator(  name="gen",  sequenceName="clubroster.seqnum",allocationSize=1)
    @Id
    @GeneratedValue( strategy=GenerationType.AUTO,generator="gen")
    @Column(name = "member_gear_id", unique = true, nullable = false)
    public Integer getMemberGearId(){
        return memberGearId;
    }

    public void setMemberGearId(Integer memberGearId){
        this.memberGearId = memberGearId;
    }

    @ManyToOne(fetch = FetchType.LAZY,  targetEntity=Gear.class )
    @JoinColumn(name = "gear_id",nullable = true)
    public Gear getGear(){
        return gear;
    }

    public void setGear(Gear gear){
        this.gear = gear;
    }

    @ManyToOne(fetch = FetchType.LAZY,  targetEntity=ClubMember.class )
    @JoinColumn(name = "member_id",nullable = true)
    public ClubMember getClubMember(){
        return clubMember;
    }

    public void setClubMember(ClubMember clubMember){
        this.clubMember = clubMember;
    }


}
