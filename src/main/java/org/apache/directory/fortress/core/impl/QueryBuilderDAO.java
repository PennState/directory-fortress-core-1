
package org.apache.directory.fortress.core.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.directory.api.ldap.model.cursor.CursorException;
import org.apache.directory.api.ldap.model.cursor.SearchCursor;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.message.SearchScope;
import org.apache.directory.fortress.core.FinderException;
import org.apache.directory.fortress.core.GlobalErrIds;
import org.apache.directory.fortress.core.GlobalIds;
import org.apache.directory.fortress.core.ldap.LdapDataProvider;
import org.apache.directory.fortress.core.model.AdminRole;
import org.apache.directory.fortress.core.search.AdminRoleQueryBuilder;
import org.apache.directory.ldap.client.api.LdapConnection;

/**
 * Data access class for running filter buidler queries
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class QueryBuilderDAO extends LdapDataProvider
{
    
    <T> List<T> runQuery( AdminRoleQueryBuilder queryBuilder, QueryBuilderProvider<T> provider ) throws FinderException
    {
        List<T> resultList = new ArrayList<T>();
        LdapConnection ld = null;
        String roleRoot = getRootDn( queryBuilder.getContextId(), provider.getRootDnString() );

        try
        {
            ld = getAdminConnection();
            SearchCursor searchResults = search( ld, roleRoot,
                SearchScope.ONELEVEL, queryBuilder.buildFilterString(), provider.getObjectReturnAttributes(), false, GlobalIds.BATCH_SIZE );
            long sequence = 0;

            while ( searchResults.next() )
            {
                resultList.add( provider.unloadLdapEntry( searchResults.getEntry(), sequence++, queryBuilder.getContextId() ) );
            }
        }
        catch ( LdapException e )
        {
            String error = "runQuery caught LdapException=" + e.getMessage();
            throw new FinderException( GlobalErrIds.ARLE_SEARCH_FAILED, error, e );
        }
        catch ( CursorException e )
        {
            String error = "runQuery caught CursorException=" + e.getMessage();
            throw new FinderException( GlobalErrIds.ARLE_SEARCH_FAILED, error, e );
        }
        finally
        {
            closeAdminConnection( ld );
        }

        return resultList;
    }

}
