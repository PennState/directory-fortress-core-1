package org.apache.directory.fortress.core.search;

import static org.apache.directory.ldap.client.api.search.FilterBuilder.contains;
import static org.apache.directory.ldap.client.api.search.FilterBuilder.startsWith;
import static org.apache.directory.ldap.client.api.search.FilterBuilder.equal;

import java.util.List;

import org.apache.directory.fortress.core.GlobalIds;
import org.apache.directory.fortress.core.model.AdminRole;
import org.apache.directory.ldap.client.api.search.FilterBuilder;

public class AdminRoleQueryBuilder extends AbstractQueryBuilder
{
    private static final String ROLE_NM = "ftRoleName";
    
    public AdminRoleQueryBuilder(){
        filters.add( equal( "objectClass", GlobalIds.ROLE_OBJECT_CLASS_NM ) );
    }
    
    public AdminRoleQueryBuilder addNameLikeFilter( String name ){
        filters.add( contains( ROLE_NM, name ) );
        return this;
    }
    
    public AdminRoleQueryBuilder addNameStartsWithFilter( String name ){
        filters.add( startsWith( ROLE_NM, name ) );
        return this;
    }
    
}