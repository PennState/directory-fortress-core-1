/*
 * Copyright (c) 2009-2013, JoshuaTree. All Rights Reserved.
 */

package com.jts.fortress.rbac;

import com.jts.fortress.CfgException;
import com.jts.fortress.GlobalErrIds;

import java.io.InputStream;


/**
 * General purpose factory uses java reflection to instantiate new Manager object.
 * </p>
 * This class is called by the Manager factories:
 * <ol>
 * <li>{@link com.jts.fortress.AccessMgrFactory}</li>
 * <li>{@link com.jts.fortress.AdminMgrFactory}</li>
 * <li>{@link com.jts.fortress.AuditMgrFactory}</li>
 * <li>{@link com.jts.fortress.DelAccessMgrFactory}</li>
 * <li>{@link com.jts.fortress.DelAdminMgrFactory}</li>
 * <li>{@link com.jts.fortress.DelReviewMgrFactory}</li>
 * <li>{@link com.jts.fortress.PwPolicyMgrFactory}</li>
 * <li>{@link com.jts.fortress.ReviewMgrFactory}</li>
 * <li>{@link com.jts.fortress.cfg.ConfigMgrFactory}</li>
 * </ol>
 *
 * @author Shawn McKinney
 */
public class ClassUtil
{
    private static final String CLS_NM = ClassUtil.class.getName();

    /**
     * Given a valid class name call the default constructor through reflexion and return the reference to the caller.
     * @param className contains fully qualified java class name to be instantiated.  Must have a public default constructor to be successful.
     * @return reference to instantiated ManagerImpl object.
     * @throws com.jts.fortress.CfgException in the event of failure to instantiate.
     *
     */
    public static Object createInstance(String className)
        throws CfgException
    {
        Object target;
        try
        {
            if (className == null || className.length() == 0)
            {
                String error = CLS_NM + ".createInstance() null or empty classname";
                throw new CfgException(GlobalErrIds.FT_MGR_CLASS_NAME_NULL, error);
            }
            target = Class.forName(className).newInstance();
        }
        catch (java.lang.ClassNotFoundException e)
        {
            String error = CLS_NM + ".createInstance() className [" + className + "] caught java.lang.ClassNotFoundException=" + e;
            throw new CfgException(GlobalErrIds.FT_MGR_CLASS_NOT_FOUND, error, e);
        }
        catch (java.lang.InstantiationException e)
        {
            String error = CLS_NM + ".createInstance()  [" + className + "] caught java.lang.InstantiationException=" + e;
            throw new CfgException(GlobalErrIds.FT_MGR_INST_EXCEPTION, error, e);
        }
        catch (java.lang.IllegalAccessException e)
        {
            String error = CLS_NM + ".createInstance()  [" + className + "] caught java.lang.IllegalAccessException=" + e;
            throw new CfgException(GlobalErrIds.FT_MGR_ILLEGAL_ACCESS, error, e);
        }
        return target;
	}


    /**
     * Find a file on the classloader and return as InputStream.
     * @param name contains the name of the file resource.
     * @return handle to the InputStream
     * @throws com.jts.fortress.CfgException in the event resource is not found on classloader.
     */
    public static InputStream resourceAsStream(String name) throws CfgException
    {
        InputStream is = null;
        is = ClassUtil.class.getClassLoader().getResourceAsStream(name);
        if (is == null)
        {
            throw new CfgException(GlobalErrIds.FT_RESOURCE_NOT_FOUND, name);
        }
        return is;
    }
}
