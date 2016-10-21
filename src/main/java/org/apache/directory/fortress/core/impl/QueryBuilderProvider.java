
package org.apache.directory.fortress.core.impl;

import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.exception.LdapInvalidAttributeValueException;

public interface QueryBuilderProvider<T>
{
    public String getRootDnString();
 
    public String[] getObjectReturnAttributes();
    
    public T unloadLdapEntry( Entry le, long sequence, String contextId ) throws LdapInvalidAttributeValueException;
}
