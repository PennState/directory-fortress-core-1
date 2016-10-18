package org.apache.directory.fortress.core.search;

import static org.apache.directory.ldap.client.api.search.FilterBuilder.equal;
import static org.apache.directory.ldap.client.api.search.FilterBuilder.and;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.annotation.XmlTransient;

import org.apache.directory.fortress.core.GlobalIds;
import org.apache.directory.ldap.client.api.search.FilterBuilder;

public class AbstractQueryBuilder<T extends AbstractQueryBuilder<T>>
{
    protected List<FilterBuilder> filters = new ArrayList<FilterBuilder>();
    protected String contextId;
    
    public String getContextId()
    {
        return contextId;
    }

    public void setContextId( String contextId )
    {
        this.contextId = contextId;
    }

    private Map<String, String> propertyFilters = new HashMap<String, String>();

    public T addPropertyEqualsFilter( String property, String value ){
        filters.add( equal( GlobalIds.PROPS, property + ":" + value ) );
        return (T)this;
    }
    
    public String buildFilterString(){
        return and( filters.toArray( new FilterBuilder[filters.size()] ) ).toString();
    }
    
}
