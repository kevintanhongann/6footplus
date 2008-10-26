package com.sixfootplus.service.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

import com.sixfootplus.Constants;
import com.sixfootplus.dao.LookupDao;
import com.sixfootplus.model.LabelValue;
import com.sixfootplus.model.Role;

public class LookupManagerImplTest extends BaseManagerMockTestCase {
	private LookupManagerImpl mgr = new LookupManagerImpl();
	private LookupDao lookupDao = null;

	@Before
	public void setUp() throws Exception {
		lookupDao = context.mock(LookupDao.class);
		mgr.setLookupDao(lookupDao);
	}

	@Test
	public void testGetAllRoles() {
		log.debug("entered 'testGetAllRoles' method");

		// set expected behavior on dao
		Role role = new Role(Constants.ADMIN_ROLE);
		final List<Role> testData = new ArrayList<Role>();
		testData.add(role);
		context.checking(new Expectations() {
			{
				one(lookupDao).getRoles();
				will(returnValue(testData));
			}
		});

		List<LabelValue> roles = mgr.getAllRoles();
		assertTrue(roles.size() > 0);
	}
}
