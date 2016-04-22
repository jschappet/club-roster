package com.schappet.c4.clubRoster.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Generated by Protogen
 * @see <a href="https://github.com/ui-icts/protogen">https://github.com/ui-icts/protogen</a>
 * @since 04/21/2016 20:21:12 CDT
 */
@Component
public class ClubRosterDaoService {

	@Autowired
	private ClubMemberService clubMemberService;

    public ClubMemberService getClubMemberService() {
        return clubMemberService;
    }

	@Autowired
	private GearService gearService;

    public GearService getGearService() {
        return gearService;
    }

	@Autowired
	private MemberGearService memberGearService;

    public MemberGearService getMemberGearService() {
        return memberGearService;
    }

}