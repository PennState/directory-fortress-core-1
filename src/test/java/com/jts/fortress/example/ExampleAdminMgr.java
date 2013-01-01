/*
 * Copyright (c) 2009-2013, JoshuaTree. All Rights Reserved.
 */

package com.jts.fortress.example;

import com.jts.fortress.SecurityException;

/**
 * Interface class for ExampleAdminMgrImpl.
 *
 * @author Shawn McKinney
 * @created December 26, 2010
 */
public interface ExampleAdminMgr
{
    public Example addExample(Example example)
        throws com.jts.fortress.SecurityException;

    public void delExample(Example example)
        throws SecurityException;
}

