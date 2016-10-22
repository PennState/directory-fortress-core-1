/*
 *   Licensed to the Apache Software Foundation (ASF) under one
 *   or more contributor license agreements.  See the NOTICE file
 *   distributed with this work for additional information
 *   regarding copyright ownership.  The ASF licenses this file
 *   to you under the Apache License, Version 2.0 (the
 *   "License"); you may not use this file except in compliance
 *   with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an
 *   "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *   KIND, either express or implied.  See the License for the
 *   specific language governing permissions and limitations
 *   under the License.
 *
 */
package org.apache.directory.fortress.core.search;

import static org.apache.directory.ldap.client.api.search.FilterBuilder.contains;
import static org.apache.directory.ldap.client.api.search.FilterBuilder.startsWith;
import static org.apache.directory.ldap.client.api.search.FilterBuilder.equal;

import java.util.List;

import org.apache.directory.fortress.core.GlobalIds;
import org.apache.directory.fortress.core.model.AdminRole;
import org.apache.directory.ldap.client.api.search.FilterBuilder;

public class AdminRoleQueryBuilder extends AbstractQueryBuilder<AdminRoleQueryBuilder>
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