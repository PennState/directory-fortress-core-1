
package org.apache.directory.fortress.core.impl;

import java.util.List;

import org.apache.directory.fortress.core.DelReviewMgr;
import org.apache.directory.fortress.core.DelReviewMgrFactory;
import org.apache.directory.fortress.core.SecurityException;
import org.apache.directory.fortress.core.model.AdminRole;
import org.apache.directory.fortress.core.search.AdminRoleQueryBuilder;
import org.junit.Test;
import org.junit.Assert;

public class DelReviewMgrSearchTest
{

    @Test
    public void testSearchAdminRole() throws SecurityException{
        DelReviewMgr delReviewMgr = DelReviewMgrFactory.createInstance();
        
        AdminRoleQueryBuilder rqb = new AdminRoleQueryBuilder();
        rqb.addNameLikeFilter( "core" );
        rqb.addPropertyEqualsFilter( "myprop", "myvalue" );
        
        List<AdminRole> adminRoles = delReviewMgr.findRoles( rqb );
        Assert.assertTrue( adminRoles.size() > 0 );
        
    }
}
