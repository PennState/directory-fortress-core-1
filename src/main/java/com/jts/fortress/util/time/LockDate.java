/*
 * Copyright (c) 2009-2013, JoshuaTree. All Rights Reserved.
 */

package com.jts.fortress.util.time;


import com.jts.fortress.GlobalIds;
import com.jts.fortress.rbac.Session;

/**
 * This class performs lock date validation for {@link com.jts.fortress.util.time.Constraint}.  This validator will ensure the current date falls outside {@link com.jts.fortress.util.time.Constraint#getBeginLockDate()} and {@link com.jts.fortress.util.time.Constraint#getEndLockDate()} range.
 * The idea is an entity can be barred from activation for a particular blackout period, i.e. vacation, leave of absence, etc.
 * The data format requires YYYYMMDD, i.e. 20110101 for January 1, 2011.  The constant {@link com.jts.fortress.GlobalIds#NONE} may be used to disable checks for a particular entity.
 * <h4> Constraint Targets include</h4>
 * <ol>
 * <li>{@link com.jts.fortress.rbac.User} maps to 'ftCstr' attribute on 'ftUserAttrs' object class</li>
 * <li>{@link com.jts.fortress.rbac.UserRole} maps to 'ftRC' attribute on 'ftUserAttrs' object class</li>
 * <li>{@link com.jts.fortress.rbac.Role}  maps to 'ftCstr' attribute on 'ftRls' object class</li>
 * <li>{@link com.jts.fortress.rbac.AdminRole}  maps to 'ftCstr' attribute on 'ftRls' object class</li>
 * <li>{@link com.jts.fortress.rbac.UserAdminRole}  maps to 'ftARC' attribute on 'ftRls' object class</li>
 * </ol>
 * </p>
 *
 * @author Shawn McKinney
 */
public class LockDate
    implements Validator
{
    /**
     * This method is called during entity activation, {@link com.jts.fortress.util.time.CUtil#validateConstraints} and ensures the current date falls
     * outside the {@link com.jts.fortress.util.time.Constraint#getBeginLockDate()} and {@link com.jts.fortress.util.time.Constraint#getEndLockDate()} range.
     *
     * This validation routine will automatically pass if either beginLockDate or endLockDate equals null or "none".
     * If both beginLockDate and endLockDate are set the validator will ensure current date does not fall between the date range.
     * The format expected if date is set is YYYYMMDD.  For example, '20110101' equals Jan 1, 2011.
     *
     * @param session    required for {@link Validator} interface but not used here.
     * @param constraint contains the begin and end lock dates.  Maps listed above.
     * @param time       contains the current time stamp.
     * @return '0' if validation succeeds else {@link com.jts.fortress.GlobalErrIds#ACTV_FAILED_LOCK} if failed.
     */
    @Override
    public int validate(Session session, Constraint constraint, Time time)
    {
        int rc;

        // if either beginLockDate or endLockDate equal to null or 'none', validation will automatically pass.
        if ( constraint.getBeginLockDate() == null || constraint.getBeginLockDate().compareToIgnoreCase(GlobalIds.NONE) == 0
          || constraint.getEndLockDate() == null || constraint.getEndLockDate().compareToIgnoreCase(GlobalIds.NONE) == 0)
        {
            rc = 0;
        }
        else
        {
            if (!(constraint.getBeginLockDate().compareTo(time.date) <= 0
                && constraint.getEndLockDate().compareTo(time.date) >= 0))

                if (!(constraint.getBeginLockDate().compareTo(time.date) <= 0
                    && constraint.getEndLockDate().compareTo(time.date) >= 0))
                {
                    rc = 0;
                }

            {
                rc = 0;
            }
        }
        return rc;
    }
}

