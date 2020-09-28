/*
 * Copyright (c) 2002-2020, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.progressmanager.business;

import fr.paris.lutece.plugins.progressmanager.service.ProgressManagerService;
import fr.paris.lutece.test.LuteceTestCase;
import java.util.Map;

/**
 * This is the business class test for the object ProgressFeed
 */
public class ProgressFeedBusinessTest extends LuteceTestCase
{
    private static final int NBITEMTOTAL = 10;
    private static final int NBITEMSUCCESS1 = 1;
    private static final int NBITEMSUCCESS2 = 2;
    private static final int NBITEMFAILURE1 = 1;
    private static final int NBITEMFAILURE2 = 2;
    private static final String REPORT1 = "Report1";
    private static final String REPORT2 = "Report2";
    private static final String FEED_NAME = "TestFeed";

    /**
     * test ProgressFeed
     */
    public void testBusiness( )
    {
        // Initialize
        ProgressManagerService service = ProgressManagerService.getInstance( );
        String strFeedToken = service.registerFeed( FEED_NAME, NBITEMTOTAL );

        Map<String, ProgressFeed> map = service.getProgressFeeds( );
        assertNotNull( map );

        // increment
        service.incrementSuccess( strFeedToken, NBITEMSUCCESS1 );
        service.incrementSuccess( strFeedToken, NBITEMSUCCESS2 );
        service.incrementFailure( strFeedToken, NBITEMFAILURE1 );
        service.incrementFailure( strFeedToken, NBITEMFAILURE2 );
        service.addReport( strFeedToken, REPORT1 );
        service.addReport( strFeedToken, REPORT2 );

        // test
        assertEquals( service.getProgressStatus( strFeedToken ), 60 );
        assertEquals( service.getSuccessNb( strFeedToken ), NBITEMSUCCESS1 + NBITEMSUCCESS2 );
        assertEquals( service.getFailureNb( strFeedToken ), NBITEMFAILURE1 + NBITEMFAILURE2 );
        assertEquals( service.getReport( strFeedToken ).get( 0 ), REPORT1 );
        assertEquals( service.getReport( strFeedToken ).get( 1 ), REPORT2 );

        service.unRegisterFeed( strFeedToken );

    }

}
