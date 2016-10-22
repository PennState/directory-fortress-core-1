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
package org.apache.directory.fortress.core.impl;

import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.exception.LdapInvalidAttributeValueException;
import org.apache.directory.fortress.core.GlobalIds;
import org.apache.directory.fortress.core.model.PermObj;

public class PermObjDAO extends PermDAO implements QueryBuilderProvider<PermObj> 
{

    @Override
    public String getRootDnString()
    {
        return GlobalIds.PERM_ROOT;
    }

    @Override
    public String[] getObjectReturnAttributes()
    {
        return PERMISION_OBJ_ATRS;
    }

    @Override
    public PermObj unloadLdapEntry( Entry le, long sequence, String contextId )
        throws LdapInvalidAttributeValueException
    {
        return unloadPobjLdapEntry( le, sequence, false );
    }

}
